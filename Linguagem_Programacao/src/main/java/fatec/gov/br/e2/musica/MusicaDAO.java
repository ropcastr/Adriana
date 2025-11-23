package fatec.gov.br.e2.musica;

import java.sql.*;
import java.util.*;
import java.io.*;

public class MusicaDAO {
    public MusicaDAO(){ MusicaDatabase.get(); }
    private Connection conn(){ return MusicaDatabase.get(); }

    public int salvarPessoa(String nome,int idade,String reputacao){ return salvarGenerico("INSERT INTO pessoa (nome,idade,reputacao) VALUES (?,?,?)", ps -> { ps.setString(1,nome); ps.setInt(2,idade); ps.setString(3,reputacao);} ); }
    public int salvarCarro(String modelo,String cor,String motor){ return salvarGenerico("INSERT INTO carro (modelo,cor,motor) VALUES (?,?,?)", ps -> { ps.setString(1,modelo); ps.setString(2,cor); ps.setString(3,motor);} ); }
    public int salvarViolao(int cordas,String madeira,String timbre){ return salvarGenerico("INSERT INTO violao (cordas,madeira,timbre) VALUES (?,?,?)", ps -> { ps.setInt(1,cordas); ps.setString(2,madeira); ps.setString(3,timbre);} ); }
    public int salvarCurva(String localizacao,String perigosidade,String nome){ return salvarGenerico("INSERT INTO curva (localizacao,perigosidade,nome) VALUES (?,?,?)", ps -> { ps.setString(1,localizacao); ps.setString(2,perigosidade); ps.setString(3,nome);} ); }
    public void salvarMetodos(String pessoaMsg,String carroMsg,String violaoMsg,String curvaMsg){ try(Connection c=conn(); PreparedStatement ps=c.prepareStatement("INSERT INTO metodo_log (pessoa_msg,carro_msg,violao_msg,curva_msg) VALUES (?,?,?,?)")){ ps.setString(1,pessoaMsg); ps.setString(2,carroMsg); ps.setString(3,violaoMsg); ps.setString(4,curvaMsg); ps.executeUpdate(); } catch(SQLException e){ throw new RuntimeException(e);} }

    private int salvarGenerico(String sql, SqlSetter setter){ try(Connection c=conn(); PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){ setter.apply(ps); ps.executeUpdate(); try(ResultSet rs=ps.getGeneratedKeys()){ if(rs.next()) return rs.getInt(1);} } catch(SQLException e){ throw new RuntimeException(e);} return -1; }
    private interface SqlSetter { void apply(PreparedStatement ps) throws SQLException; }

    public List<String> listarMetodos(){ List<String> lista=new ArrayList<>(); try(Connection c=conn(); PreparedStatement ps=c.prepareStatement("SELECT pessoa_msg,carro_msg,violao_msg,curva_msg FROM metodo_log ORDER BY id DESC"); ResultSet rs=ps.executeQuery()){ while(rs.next()){ lista.add(rs.getString(1)); lista.add(rs.getString(2)); lista.add(rs.getString(3)); lista.add(rs.getString(4)); } } catch(SQLException e){ throw new RuntimeException(e);} return lista; }

    public List<Map<String,Object>> listarPessoas(){ return listarTabela("SELECT id,nome,idade,reputacao FROM pessoa ORDER BY id DESC", rs -> {
        Map<String,Object> m=new LinkedHashMap<>(); m.put("id",rs.getInt("id")); m.put("nome",rs.getString("nome")); m.put("idade",rs.getInt("idade")); m.put("reputacao",rs.getString("reputacao")); return m; }); }
    public List<Map<String,Object>> listarCarros(){ return listarTabela("SELECT id,modelo,cor,motor FROM carro ORDER BY id DESC", rs -> {
        Map<String,Object> m=new LinkedHashMap<>(); m.put("id",rs.getInt("id")); m.put("modelo",rs.getString("modelo")); m.put("cor",rs.getString("cor")); m.put("motor",rs.getString("motor")); return m; }); }
    public List<Map<String,Object>> listarVioloes(){ return listarTabela("SELECT id,cordas,madeira,timbre FROM violao ORDER BY id DESC", rs -> {
        Map<String,Object> m=new LinkedHashMap<>(); m.put("id",rs.getInt("id")); m.put("cordas",rs.getInt("cordas")); m.put("madeira",rs.getString("madeira")); m.put("timbre",rs.getString("timbre")); return m; }); }
    public List<Map<String,Object>> listarCurvas(){ return listarTabela("SELECT id,localizacao,perigosidade,nome FROM curva ORDER BY id DESC", rs -> {
        Map<String,Object> m=new LinkedHashMap<>(); m.put("id",rs.getInt("id")); m.put("localizacao",rs.getString("localizacao")); m.put("perigosidade",rs.getString("perigosidade")); m.put("nome",rs.getString("nome")); return m; }); }

    private interface RowMapper { Map<String,Object> map(ResultSet rs) throws SQLException; }
    private List<Map<String,Object>> listarTabela(String sql, RowMapper mapper){ List<Map<String,Object>> out=new ArrayList<>(); try(Connection c=conn(); PreparedStatement ps=c.prepareStatement(sql); ResultSet rs=ps.executeQuery()){ while(rs.next()) out.add(mapper.map(rs)); } catch(SQLException e){ throw new RuntimeException(e);} return out; }

    public void limparBanco(){ try(Connection c=conn(); Statement st=c.createStatement()){ st.executeUpdate("DELETE FROM pessoa"); st.executeUpdate("DELETE FROM carro"); st.executeUpdate("DELETE FROM violao"); st.executeUpdate("DELETE FROM curva"); st.executeUpdate("DELETE FROM metodo_log"); } catch(SQLException e){ throw new RuntimeException(e);} }

    public File exportarCsv(String path){ File f=new File(path); try(FileWriter w=new FileWriter(f)){ w.write("nome,idade,reputacao,modelo,cor,motor,cordas,madeira,timbre,curva_nome,curva_localizacao,curva_perigosidade,pessoa_msg,carro_msg,violao_msg,curva_msg\n"); // header
            // assumir mesmo número de registros por inserção simultânea (alinhar por ordem de inserção)
            List<Map<String,Object>> pessoas=listarPessoas(); List<Map<String,Object>> carros=listarCarros(); List<Map<String,Object>> violoes=listarVioloes(); List<Map<String,Object>> curvas=listarCurvas(); List<String> metodos=listarMetodos();
            int linhas=Math.min(Math.min(pessoas.size(),carros.size()),Math.min(violoes.size(),curvas.size()));
            // cada registro possui 4 mensagens em sequência em metodos (pessoa, carro, violao, curva)
            for(int i=0;i<linhas;i++){
                int msgBase=i*4; String pessoaMsg= msgBase < metodos.size()? metodos.get(msgBase):""; String carroMsg=msgBase+1<metodos.size()?metodos.get(msgBase+1):""; String violaoMsg=msgBase+2<metodos.size()?metodos.get(msgBase+2):""; String curvaMsg=msgBase+3<metodos.size()?metodos.get(msgBase+3):"";
                Map<String,Object> p=pessoas.get(linhas-1 - i); // invert to match chronological order
                Map<String,Object> c=carros.get(linhas-1 - i); Map<String,Object> v=violoes.get(linhas-1 - i); Map<String,Object> cu=curvas.get(linhas-1 - i);
                w.write(String.join(",",
                        str(p.get("nome")),str(p.get("idade")),str(p.get("reputacao")),
                        str(c.get("modelo")),str(c.get("cor")),str(c.get("motor")),
                        str(v.get("cordas")),str(v.get("madeira")),str(v.get("timbre")),
                        str(cu.get("nome")),str(cu.get("localizacao")),str(cu.get("perigosidade")),
                        quote(pessoaMsg),quote(carroMsg),quote(violaoMsg),quote(curvaMsg)) + "\n");
            }
        } catch(IOException e){ throw new RuntimeException(e);} return f; }
    private String str(Object o){ return String.valueOf(o); }
    private String quote(String s){ return '"'+s.replace("\"","'")+'"'; }
}

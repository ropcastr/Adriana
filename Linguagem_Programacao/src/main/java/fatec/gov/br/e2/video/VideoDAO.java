package fatec.gov.br.e2.video;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {
    public VideoDAO(){ VideoDatabase.get(); }
    private Connection conn(){ return VideoDatabase.get(); }

    private interface Setter { void apply(PreparedStatement ps) throws SQLException; }
    private int insert(String sql, Setter setter){
        try(Connection c=conn(); PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            setter.apply(ps);
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){ if(rs.next()) return rs.getInt(1); }
        }catch(SQLException e){ throw new RuntimeException(e); }
        return -1;
    }

    public int salvarChef(String nome,int experiencia,String especialidade,String criadoEm){
        return insert("INSERT INTO chef (nome,experiencia,especialidade,criado_em) VALUES (?,?,?,?)", ps -> {
            ps.setString(1,nome); ps.setInt(2,experiencia); ps.setString(3,especialidade); ps.setString(4,criadoEm);
        });
    }
    public int salvarIngrediente(String nome,double quantidade,String unidade){
        return insert("INSERT INTO ingrediente (nome,quantidade,unidade) VALUES (?,?,?)", ps -> {
            ps.setString(1,nome); ps.setDouble(2,quantidade); ps.setString(3,unidade);
        });
    }
    public int salvarUtensilio(String tipo,String material,double tamanho){
        return insert("INSERT INTO utensilio (tipo,material,tamanho) VALUES (?,?,?)", ps -> {
            ps.setString(1,tipo); ps.setString(2,material); ps.setDouble(3,tamanho);
        });
    }
    public void salvarLog(String mensagem){
        try(Connection c=conn(); PreparedStatement ps=c.prepareStatement("INSERT INTO log (mensagem) VALUES (?)")){
            ps.setString(1,mensagem); ps.executeUpdate();
        }catch(SQLException e){ throw new RuntimeException(e); }
    }

    public List<String> listarLogs(){
        List<String> list=new ArrayList<>();
        try(Connection c=conn(); PreparedStatement ps=c.prepareStatement("SELECT mensagem,ts FROM log ORDER BY id DESC LIMIT 200"); ResultSet rs=ps.executeQuery()){
            while(rs.next()) list.add("["+rs.getString(2)+"] "+rs.getString(1));
        }catch(SQLException e){ throw new RuntimeException(e); }
        return list;
    }

    public void limparTudo(){
        try(Connection c=conn(); Statement st=c.createStatement()){
            st.executeUpdate("DELETE FROM chef");
            st.executeUpdate("DELETE FROM ingrediente");
            st.executeUpdate("DELETE FROM utensilio");
            st.executeUpdate("DELETE FROM log");
        }catch(SQLException e){ throw new RuntimeException(e); }
    }
}

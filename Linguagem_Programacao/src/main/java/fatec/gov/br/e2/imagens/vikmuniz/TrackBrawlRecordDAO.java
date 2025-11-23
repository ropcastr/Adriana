package fatec.gov.br.e2.imagens.vikmuniz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackBrawlRecordDAO {
    public TrackBrawlRecordDAO(){ ImagensDatabase.get(); }
    public void salvar(String pessoaNome,int pessoaForca,String pessoaPosicao,float trilhoBitola,float trilhoComprimento,String trilhoMaterial,String vencedorNome,int vencedorPontuacao,String vencedorTempo,String pessoaMsg,String trilhoMsg,String vencedorMsg){
        String sql="INSERT INTO trackbrawl (pessoa_nome,pessoa_forca,pessoa_posicao,trilho_bitola,trilho_comprimento,trilho_material,vencedor_nome,vencedor_pontuacao,vencedor_tempo,pessoa_msg,trilho_msg,vencedor_msg) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection c=ImagensDatabase.get(); PreparedStatement ps=c.prepareStatement(sql)){ ps.setString(1,pessoaNome); ps.setInt(2,pessoaForca); ps.setString(3,pessoaPosicao); ps.setFloat(4,trilhoBitola); ps.setFloat(5,trilhoComprimento); ps.setString(6,trilhoMaterial); ps.setString(7,vencedorNome); ps.setInt(8,vencedorPontuacao); ps.setString(9,vencedorTempo); ps.setString(10,pessoaMsg); ps.setString(11,trilhoMsg); ps.setString(12,vencedorMsg); ps.executeUpdate(); }catch(SQLException e){ throw new RuntimeException(e);} }
    public List<String> listarMensagens(){ List<String> lista=new ArrayList<>(); String sql="SELECT pessoa_msg,trilho_msg,vencedor_msg FROM trackbrawl ORDER BY id DESC"; try(Connection c=ImagensDatabase.get(); PreparedStatement ps=c.prepareStatement(sql); ResultSet rs=ps.executeQuery()){ while(rs.next()){ lista.add(rs.getString(1)); lista.add(rs.getString(2)); lista.add(rs.getString(3)); } } catch(SQLException e){ throw new RuntimeException(e);} return lista; }
}


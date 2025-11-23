package fatec.gov.br.e2.imagens.vikmuniz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaparazziRecordDAO {
    public PaparazziRecordDAO(){ ImagensDatabase.get(); }
    public void salvar(String modelo,String lente,int resolucao,String marca,float compCabo,int sensibilidade,String nomeFotografo,String experiencia,String cameraMsg,String fotografoMsg,String microfoneMsg){
        String sql="INSERT INTO paparazzi (modelo,lente,resolucao,marca,comp_cabo,sensibilidade,nome_fotografo,experiencia,camera_msg,fotografo_msg,microfone_msg) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection c=ImagensDatabase.get(); PreparedStatement ps=c.prepareStatement(sql)){ ps.setString(1,modelo); ps.setString(2,lente); ps.setInt(3,resolucao); ps.setString(4,marca); ps.setFloat(5,compCabo); ps.setInt(6,sensibilidade); ps.setString(7,nomeFotografo); ps.setString(8,experiencia); ps.setString(9,cameraMsg); ps.setString(10,fotografoMsg); ps.setString(11,microfoneMsg); ps.executeUpdate(); }catch(SQLException e){ throw new RuntimeException(e);} }
    public List<String> listarMensagens(){ List<String> lista=new ArrayList<>(); String sql="SELECT camera_msg,fotografo_msg,microfone_msg FROM paparazzi ORDER BY id DESC"; try(Connection c=ImagensDatabase.get(); PreparedStatement ps=c.prepareStatement(sql); ResultSet rs=ps.executeQuery()){ while(rs.next()){ lista.add(rs.getString(1)); lista.add(rs.getString(2)); lista.add(rs.getString(3)); } } catch(SQLException e){ throw new RuntimeException(e);} return lista; }
}


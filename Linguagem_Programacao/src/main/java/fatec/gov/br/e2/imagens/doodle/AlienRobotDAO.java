package fatec.gov.br.e2.imagens.doodle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlienRobotDAO {
    static { DoodleDatabase.get(); }
    private Connection conn(){ return DoodleDatabase.get(); }

    public AlienDb salvarAlien(AlienDb a){ String sql="INSERT INTO alien (tipo,forca,vida) VALUES (?,?,?)"; try(Connection c=conn(); PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){ ps.setString(1,a.getTipo()); ps.setInt(2,a.getForca()); ps.setInt(3,a.getVida()); ps.executeUpdate(); try(ResultSet rs=ps.getGeneratedKeys()){ if(rs.next()) a.setId(rs.getInt(1)); } return a; } catch(SQLException e){ throw new RuntimeException(e);} }
    public RobotDb salvarRobot(RobotDb r){ String sql="INSERT INTO robot (nivel,vida,pontos) VALUES (?,?,?)"; try(Connection c=conn(); PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){ ps.setInt(1,r.getNivel()); ps.setInt(2,r.getVida()); ps.setInt(3,r.getPontos()); ps.executeUpdate(); try(ResultSet rs=ps.getGeneratedKeys()){ if(rs.next()) r.setId(rs.getInt(1)); } return r; } catch(SQLException e){ throw new RuntimeException(e);} }
    public List<AlienDb> listarAliens(){ List<AlienDb> lista=new ArrayList<>(); String sql="SELECT * FROM alien ORDER BY id DESC"; try(Connection c=conn(); PreparedStatement ps=c.prepareStatement(sql); ResultSet rs=ps.executeQuery()){ while(rs.next()){ lista.add(new AlienDb(rs.getInt("id"),rs.getString("tipo"),rs.getInt("forca"),rs.getInt("vida"))); } } catch(SQLException e){ throw new RuntimeException(e);} return lista; }
    public List<RobotDb> listarRobots(){ List<RobotDb> lista=new ArrayList<>(); String sql="SELECT * FROM robot ORDER BY id DESC"; try(Connection c=conn(); PreparedStatement ps=c.prepareStatement(sql); ResultSet rs=ps.executeQuery()){ while(rs.next()){ lista.add(new RobotDb(rs.getInt("id"),rs.getInt("nivel"),rs.getInt("vida"),rs.getInt("pontos"))); } } catch(SQLException e){ throw new RuntimeException(e);} return lista; }
}

package fatec.gov.br.e2.calculomedia;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResultadoMediaDAO {
    private final String url = "jdbc:sqlite:src/main/java/fatec/gov/br/e2/calculomedia/medias.db";

    public ResultadoMediaDAO(){initTable();}

    private void initTable(){
        String sql = "CREATE TABLE IF NOT EXISTS resultados ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "e1 REAL, e2 REAL, p1 REAL, x REAL, api REAL, sub REAL, base REAL, media REAL, status TEXT, timestamp TEXT)";
        try(Connection c= DriverManager.getConnection(url); Statement st=c.createStatement()){st.execute(sql);}catch(SQLException e){throw new RuntimeException(e);}    }

    public ResultadoMedia salvar(ResultadoMedia r){
        String sql = "INSERT INTO resultados (e1,e2,p1,x,api,sub,base,media,status,timestamp) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try(Connection c=DriverManager.getConnection(url); PreparedStatement ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            ps.setDouble(1,r.getE1()); ps.setDouble(2,r.getE2()); ps.setDouble(3,r.getP1()); ps.setDouble(4,r.getX()); ps.setDouble(5,r.getApi()); ps.setDouble(6,r.getSub()); ps.setDouble(7,r.getBase()); ps.setDouble(8,r.getMediaFinal()); ps.setString(9,r.getStatus()); ps.setString(10,r.getTimestamp());
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){ if(rs.next()) r.setId(rs.getInt(1)); }
            return r;
        }catch(SQLException e){throw new RuntimeException(e);}    }

    public List<ResultadoMedia> listar(){
        List<ResultadoMedia> lista=new ArrayList<>();
        String sql="SELECT * FROM resultados ORDER BY id DESC";
        try(Connection c=DriverManager.getConnection(url); PreparedStatement ps=c.prepareStatement(sql); ResultSet rs=ps.executeQuery()){
            while(rs.next()){
                lista.add(new ResultadoMedia(
                        rs.getInt("id"),
                        rs.getDouble("e1"), rs.getDouble("e2"), rs.getDouble("p1"), rs.getDouble("x"), rs.getDouble("api"), rs.getDouble("sub"),
                        rs.getDouble("base"), rs.getDouble("media"), rs.getString("status"),
                        LocalDateTime.parse(rs.getString("timestamp"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                ));
            }
        }catch(SQLException e){throw new RuntimeException(e);}    return lista; }

    public void deletarTodos(){
        try(Connection c=DriverManager.getConnection(url); Statement st=c.createStatement()){ st.executeUpdate("DELETE FROM resultados"); }catch(SQLException e){ throw new RuntimeException(e);} }
}


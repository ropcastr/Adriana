package fatec.gov.br.e2.imagens.doodle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DoodleDatabase {
    private static final String URL = "jdbc:sqlite:src/main/java/fatec/gov/br/e2/imagens/doodle/doodle.db";
    static { init(); }
    private static void init(){
        try(Connection c=DriverManager.getConnection(URL); Statement st=c.createStatement()){
            st.executeUpdate("CREATE TABLE IF NOT EXISTS alien (id INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT, forca INTEGER, vida INTEGER, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS robot (id INTEGER PRIMARY KEY AUTOINCREMENT, nivel INTEGER, vida INTEGER, pontos INTEGER, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
        }catch(SQLException e){ throw new RuntimeException(e); }
    }
    public static Connection get(){ try { return DriverManager.getConnection(URL);} catch (SQLException e){ throw new RuntimeException(e);} }
}


package fatec.gov.br.e2.video;

import java.sql.*;

public class VideoDatabase {
    private static final String URL = "jdbc:sqlite:src/main/java/fatec/gov/br/e2/video/video.db";
    static { init(); }
    private static void init(){
        try(Connection c=DriverManager.getConnection(URL); Statement st=c.createStatement()){
            st.executeUpdate("CREATE TABLE IF NOT EXISTS chef (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, experiencia INTEGER, especialidade TEXT, criado_em TEXT)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS ingrediente (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, quantidade REAL, unidade TEXT)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS utensilio (id INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT, material TEXT, tamanho REAL)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS log (id INTEGER PRIMARY KEY AUTOINCREMENT, mensagem TEXT, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
        }catch(SQLException e){ throw new RuntimeException(e);} }
    public static Connection get(){ try { return DriverManager.getConnection(URL);} catch(SQLException e){ throw new RuntimeException(e);} }
}


package fatec.gov.br.e2.musica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MusicaDatabase {
    private static final String URL = "jdbc:sqlite:src/main/java/fatec/gov/br/e2/musica/musica.db";
    static { init(); }
    private static void init(){
        try(Connection c=DriverManager.getConnection(URL); Statement st=c.createStatement()){
            st.executeUpdate("CREATE TABLE IF NOT EXISTS pessoa (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, idade INTEGER, reputacao TEXT, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS carro (id INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT, cor TEXT, motor TEXT, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS violao (id INTEGER PRIMARY KEY AUTOINCREMENT, cordas INTEGER, madeira TEXT, timbre TEXT, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS curva (id INTEGER PRIMARY KEY AUTOINCREMENT, localizacao TEXT, perigosidade TEXT, nome TEXT, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS metodo_log (id INTEGER PRIMARY KEY AUTOINCREMENT, pessoa_msg TEXT, carro_msg TEXT, violao_msg TEXT, curva_msg TEXT, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
        }catch(SQLException e){ throw new RuntimeException(e); }
    }
    public static Connection get(){ try { return DriverManager.getConnection(URL);} catch (SQLException e){ throw new RuntimeException(e);} }
}


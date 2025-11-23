package fatec.gov.br.e2.imagens.vikmuniz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ImagensDatabase {
    private static final String URL = "jdbc:sqlite:src/main/java/fatec/gov/br/e2/imagens/vikmuniz/vikmuniz.db"; // banco específico vikmuniz
    static { init(); }
    private static void init(){
        try(Connection c=DriverManager.getConnection(URL); Statement st=c.createStatement()){
            // Tabelas de domínio vikmuniz
            st.executeUpdate("CREATE TABLE IF NOT EXISTS paparazzi (id INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT, lente TEXT, resolucao INTEGER, marca TEXT, comp_cabo REAL, sensibilidade INTEGER, nome_fotografo TEXT, experiencia TEXT, camera_msg TEXT, fotografo_msg TEXT, microfone_msg TEXT, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS trackbrawl (id INTEGER PRIMARY KEY AUTOINCREMENT, pessoa_nome TEXT, pessoa_forca INTEGER, pessoa_posicao TEXT, trilho_bitola REAL, trilho_comprimento REAL, trilho_material TEXT, vencedor_nome TEXT, vencedor_pontuacao INTEGER, vencedor_tempo TEXT, pessoa_msg TEXT, trilho_msg TEXT, vencedor_msg TEXT, ts DATETIME DEFAULT CURRENT_TIMESTAMP)");
            // Tabelas alien/robot removidas daqui (usadas agora em doodle.db)
        }catch(SQLException e){ throw new RuntimeException(e); }
    }
    public static Connection get(){ try { return DriverManager.getConnection(URL);} catch (SQLException e){ throw new RuntimeException(e);} }
}

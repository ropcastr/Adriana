package fatec.gov.br.e2.cadastrocsv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operações CRUD de Pessoa usando SQLite.
 * O arquivo do banco é criado dentro do pacote conforme requisito.
 */
public class PessoaDAO {
    private final String url;

    public PessoaDAO() {
        // Caminho dentro do pacote (não é prática recomendada para produção)
        this.url = "jdbc:sqlite:src/main/java/fatec/gov/br/e2/cadastrocsv/pessoas.db";
        initTable();
    }

    public PessoaDAO(String url) { // usado em testes para apontar para outro arquivo
        this.url = url;
        initTable();
    }

    private void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS pessoas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "sexo TEXT, " +
                "altura REAL, " +
                "massa REAL" +
                ")";
        try (Connection conn = DriverManager.getConnection(url); Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro criando tabela pessoas", e);
        }
    }

    public Pessoa salvar(Pessoa p) {
        String sql = "INSERT INTO pessoas (nome, sexo, altura, massa) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getSexo());
            ps.setDouble(3, p.getAltura());
            ps.setDouble(4, p.getMassa());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar pessoa", e);
        }
    }

    public List<Pessoa> listarTodos() {
        String sql = "SELECT id, nome, sexo, altura, massa FROM pessoas";
        List<Pessoa> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Pessoa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sexo"),
                        rs.getDouble("altura"),
                        rs.getDouble("massa")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pessoas", e);
        }
        return lista;
    }

    public Pessoa buscarPorId(int id) {
        String sql = "SELECT id, nome, sexo, altura, massa FROM pessoas WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pessoa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("sexo"),
                            rs.getDouble("altura"),
                            rs.getDouble("massa")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pessoa por id", e);
        }
        return null;
    }

    public boolean atualizar(Pessoa p) {
        if (p.getId() == null) throw new IllegalArgumentException("Pessoa sem id para atualizar");
        String sql = "UPDATE pessoas SET nome=?, sexo=?, altura=?, massa=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getSexo());
            ps.setDouble(3, p.getAltura());
            ps.setDouble(4, p.getMassa());
            ps.setInt(5, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pessoa", e);
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM pessoas WHERE id=?";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pessoa", e);
        }
    }

    public int deletarTodos() {
        String sql = "DELETE FROM pessoas";
        try (Connection conn = DriverManager.getConnection(url); PreparedStatement ps = conn.prepareStatement(sql)) {
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar todas as pessoas", e);
        }
    }
}

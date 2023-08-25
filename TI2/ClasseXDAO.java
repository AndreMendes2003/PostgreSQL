import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClasseXDAO {
    private Connection connection;

    public ClasseXDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para listar todos os registros cadastrados
    public List<ClasseX> listarTodos() throws SQLException {
        List<ClasseX> lista = new ArrayList<>();
        String sql = "SELECT * FROM tabela_x";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ClasseX x = new ClasseX();
                x.setId(rs.getInt("id"));
                x.setNome(rs.getString("nome"));
                x.setIdade(rs.getInt("idade"));
                x.setEmail(rs.getString("email"));
                lista.add(x);
            }
        }
        return lista;
    }

    // Método para inserir um registro novo
    public void inserir(ClasseX x) throws SQLException {
        String sql = "INSERT INTO tabela_x (nome, idade, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, x.getNome());
            ps.setInt(2, x.getIdade());
            ps.setString(3, x.getEmail());
            ps.executeUpdate();
        }
    }

    // Método para excluir um registro
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM tabela_x WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Método para atualizar um registro
    public void atualizar(ClasseX x) throws SQLException {
        String sql = "UPDATE tabela_x SET nome=?, idade=?, email=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, x.getNome());
            ps.setInt(2, x.getIdade());
            ps.setString(3, x.getEmail());
            ps.setInt(4, x.getId());
            ps.executeUpdate();
        }
    }
}

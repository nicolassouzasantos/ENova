package br.com.fiap.porto.dao;

import br.com.fiap.porto.entity.User;
import java.sql.*;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User createUser(User user) throws SQLException {
        String sql = "INSERT INTO enova_users (nm_user, email_user, cidade_user, uf_user) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id_user"})) {
            stmt.setString(1, user.getNmUser());
            stmt.setString(2, user.getEmailUser());
            stmt.setString(3, user.getCidadeUser());
            stmt.setString(4, user.getUfUser());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir o usuário, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setIdUser(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao inserir o usuário, ID não obtido.");
                }
            }
        }

        return user;
    }

    // Outros métodos CRUD podem ser adicionados aqui
}

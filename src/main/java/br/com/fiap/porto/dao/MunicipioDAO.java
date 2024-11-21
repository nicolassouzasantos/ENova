package br.com.fiap.porto.dao;

import br.com.fiap.porto.entity.Municipio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO {

    private Connection connection;

    public MunicipioDAO(Connection connection) {
        this.connection = connection;
    }

    public Municipio findByNome(String nome) throws SQLException {
        String sql = "SELECT * FROM MUNICIPIO WHERE UF = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Municipio municipio = new Municipio();
                    municipio.setIdMunicipio(rs.getLong("Id_Municipio"));
                    municipio.setCodigo(rs.getLong("Codigo"));
                    municipio.setNome(rs.getString("Nome"));
                    municipio.setUf(rs.getString("Uf"));
                    municipio.setPerHidro(rs.getDouble("per_hidro"));
                    municipio.setPerEolica(rs.getDouble("per_eolica"));
                    municipio.setPerSolar(rs.getDouble("per_solar"));
                    return municipio;
                } else {
                    return null;
                }
            }
        }
    }

    public List<String> findNomesByUf(String uf) throws SQLException {
        String sql = "SELECT Nome FROM Municipio WHERE Uf = ?";
        List<String> nomes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uf);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nome = rs.getString("Nome");
                    nomes.add(nome);
                }
            }
        }
        return nomes;
    }
}

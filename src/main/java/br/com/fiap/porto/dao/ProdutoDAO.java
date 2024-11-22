package br.com.fiap.porto.dao;

import br.com.fiap.porto.entity.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Produto> getAllProdutos() throws SQLException {
        String sql = "SELECT * FROM ENOVA_PRODUTOS";
        List<Produto> produtos = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProd(rs.getLong("id_prod"));
                produto.setNmProd(rs.getString("nm_prod"));
                produto.setTipo(rs.getString("tipo"));
                produto.setTamanho(rs.getDouble("tamanho"));
                produto.setValor(rs.getDouble("valor"));
                produto.setQtdEstoque(rs.getInt("qtd_estoque"));
                produto.setQtdVendas(rs.getInt("qtd_vendas"));
                produtos.add(produto);
            }
        }

        return produtos;
    }

        public boolean atualizarQtdVendas(Long idProd, int quantidade) throws SQLException {
        String sql = "UPDATE ENOVA_PRODUTOS SET qtd_vendas = qtd_vendas + ? WHERE id_prod = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantidade);
            stmt.setLong(2, idProd);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public Produto getProdutoById(Long idProd) throws SQLException {
        String sql = "SELECT * FROM ENOVA_PRODUTOS WHERE id_prod = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idProd);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setIdProd(rs.getLong("id_prod"));
                    produto.setNmProd(rs.getString("nm_prod"));
                    produto.setTipo(rs.getString("tipo"));
                    produto.setTamanho(rs.getDouble("tamanho"));
                    produto.setValor(rs.getDouble("valor"));
                    produto.setQtdEstoque(rs.getInt("qtd_estoque"));
                    produto.setQtdVendas(rs.getInt("qtd_vendas"));
                    return produto;
                } else {
                    return null;
                }
            }
        }
    }

    public Produto getMaiorProdutoPorTamanho(double areaDisponivel) throws SQLException {
        String sql = "SELECT * FROM ENOVA_PRODUTOS " +
                "WHERE tamanho <= ? " +
                "ORDER BY tamanho DESC " +
                "FETCH FIRST 1 ROWS ONLY";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, areaDisponivel);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setIdProd(rs.getLong("id_prod"));
                    produto.setNmProd(rs.getString("nm_prod"));
                    produto.setTipo(rs.getString("tipo")); // Opcional
                    produto.setTamanho(rs.getDouble("tamanho"));
                    produto.setValor(rs.getDouble("valor"));
                    produto.setQtdEstoque(rs.getInt("qtd_estoque"));
                    produto.setQtdVendas(rs.getInt("qtd_vendas"));
                    produto.setPerEconomia(rs.getDouble("per_economia")); // Novo campo
                    return produto;
                } else {
                    return null;
                }
            }
        }
    }
}

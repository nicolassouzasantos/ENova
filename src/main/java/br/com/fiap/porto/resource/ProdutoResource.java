package br.com.fiap.porto.resource;

import br.com.fiap.porto.dao.ProdutoDAO;
import br.com.fiap.porto.entity.Produto;
import br.com.fiap.porto.factory.ConnectionFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.List;

@Path("/produtos")
public class ProdutoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutos() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(conn);
            List<Produto> produtos = produtoDAO.getAllProdutos();

            if (produtos.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Nenhum produto encontrado").build();
            } else {
                return Response.ok(produtos).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao obter produtos").build();
        }
    }

    @PUT
    @Path("/{id}/vendas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarQtdVendas(@PathParam("id") Long idProd, @QueryParam("quantidade") int quantidade) {
        if (quantidade <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("A quantidade deve ser maior que zero").build();
        }

        try (Connection conn = ConnectionFactory.getConnection()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(conn);

            Produto produto = produtoDAO.getProdutoById(idProd);
            if (produto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Produto não encontrado").build();
            }

            boolean atualizado = produtoDAO.atualizarQtdVendas(idProd, quantidade);

            if (atualizado) {
                return Response.ok("Quantidade de vendas atualizada com sucesso").build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar quantidade de vendas").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao processar a solicitação").build();
        }
    }

    @GET
    @Path("/recomendacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutoRecomendado(@QueryParam("areaDisponivel") double areaDisponivel) {
        if (areaDisponivel <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O tamanho da área disponível deve ser maior que zero")
                    .build();
        }

        try (Connection conn = ConnectionFactory.getConnection()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(conn);

            Produto produto = produtoDAO.getMaiorProdutoPorTamanho(areaDisponivel);

            if (produto != null) {
                return Response.ok(produto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum produto encontrado que caiba na área disponível")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao processar a solicitação")
                    .build();
        }
    }
}

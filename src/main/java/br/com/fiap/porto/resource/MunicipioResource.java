package br.com.fiap.porto.resource;

import br.com.fiap.porto.entity.Municipio;
import br.com.fiap.porto.dao.MunicipioDAO;
import br.com.fiap.porto.factory.ConnectionFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("/municipios")
public class MunicipioResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMunicipio(@QueryParam("uf") String nome) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            MunicipioDAO municipioDAO = new MunicipioDAO(conn);

            String nomeFormatado = Municipio.formatarNomeCidade(nome);
            System.out.println(nomeFormatado);
            Municipio municipio = municipioDAO.findByNome(nomeFormatado);

            if (municipio != null) {
                return Response.ok(municipio).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Município não encontrado").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro de banco de dados").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao processar a solicitação").build();
        }
    }

    @GET
    @Path("/por-uf")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMunicipiosPorUf(@QueryParam("uf") String uf) {
        if (uf == null || uf.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O parâmetro 'uf' é obrigatório.")
                    .build();
        }

        try (Connection conn = ConnectionFactory.getConnection()) {
            MunicipioDAO municipioDAO = new MunicipioDAO(conn);

            List<String> nomes = municipioDAO.findNomesByUf(uf);

            if (nomes.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum município encontrado para o UF: " + uf)
                        .build();
            } else {
                return Response.ok(nomes).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao processar a solicitação")
                    .build();
        }
    }
}

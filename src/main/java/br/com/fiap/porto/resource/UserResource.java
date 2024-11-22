package br.com.fiap.porto.resource;

import br.com.fiap.porto.entity.User;
import br.com.fiap.porto.dao.UserDAO;
import br.com.fiap.porto.factory.ConnectionFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;

@Path("/users")
public class UserResource {

    @POST
    @Path("/authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticacao(User user) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            UserDAO userDAO = new UserDAO(conn);

            // Busca o usuário pelo email, cidade e UF
            User userPesquisa = userDAO.findUserByEmailCidadeUf(user.getEmailUser(), user.getCidadeUser(), user.getUfUser());

            // Verifica se o usuário foi encontrado
            if (userPesquisa != null) {
                return Response.status(Response.Status.OK).entity(userPesquisa).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("{\"message\": \"Autenticação falhou. Usuário não encontrado.\"}").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\": \"Erro de banco de dados\"}").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\": \"Erro ao processar a solicitação\"}").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            UserDAO userDAO = new UserDAO(conn);

            // Salva o usuário no banco de dados
            User savedUser = userDAO.createUser(user);

            // Retorna o usuário cadastrado com sucesso
            return Response.status(Response.Status.CREATED).entity(savedUser).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro de banco de dados").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao processar a solicitação").build();
        }
    }
}

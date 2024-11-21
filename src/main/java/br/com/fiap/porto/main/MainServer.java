package br.com.fiap.porto.main;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class MainServer {

    // Defina a URI base onde o servidor irá escutar
    public static final String BASE_URI = "http://localhost:8080/api/";

    /**
     * Inicia o servidor Grizzly HTTP que expõe os recursos JAX-RS definidos neste aplicativo.
     * @return HttpServer O servidor Grizzly HTTP.
     */
    public static HttpServer startServer() {
        // Cria uma configuração de recursos que escaneia o pacote de recursos
        final ResourceConfig rc = new ResourceConfig().packages("br.com.fiap.projeto.resource");

        // Registre os provedores do Jackson para serialização JSON
        rc.register(org.glassfish.jersey.jackson.JacksonFeature.class);

        // Cria e inicia uma nova instância do servidor HTTP Grizzly
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Método principal.
     * @param args argumentos.
     * @throws IOException se ocorrer um erro de E/S.
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("Aplicação Jersey iniciada em " + BASE_URI);
        System.out.println("Pressione Enter para parar o servidor...");
        System.in.read();
        server.shutdownNow();
    }
}

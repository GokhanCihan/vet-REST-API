package gokhancihan.vet.utility.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Gökhan Cihan");
        myContact.setEmail("cihangokhan@gmail.com");

        Info information = new Info()
                .title("vet REST API")
                .version("1.0")
                .description("vet REST API created with Spring Boot, exposes endpoints of a veterinary management system")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}


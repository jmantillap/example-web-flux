package work.javiermantilla.example.security.router;

import lombok.extern.slf4j.Slf4j;
import work.javiermantilla.example.security.handler.AuthHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class RouterAuth {

    private static final String PATH = "auth/";

    @Bean
    RouterFunction<ServerResponse> authRouter(AuthHandler handler) {
        return RouterFunctions.route()
                .POST(PATH + "login", handler::login)
                .POST(PATH + "create", handler::create)
                .build();
    }
}

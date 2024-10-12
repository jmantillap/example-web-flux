package work.javiermantilla.example.security.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import work.javiermantilla.example.security.dto.CreateUserDto;
import work.javiermantilla.example.security.dto.LoginDto;
import work.javiermantilla.example.security.dto.TokenDto;
import work.javiermantilla.example.security.entity.User;
import work.javiermantilla.example.security.service.UserService;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthHandler {

    private final UserService userService;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginDto> dtoMono = request.bodyToMono(LoginDto.class);
        return dtoMono
                .flatMap(dto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.login(dto), TokenDto.class));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<CreateUserDto> dtoMono = request.bodyToMono(CreateUserDto.class);
        return dtoMono
                .flatMap(dto -> ServerResponse.ok()
                				.contentType(MediaType.APPLICATION_JSON)
                				.body(userService.create(dto), User.class));
    }
}

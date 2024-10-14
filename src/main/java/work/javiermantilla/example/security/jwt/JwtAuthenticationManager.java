package work.javiermantilla.example.security.jwt;


import java.util.List;
import java.util.Map;

import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import work.javiermantilla.example.exception.CustomException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
	 
	private final JwtProvider jwtProvider;
	
	@SuppressWarnings("unchecked")
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		return Mono.just(authentication)
                .map(auth -> jwtProvider.getClaims(auth.getCredentials().toString()))
                .log()
                .onErrorResume(e -> Mono.error(new CustomException(HttpStatus.UNAUTHORIZED, "bad token")))
                .map(claims -> {
                	List<SimpleGrantedAuthority> list= Stream.of(claims.get("roles"))
                											.map(role -> (List<Map<String, String>>) role)
								                    		.flatMap(role -> role.stream()
								                            .map(r -> r.get("authority"))
								                            .map(SimpleGrantedAuthority::new))
								                    		.toList();
                	return new UsernamePasswordAuthenticationToken(claims.getSubject(),null,list);                	
                });
	}

}

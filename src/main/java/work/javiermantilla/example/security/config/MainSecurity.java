package work.javiermantilla.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.LogoutSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.RequiredArgsConstructor;
import work.javiermantilla.example.security.jwt.JwtFilter;
import work.javiermantilla.example.security.repository.SecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class MainSecurity {
	
	private final SecurityContextRepository securityContextRepository;
		
	@Bean	
    SecurityWebFilterChain filterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {
        return http
                .authorizeExchange(authorize -> authorize
                                .pathMatchers("/auth/**")
                                .permitAll()
                                .anyExchange()
                                .authenticated()
                )
                .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
                .securityContextRepository(securityContextRepository)
                .formLogin(FormLoginSpec::disable)
                .logout(LogoutSpec::disable)
                .httpBasic(HttpBasicSpec::disable)
                .csrf(CsrfSpec::disable)
                .build();
    }
}

//return http
//        .authorizeExchange()
//        .pathMatchers("/auth/**")
//        .permitAll()
//        .anyExchange()
//        .authenticated()
//        .and()
//        .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
//        .securityContextRepository(securityContextRepository)
//        .formLogin().disable()
//        .logout().disable()
//        .httpBasic().disable()
//        .csrf().disable()
//        .build();

package work.javiermantilla.example.security.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import work.javiermantilla.example.exception.CustomException;
import work.javiermantilla.example.security.dto.CreateUserDto;
import work.javiermantilla.example.security.dto.LoginDto;
import work.javiermantilla.example.security.dto.TokenDto;
import work.javiermantilla.example.security.entity.User;
import work.javiermantilla.example.security.enums.Role;
import work.javiermantilla.example.security.jwt.JwtProvider;
import work.javiermantilla.example.security.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    
    public Mono<TokenDto> login(LoginDto dto) {
        return userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getUsername())
                .filter(user -> passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                .map(user -> new TokenDto(jwtProvider.generateToken(user)))
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "bad credentials")));
    }
    
    public Mono<User> create(CreateUserDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                //.roles(Role.ROLE_ADMIN.name() + ", " + Role.ROLE_USER.name())
                .roles(Role.ROLE_USER.name())
                .build();
        Mono<Boolean> userExists = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).hasElement();
        
        return userExists
                .flatMap(exists -> exists.booleanValue() ?
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "username or email already in use"))
                        : userRepository.save(user));
    }
    
    
    
}

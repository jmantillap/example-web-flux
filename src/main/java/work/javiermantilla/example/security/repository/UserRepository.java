package work.javiermantilla.example.security.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;
import work.javiermantilla.example.security.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
	Mono<User> findByUsernameOrEmail(String username, String email);
}

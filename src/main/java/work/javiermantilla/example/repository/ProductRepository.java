package work.javiermantilla.example.repository;

//import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import work.javiermantilla.example.entity.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
	Mono<Product> findByName(String name);
}

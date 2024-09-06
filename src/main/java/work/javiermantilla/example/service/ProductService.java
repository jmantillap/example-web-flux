package work.javiermantilla.example.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.javiermantilla.example.entity.Product;
import work.javiermantilla.example.repository.ProductRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository productRepository;

	public Flux<Product> getAll() {
		return productRepository.findAll();
	}

	public Mono<Product> getById(int id) {
		return productRepository.findById(id).
				switchIfEmpty(Mono.error(new RuntimeException("El producto no se encuentra")));
	}

	public Mono<Product> save(Product product) {		
		Mono<Boolean> isExist= this.productRepository.findByName(product.getName())
								.hasElement();		
		//return productRepository.save(product);
		return isExist.flatMap(exist-> 
						exist ?	Mono.error(new RuntimeException("Producto existe")) 
						: productRepository.save(product)
						);
	}

	public Mono<Product> update(int id, Product product) {
		log.info("Se va actualizar el producto con id : {} ",id);
		return productRepository.save(new Product(id, product.getName(), product.getPrice()));
	}

	public Mono<Void> delete(Integer id) {
		return productRepository.deleteById(id);
	}

}

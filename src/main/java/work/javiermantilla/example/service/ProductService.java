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
	
	private final static String NF_MESSAGE = "product not found";
    private final static String NAME_MESSAGE = "product name already in use";

	public Flux<Product> getAll() {
		return productRepository.findAll();
	}

	public Mono<Product> getById(int id) {
		return productRepository.findById(id).
				switchIfEmpty(Mono.error(new RuntimeException(NF_MESSAGE)));
	}

	public Mono<Product> save(Product product) {		
		Mono<Boolean> isExist= this.productRepository.findByName(product.getName())
								.hasElement();		
		//return productRepository.save(product);
		return isExist.flatMap(exist-> 
						exist.booleanValue() ?	Mono.error(new RuntimeException(NAME_MESSAGE)) 
						: productRepository.save(product)
						);
	}

	public Mono<Product> update(int id, Product product) {
		log.info("Se va actualizar el producto con id : {} ",id);
		Mono<Boolean> productId = productRepository.findById(id).hasElement();
		Mono<Boolean> productRepeatedName = productRepository.repeatedName(id, product.getName()).hasElement();		
		//return productRepository.save(new Product(id, product.getName(), product.getPrice()));
		
		return productId.flatMap(isExist->
							isExist.booleanValue() ? 
							productRepeatedName.flatMap(nameExist->
										!nameExist.booleanValue() ? 
										productRepository.save(new Product(id, product.getName(), product.getPrice()))
										: Mono.error(new RuntimeException(NAME_MESSAGE))	
							 ) 		
							: Mono.error(new RuntimeException(NF_MESSAGE))
				);
				
	}

	public Mono<Void> delete(Integer id) {
		Mono<Boolean> productId = productRepository.findById(id).hasElement();		
		//return productRepository.deleteById(id);
		return productId.flatMap(exist-> 
				exist.booleanValue() ? productRepository.deleteById(id) : Mono.error(new RuntimeException(NF_MESSAGE)) 
				);
		
	}

}

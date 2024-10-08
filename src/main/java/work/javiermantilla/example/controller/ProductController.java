package work.javiermantilla.example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.javiermantilla.example.dto.ProductDto;
import work.javiermantilla.example.entity.Product;
import work.javiermantilla.example.service.ProductService;
import work.javiermantilla.example.validation.ObjectValidator;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	private final ObjectValidator objectValidator;
	
	@GetMapping
	public Flux<Product> getAll(){
		return productService.getAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Product> getById(@PathVariable int id){
		return productService.getById(id);
	}
	
	@PostMapping
	public Mono<Product> save(@RequestBody ProductDto product){
		objectValidator.validate(product);
		return productService.save(product);
	}
	
	@PutMapping("/{id}")
	public Mono<Product> update(@PathVariable int id, @RequestBody ProductDto product){
		log.info("Se actualiza id: {}",id);
		objectValidator.validate(product);
		return productService.update(id,product);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable int id){
		log.info("Se elimina id: {}",id);
		return productService.delete(id);
	}
	
	
}

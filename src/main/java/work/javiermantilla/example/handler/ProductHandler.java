package work.javiermantilla.example.handler;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.javiermantilla.example.dto.ProductDto;
import work.javiermantilla.example.entity.Product;
import work.javiermantilla.example.service.ProductService;
import work.javiermantilla.example.validation.ObjectValidator;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductHandler {
	
	private final ProductService productService;
	private final ObjectValidator objectValidator;
	
	public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Product> products = productService.getAll();
        return ServerResponse.ok()
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(products, Product.class);
    }

    public Mono<ServerResponse> getOne(ServerRequest request) {        
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<Product> product = productService.getById(id);
        return ServerResponse.ok()
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(product, Product.class);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<ProductDto> productDTO = request.bodyToMono(ProductDto.class)
        		.doOnNext(objectValidator::validate);
        return productDTO.flatMap(p -> ServerResponse.status(201)
        					.contentType(MediaType.APPLICATION_JSON)
        					.body(productService.save(p), Product.class)
        					);
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Mono<ServerResponse> update(ServerRequest request) {    	
        
        int id = Integer.parseInt(request.pathVariable("id"));
        log.info("Se actializa el producto: {}",id);
        Mono<ProductDto> productDto = request.bodyToMono(ProductDto.class)
        							.doOnNext(objectValidator::validate);
        return productDto.flatMap(p -> ServerResponse.ok()
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(productService.update(id, p), Product.class));
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Mono<ServerResponse> delete(ServerRequest request) {
    	int id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse.ok()
        		.contentType(MediaType.APPLICATION_JSON)
        		.body(productService.delete(id), Product.class);
    }
	
}

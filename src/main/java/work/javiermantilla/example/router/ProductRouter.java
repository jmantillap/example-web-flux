package work.javiermantilla.example.router;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import work.javiermantilla.example.handler.ProductHandler;

@Configuration
@Slf4j
public class ProductRouter {

	private static final String PATH = "product-function";
	
	private static final String PATH_VARIABLE = PATH + "/{id}";
	
	@Bean
    WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }
	
	 @Bean
	 RouterFunction<ServerResponse> router(ProductHandler handler) {
		 	log.info("Se cargo el router para el manejaror de productos");
	        return RouterFunctions.route()
	                .GET(PATH, handler::getAll)
	                .GET(PATH_VARIABLE, handler::getOne)
	                .POST(PATH, handler::save)
	                .PUT(PATH_VARIABLE, handler::update)
	                .DELETE(PATH_VARIABLE, handler::delete)
	                .build();
	 }
	
}

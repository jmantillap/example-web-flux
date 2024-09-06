package work.javiermantilla.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class ExampleWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleWebFluxApplication.class, args);
	}

}

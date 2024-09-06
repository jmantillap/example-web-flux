package work.javiermantilla.example.config;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
//import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;


//import io.r2dbc.spi.ConnectionFactory;
//import lombok.extern.slf4j.Slf4j;

@Configuration
//@Slf4j
public class DBConfig /* extends AbstractR2dbcConfiguration */ {

//	@Value("${spring.r2dbc.url}")
//	private String dbUrl;
//
//	@Value("${spring.r2dbc.username}")
//	private String userName;
//
//	@Value("${spring.r2dbc.password}")
//	private String password;

	/*
	 * @Override public ConnectionFactory connectionFactory() {
	 * log.info("Se inicia connectionFactory "); return
	 * ConnectionFactories.get(this.dbUrl); }
	 */

//	@Bean
//	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
//
//		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//		initializer.setConnectionFactory(connectionFactory);
//		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//		log.info("Se inicializa base de datos");
//		return initializer;
//	}

}

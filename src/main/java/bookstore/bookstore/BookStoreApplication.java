package bookstore.bookstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BookStoreApplication {


//	localhost:8080/v2/api-docs ->this is for postman
	//http://localhost:8080/swagger-ui.html -> swgger ui
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Configuration
	public class JacksonConfig {
		@Bean
		public ObjectMapper objectMapper() {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			return objectMapper;
		}
	}

}

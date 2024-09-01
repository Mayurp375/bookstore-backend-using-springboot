package bookstore.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BookStoreApplication {


//	localhost:8080/v2/api-docs ->this is for postman
	//http://localhost:8080/swagger-ui.html -> swgger ui
	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

}

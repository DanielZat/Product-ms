package compasso.productms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Product-ms", version = "1.0", description = "Documentação do microserviço de produtos"))
public class ProductMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMsApplication.class, args);
    }
}

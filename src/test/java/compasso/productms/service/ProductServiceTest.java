package compasso.productms.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import compasso.productms.helper.ProductHelper;
import compasso.productms.mapper.ProductMapper;
import compasso.productms.repository.ProductRepository;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.main.webApplicationType=reactive"})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    // TODO: Falta implementar os testes da ProductService

    @Test
    public void teste() {

        var productRequest = ProductHelper.createProductRequest();
        var productEntity = ProductMapper.convertRequestToEntity(productRequest);
        var productEntityReturn = ProductHelper.createProductEntity();

        when(productRepository.save(productEntity)).thenReturn(Mono.just(productEntityReturn));

        var productResponse = productService.createProduct(productRequest).block();

        Assertions.assertNotNull(productResponse);
        Assertions.assertEquals(productEntityReturn.getId(), productResponse.getId());

    }
}

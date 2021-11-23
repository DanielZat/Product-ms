package compasso.productms.service;

import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

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

    @Test
    public void shouldCreateProductSuccessfully() {

        var productRequest = ProductHelper.createProductRequest();
        var productEntity = ProductMapper.convertRequestToEntity(productRequest);
        var productEntityReturn = ProductHelper.createProductEntity();

        when(productRepository.save(productEntity)).thenReturn(Mono.just(productEntityReturn));

        var productResponse = productService.createProduct(productRequest).block();

        Assertions.assertNotNull(productResponse);
        Assertions.assertEquals(productEntityReturn.getId(), productResponse.getId());
        Assertions.assertEquals(productEntityReturn.getName(), productResponse.getName());
        Assertions.assertEquals(productEntityReturn.getDescription(), productResponse.getDescription());
        Assertions.assertEquals(productEntityReturn.getPrice(), productResponse.getPrice());
    }

    @Test
    public void shouldUpdateProductSuccessfully() {

        var id = "6199a4ba8cdb5f7e50643648";
        var productRequest = ProductHelper.createProductRequest();
        var productEntity = ProductHelper.createProductEntity();

        when(productRepository.findById(id)).thenReturn(Mono.just(productEntity));
        when(productRepository.save(ProductMapper.updateProductEntityWithProductRequest(productEntity, productRequest))).thenReturn(Mono
                .just(productEntity));

        var productResponse = productService.updateProduct(id, productRequest).block();

        Assertions.assertNotNull(productResponse);
        Assertions.assertEquals(id, productResponse.getId());
        Assertions.assertEquals(productRequest.getName(), productResponse.getName());
        Assertions.assertEquals(productRequest.getDescription(), productResponse.getDescription());
        Assertions.assertEquals(productRequest.getPrice(), productResponse.getPrice());
    }

    @Test
    public void shouldReturnExceptionInUpdateMethodWhenProductIdNotExists() {

        var id = "6199a4ba8cdb5f7e506436222";
        var productRequest = ProductHelper.createProductRequest();

        when(productRepository.findById(id)).thenReturn(Mono.empty());

        try {
            productService.updateProduct(id, productRequest).block();
            Assertions.fail();
        } catch (ResponseStatusException ex) {
            Assertions.assertNotNull(ex);
            Assertions.assertTrue(StringUtils.isNotEmpty(ex.getMessage()));
            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            Assertions.assertEquals("Produto não foi localizado", ex.getReason());
        }
    }

    @Test
    public void shouldReturnFindByIdSuccessfully() {

        var id = "6199a4ba8cdb5f7e50643648";
        var productEntity = ProductHelper.createProductEntity();

        when(productRepository.findById(id)).thenReturn(Mono.just(productEntity));

        var productResponse = productService.findProductById(id).block();

        Assertions.assertNotNull(productResponse);
        Assertions.assertEquals(id, productResponse.getId());
        Assertions.assertEquals(productEntity.getName(), productResponse.getName());
        Assertions.assertEquals(productEntity.getDescription(), productResponse.getDescription());
        Assertions.assertEquals(productEntity.getPrice(), productResponse.getPrice());
    }

    @Test
    public void shouldReturnExceptionInFindByIdMethodWhenProductIdNotExists() {

        var id = "6199a4ba8cdb5f7e506436222";

        when(productRepository.findById(id)).thenReturn(Mono.empty());

        try {
            var productResponse = productService.findProductById(id).block();
            Assertions.fail();
        } catch (ResponseStatusException ex) {
            Assertions.assertNotNull(ex);
            Assertions.assertTrue(StringUtils.isNotEmpty(ex.getMessage()));
            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            Assertions.assertEquals("Produto não foi localizado", ex.getReason());
        }
    }

    @Test
    public void shouldReturnExceptionInDeleteMethodWhenProductIdNotExists() {

        var id = "6199a4ba8cdb5f7e506436222";

        when(productRepository.findById(id)).thenReturn(Mono.empty());

        try {
            productService.deleteProduct(id).block();
            Assertions.fail();
        } catch (ResponseStatusException ex) {
            Assertions.assertNotNull(ex);
            Assertions.assertTrue(StringUtils.isNotEmpty(ex.getMessage()));
            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            Assertions.assertEquals("Produto não foi localizado", ex.getReason());
        }
    }
}

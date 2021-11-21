package compasso.productms.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import compasso.productms.helper.ProductHelper;

public class ProductMapperTest {

    @Test
    public void shouldConvertRequestToEntitySuccessfully() {

        var productRequest = ProductHelper.createProductRequest();

        var productEntity = ProductMapper.convertRequestToEntity(productRequest);

        Assertions.assertNotNull(productEntity);
        Assertions.assertEquals(productRequest.getName(), productEntity.getName());
        Assertions.assertEquals(productRequest.getDescription(), productEntity.getDescription());
        Assertions.assertEquals(productRequest.getPrice(), productEntity.getPrice());
    }

    @Test
    public void shouldConvertEntityToResponseSuccessfully() {

        var productEntity = ProductHelper.createProductEntity();

        var productResponse = ProductMapper.convertEntityToResponse(productEntity);

        Assertions.assertNotNull(productResponse);
        Assertions.assertEquals(productEntity.getId(), productResponse.getId());
        Assertions.assertEquals(productEntity.getName(), productResponse.getName());
        Assertions.assertEquals(productEntity.getDescription(), productResponse.getDescription());
        Assertions.assertEquals(productEntity.getPrice(), productResponse.getPrice());
    }

    @Test
    public void shouldUpdateProductEntityWithProductRequestSuccessfully() {

        var productRequest = ProductHelper.createProductRequest();

        var productEntity = ProductHelper.createProductEntity();

        var productEntityUpdated = ProductMapper.updateProductEntityWithProductRequest(productEntity, productRequest);

        Assertions.assertNotNull(productEntityUpdated);
        Assertions.assertEquals(productRequest.getName(), productEntity.getName());
        Assertions.assertEquals(productRequest.getDescription(), productEntity.getDescription());
        Assertions.assertEquals(productRequest.getPrice(), productEntity.getPrice());
    }
}

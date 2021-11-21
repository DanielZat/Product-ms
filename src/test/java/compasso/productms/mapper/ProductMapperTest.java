package compasso.productms.mapper;

import compasso.productms.entity.ProductEntity;
import compasso.productms.helper.ProductHelper;
import compasso.productms.request.ProductRequest;
import compasso.productms.response.ProductResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ProductMapperTest {

    @Test
    public void shouldConvertRequestToEntitySuccessfully() {

        ProductRequest productRequest = ProductHelper.createProductRequest();

        ProductEntity productEntity =  ProductMapper.convertRequestToEntity(productRequest);

        Assertions.assertNotNull(productEntity);
        Assertions.assertEquals(productRequest.getName(), productEntity.getName());
        Assertions.assertEquals(productRequest.getDescription(), productEntity.getDescription());
        Assertions.assertEquals(productRequest.getPrice(), productEntity.getPrice());
    }

    @Test
    public void shouldConvertEntityToResponseSuccessfully() {

        ProductEntity productEntity =  ProductHelper.createProductEntity();

        ProductResponse productResponse = ProductMapper.convertEntityToResponse(productEntity);

        Assertions.assertNotNull(productResponse);
        Assertions.assertEquals(productEntity.getId(), productResponse.getId());
        Assertions.assertEquals(productEntity.getName(), productResponse.getName());
        Assertions.assertEquals(productEntity.getDescription(), productResponse.getDescription());
        Assertions.assertEquals(productEntity.getPrice(), productResponse.getPrice());
    }

    @Test
    public void shouldUpdateProductEntityWithProductRequestSuccessfully() {

        ProductRequest productRequest =  ProductHelper.createProductRequest();

        ProductEntity productEntity = ProductHelper.createProductEntity();

        ProductEntity productEntityUpdated = ProductMapper.updateProductEntityWithProductRequest(productEntity, productRequest);

        Assertions.assertNotNull(productEntityUpdated);
        Assertions.assertEquals(productRequest.getName(), productEntity.getName());
        Assertions.assertEquals(productRequest.getDescription(), productEntity.getDescription());
        Assertions.assertEquals(productRequest.getPrice(), productEntity.getPrice());
    }
}

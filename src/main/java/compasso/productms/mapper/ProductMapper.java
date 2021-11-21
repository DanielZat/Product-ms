package compasso.productms.mapper;

import compasso.productms.entity.ProductEntity;
import compasso.productms.request.ProductRequest;
import compasso.productms.response.ProductResponse;

public class ProductMapper {

    public static ProductResponse convertEntityToResponse(ProductEntity productEntity) {
        return ProductResponse.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .build();
    }

    public static ProductEntity convertRequestToEntity(String id, ProductRequest productRequest) {
        return ProductEntity.builder()
                .id(id)
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }

    public static ProductEntity convertRequestToEntity(ProductRequest productRequest) {
        return ProductEntity.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }

    public static ProductEntity updateProductEntityWithProductRequest(ProductEntity productEntity, ProductRequest productRequest) {

        productEntity.setName(productRequest.getName());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPrice(productRequest.getPrice());

        return productEntity;
    }
}

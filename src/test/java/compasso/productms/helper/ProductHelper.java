package compasso.productms.helper;

import compasso.productms.entity.ProductEntity;
import compasso.productms.request.ProductRequest;
import compasso.productms.response.ProductResponse;

public class ProductHelper {

    public static ProductRequest createProductRequest() {
        return ProductRequest.builder().name("Arroz").description("Arroz Tio João").price(7.50).build();
    }

    public static ProductResponse createProductResponse() {
        return ProductResponse.builder().id("6199a4ba8cdb5f7e50643648").name("Feijão").description("Feijão namorado").price(7.50).build();
    }

    public static ProductEntity createProductEntity() {
        return ProductEntity.builder().id("6199a4ba8cdb5f7e50643648").name("Feijão").description("Feijão namorado").price(7.50).build();
    }
}

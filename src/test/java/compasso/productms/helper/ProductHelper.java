package compasso.productms.helper;

import java.util.Arrays;
import java.util.List;

import compasso.productms.entity.ProductEntity;
import compasso.productms.request.ProductRequest;
import compasso.productms.response.ProductResponse;

public class ProductHelper {

    public static ProductRequest createProductRequest() {
        return ProductRequest.builder().name("Feijão").description("Feijão namorado").price(7.50).build();
    }

    public static ProductRequest createCustomProductRequest(String name, String description, Double price) {
        return ProductRequest.builder().name(name).description(description).price(price).build();
    }

    public static ProductResponse createProductResponse() {
        return ProductResponse.builder().id("6199a4ba8cdb5f7e50643648").name("Feijão").description("Feijão namorado").price(7.50).build();
    }

    public static ProductEntity createProductEntity() {
        return ProductEntity.builder().id("6199a4ba8cdb5f7e50643648").name("Feijão").description("Feijão namorado").price(7.50).build();
    }

    public static List<ProductEntity> createListProductEntity() {
        return Arrays.asList(ProductEntity
                .builder()
                .id("6199a4ba8cdb5f7e50643648")
                .name("Feijão")
                .description("Feijão namorado")
                .price(7.50)
                .build(), ProductEntity
                        .builder()
                        .id("6199a4ba8cdb5f7e50643432")
                        .name("Arroz")
                        .description("Arroz Tio João")
                        .price(7.50)
                        .build());
    }
}

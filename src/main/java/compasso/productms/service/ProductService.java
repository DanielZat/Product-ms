package compasso.productms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import compasso.productms.exception.NotFoundException;
import compasso.productms.mapper.ProductMapper;
import compasso.productms.repository.ProductMongoTemplateRepository;
import compasso.productms.repository.ProductRepository;
import compasso.productms.request.ProductRequest;
import compasso.productms.request.SearchProductRequest;
import compasso.productms.response.ProductResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMongoTemplateRepository productMongoTemplateRepository;

    private final ValidationService validationService;

    public Mono<ProductResponse> createProduct(ProductRequest productRequest) {

        validationService.validate(productRequest);

        return productRepository.save(ProductMapper.convertRequestToEntity(productRequest))
                .map(ProductMapper::convertEntityToResponse);
    }

    public Mono<ProductResponse> updateProduct(String id, ProductRequest productRequest) {

        validationService.validate(productRequest);

        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Produto não foi localizado")))
                .map(productEntity -> ProductMapper.updateProductEntityWithProductRequest(productEntity, productRequest))
                .flatMap(productRepository::save)
                .map(ProductMapper::convertEntityToResponse);
    }

    public Mono<ProductResponse> findProductById(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Produto não foi localizado")))
                .map(ProductMapper::convertEntityToResponse);
    }

    public Flux<ProductResponse> getProducts() {
        return productRepository.findAll().map(ProductMapper::convertEntityToResponse);
    }

    public Flux<ProductResponse> searchProducts(SearchProductRequest searchProductRequest) {

        return productMongoTemplateRepository.searchProducts(searchProductRequest)
                .map(ProductMapper::convertEntityToResponse);
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Produto não foi localizado")))
                .flatMap(productRepository::delete);
    }
}

package compasso.productms.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import compasso.productms.request.ProductRequest;
import compasso.productms.request.SearchProductRequest;
import compasso.productms.response.ProductResponse;
import compasso.productms.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/products")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso", content = @Content(schema = @Schema(
                    implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Campos inválidos no cadastro de Produto")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = @Content(schema = @Schema(
                    implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Campos inválidos no cadastro de Produto"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public Mono<ProductResponse> updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto localizado com sucesso", content = @Content(schema = @Schema(
                    implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public Mono<ProductResponse> findProductById(@PathVariable("id") String id) {
        return productService.findProductById(id);
    }

    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos localizados com sucesso", content = @Content(schema = @Schema(
                    implementation = ProductResponse.class)))
    })
    public Flux<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos localizados com sucesso", content = @Content(schema = @Schema(
                    implementation = ProductResponse.class)))
    })
    public Flux<ProductResponse> searchProducts(SearchProductRequest searchProductRequest) {
        return productService.searchProducts(searchProductRequest);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public Mono<Void> deleteProduct(@PathVariable("id") String id) {
        return productService.deleteProduct(id);
    }
}

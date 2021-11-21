package compasso.productms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import compasso.productms.entity.ProductEntity;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {

}

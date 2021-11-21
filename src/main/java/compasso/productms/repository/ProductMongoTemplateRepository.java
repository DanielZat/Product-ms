package compasso.productms.repository;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import compasso.productms.entity.ProductEntity;
import compasso.productms.request.SearchProductRequest;

import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class ProductMongoTemplateRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<ProductEntity> searchProducts(SearchProductRequest request) {

        Query query = new Query();

        addCriteriaNameOrDescription(query, request.getQ());
        addCriteriaPrice(query, request.getMin_price(), request.getMax_price());

        return mongoTemplate.find(query, ProductEntity.class);
    }

    public void addCriteriaNameOrDescription(Query query, String q) {

        if (StringUtils.isNotEmpty(q)) {
            query.addCriteria(new Criteria().orOperator(Criteria.where("name").regex(q), Criteria.where("description").regex(q)));
        }
    }

    public void addCriteriaPrice(Query query, Double minPrice, Double maxPrice) {

        if (minPrice != null && maxPrice == null) {
            query.addCriteria(Criteria.where("price").gte(minPrice));
        }

        if (minPrice == null && maxPrice != null) {
            query.addCriteria(Criteria.where("price").lte(maxPrice));
        }

        if (minPrice != null && maxPrice != null) {
            query.addCriteria(Criteria.where("price").gte(minPrice).lte(maxPrice));
        }
    }
}

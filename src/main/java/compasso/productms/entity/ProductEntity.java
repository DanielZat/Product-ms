package compasso.productms.entity;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Builder
@Data
@Document(collection = "PRODUCT")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ProductEntity {

    @Id
    private String id;

    private String name;

    private String description;

    private Double price;
}

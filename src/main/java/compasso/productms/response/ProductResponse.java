package compasso.productms.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductResponse {

    private String id;

    private String name;

    private String description;

    private Double price;

}

package compasso.productms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SearchProductRequest {

    private String q;

    private Double min_price;

    private Double max_price;
}

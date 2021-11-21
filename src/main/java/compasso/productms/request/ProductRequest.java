package compasso.productms.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "O campo name é obrigatório")
    private String name;

    @NotBlank(message = "O campo description é obrigatório")
    private String description;

    @NotNull(message = "O campo price é obrigatório")
    @Positive(message = "O campo price deve ser positivo")
    private Double price;
}

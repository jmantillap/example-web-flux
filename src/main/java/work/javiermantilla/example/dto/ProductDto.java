package work.javiermantilla.example.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    @NotBlank(message = "name is mandatory")
    private String name;
    @Min(value = 1, message = "price must be greater then zero")
    private float price;

}
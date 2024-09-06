package work.javiermantilla.example.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class Product {
    
	@Id
	@Column(value = "id")
    private int id;
	@Column(value = "name")
    private String name;
	@Column(value = "price")
    private float price;

}
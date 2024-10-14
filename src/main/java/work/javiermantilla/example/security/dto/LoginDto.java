package work.javiermantilla.example.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
	@NotBlank(message = "username is mandatory")
	private String username;
	@NotBlank(message = "password is mandatory")
	private String password;
}

package work.javiermantilla.example.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserDto {
	@NotBlank(message = "username is mandatory")
	private String username;
	@NotBlank(message = "email is mandatory")
	@Email(message = "invalid email")
	private String email;
	@NotBlank(message = "password is mandatory")
	private String password;
}
package work.javiermantilla.example.security.entity;

import java.util.Collection;
import java.util.stream.Stream;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User implements UserDetails {
	
	private static final long serialVersionUID = -6129566609472206517L;
	private int id;
	private String username;
	public String email;
	@JsonIgnore
	private String password;
	private String roles;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Stream.of(roles.split(", "))
				.map(SimpleGrantedAuthority::new)
                .toList();
	}
	@Override
	public String getPassword() {		
		return this.password;
	}
	@Override
	public String getUsername() {		
		return this.username;
	}
}

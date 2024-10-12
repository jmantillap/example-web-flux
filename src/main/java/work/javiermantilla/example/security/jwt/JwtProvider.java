package work.javiermantilla.example.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtProvider {
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;

	@SuppressWarnings("deprecation")
	public String generateToken(UserDetails userDetails) {
		
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.claim("roles", userDetails.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(getSecretKey(secret))
				.compact();
	}

	public boolean validate(String token) {
		try {
			//Jwts.parser().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody();
			Jwts.parser().verifyWith(getSecretKey(secret)).build().parseSignedClaims(token).getPayload();
			return true;
		} catch (ExpiredJwtException e) {
			log.error("token expired");
		} catch (UnsupportedJwtException e) {
			log.error("token unsupported");
		} catch (MalformedJwtException e) {
			log.error("token malformed");
		} catch (SignatureException e) {
			log.error("bad signature");
		} catch (IllegalArgumentException e) {
			log.error("illegal args");
		}
		return false;
	}

	public Claims getClaims(String token) {
		return Jwts.parser().verifyWith(getSecretKey(secret)).build().parseSignedClaims(token).getPayload();
	}

	@SuppressWarnings("deprecation")
	public Claims getClaimsDeprecade(String token) {
		return Jwts.parser().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody();
	}

	private Key getKey(String secret) {
		byte[] secretBytes = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(secretBytes);
	}

	private SecretKey getSecretKey(String secret) {
		byte[] secretBytesKey = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(secretBytesKey);
	}

}

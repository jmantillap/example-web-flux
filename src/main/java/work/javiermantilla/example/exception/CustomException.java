package work.javiermantilla.example.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception{

    
	private static final long serialVersionUID = -3493357424176022599L;
	
	private final HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
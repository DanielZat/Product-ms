package compasso.productms.exception;

import java.util.List;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ValidationException extends ResponseStatusException {

    private Integer statusCode;

    private String message;

    public ValidationException(List<String> errors) {
        super(HttpStatus.BAD_REQUEST);
        statusCode = HttpStatus.BAD_REQUEST.value();
        message = errors.toString();
    }
}

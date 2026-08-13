package br.com.alessandro.countryapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCountryNotFound(
            CountryNotFoundException exception) {

        return new ErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgument(
            IllegalArgumentException exception) {

        return new ErrorResponse(
                400,
                exception.getMessage(),
                LocalDateTime.now()
        );
    }

    public record ErrorResponse(
            int status,
            String message,
            LocalDateTime timestamp
    ) {
    }
}
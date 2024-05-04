package postech.soat.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errorList = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new ApiError(error.getDefaultMessage(), error.getObjectName()))
                .toList();

        return new ApiResponse<>(errorList);
    }
}

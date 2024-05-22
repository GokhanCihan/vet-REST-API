package gokhancihan.vet.utility.config;

import gokhancihan.vet.utility.Result;
import gokhancihan.vet.utility.exception.BadRequestException;
import gokhancihan.vet.utility.exception.InvalidRequestParameterException;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.exception.RedundantDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RedundantDataException.class)
    public ResponseEntity<Result> handleRedundantDataException(RedundantDataException e) {
        Result result = new Result(false, "Bad Request", "400", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Result> handleBadRequestException(BadRequestException e) {
        Result result = new Result(false, "Bad Request", "400", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestParameterException.class)
    public ResponseEntity<Result> handleInvalidRequestParameterException(InvalidRequestParameterException e) {
        Result result = new Result(false, "Validation Error", "400", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        Result result = new Result(false, "Resource Not Found", "404", e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> validationErrors = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());
        Result result = new Result(false, "Validation Error", "400", validationErrors);
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}

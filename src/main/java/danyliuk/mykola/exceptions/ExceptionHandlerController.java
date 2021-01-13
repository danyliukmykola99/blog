package danyliuk.mykola.exceptions;

import java.io.Serializable;
import javax.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Реалізація обробника помилок
 */
@Log4j2
@ControllerAdvice
@RestController
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Data
    @AllArgsConstructor
    private static class ErrorDetails implements Serializable {
        String message;
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDetails handleNotFound(NotFoundException e){
        return new ErrorDetails(e.getMessage());
    }

    @ExceptionHandler(value = ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public final ErrorDetails handleForbidden(ForbiddenException e){
        return new ErrorDetails(e.getMessage());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ErrorDetails handleUnauthorized(UnauthorizedException e){
        return new ErrorDetails(e.getMessage());
    }

    @ExceptionHandler(value = {BadRequestException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDetails handleBadRequest(RuntimeException e){
        return new ErrorDetails(e.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ErrorDetails handleBadCredentials(AuthenticationException e){
        return new ErrorDetails(e.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, InvalidFormatException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorDetails handleInternal(IllegalArgumentException e){
        log.info(e);
        return new ErrorDetails(e.getMessage());
    }

    @ExceptionHandler(value = DuplicatedDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final ErrorDetails handleConflict(DuplicatedDataException e){
        log.info(e);
        return new ErrorDetails(e.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        ErrorDetails apiErrorVO = new ErrorDetails(errorMessage);
        return ResponseEntity.badRequest().body(apiErrorVO);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getCause().getMessage();
        ErrorDetails apiErrorVO = new ErrorDetails(errorMessage);
        return ResponseEntity.badRequest().body(apiErrorVO);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info(e);
        return ResponseEntity.ok(new ErrorDetails(e.getMessage()));
    }
}

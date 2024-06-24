package br.forsign.allo.handler;

import br.forsign.allo.common.error.BusinessException;
import br.forsign.allo.common.error.rest.APIErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@CommonsLog
@RestControllerAdvice
public class ControllerExceptionHandler {

    final String CAMPOS_INVALIDOS_MSG = "Campos inválidos.";

    @ExceptionHandler({
            NoSuchElementException.class,
            EntityNotFoundException.class,
            EmptyResultDataAccessException.class
    })
    public ResponseEntity<APIErrorResponse> handleOptionalNotFoundException(Exception ex, WebRequest request) {
        return this.handleError(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIErrorResponse> handleRuntimeException(DataIntegrityViolationException ex, WebRequest request) {
        return this.handleError(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIErrorResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return this.handleError(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    @ExceptionHandler({
            BusinessException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<APIErrorResponse> handleBusinessException(Exception ex, WebRequest request) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler({
            ValidationException.class
    })
    public ResponseEntity<APIErrorResponse> handleValidationException(Exception ex, WebRequest request) {
        return this.handleError(ex.getCause().getMessage(), request);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<APIErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        return this.getMethodArgumentoNotValidResponse(HttpStatus.BAD_REQUEST, ex, request);
    }

    private ResponseEntity<APIErrorResponse> handleError(String exMessage, WebRequest request) {
        log.error(exMessage);
        List<String> errors = new ArrayList<>();
        errors.add(exMessage);
        return this.getErrorResponse(request, errors);
    }

    private ResponseEntity<APIErrorResponse> getErrorResponse(WebRequest request, List<String> errors) {
        APIErrorResponse body = new APIErrorResponse(
                CAMPOS_INVALIDOS_MSG,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                LocalDateTime.now(),
                request.getDescription(false),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    private ResponseEntity<APIErrorResponse> getErrorResponse(HttpStatus status, String exMessage, WebRequest request) {
        APIErrorResponse body = new APIErrorResponse(
                exMessage,
                status.value(),
                status.getReasonPhrase(),
                LocalDateTime.now(),
                request.getDescription(false),
                new ArrayList<>()
        );
        return ResponseEntity.status(status).body(body);
    }

    private ResponseEntity<APIErrorResponse> getMethodArgumentoNotValidResponse(HttpStatus status, MethodArgumentNotValidException ex, WebRequest request) {
        final List<String> erros = getMethodArgumentNotValidMessage(status, ex, request);
        APIErrorResponse body = new APIErrorResponse(
                CAMPOS_INVALIDOS_MSG,
                status.value(),
                status.getReasonPhrase(),
                LocalDateTime.now(),
                request.getDescription(false),
                erros
        );
        return ResponseEntity.status(status).body(body);
    }

    private List<String> getMethodArgumentNotValidMessage(HttpStatus status, MethodArgumentNotValidException e, WebRequest request) {
        if (StringUtils.isEmpty(e.getBindingResult().getFieldErrors ().toString())) {
            return new ArrayList<>();
        }
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ControllerExceptionHandler::formatError)
                .collect(Collectors.toList());
    }

    private ResponseEntity<APIErrorResponse> handleError(HttpStatus status, Throwable ex, WebRequest request) {
        return this.handleError(status, ex.getMessage(), request);
    }

    private ResponseEntity<APIErrorResponse> handleError(HttpStatus status, String exMessage, WebRequest request) {
        log.error(exMessage);
        return this.getErrorResponse(status, exMessage, request);
    }

    private static String formatError(FieldError error) {
        StringBuilder builder = new StringBuilder();
        if(!StringUtils.isEmpty(error.getDefaultMessage())){
            builder.append(error.getDefaultMessage());
        }
        if(!StringUtils.isEmpty(error.getField())){
            builder.append(" - ").append(error.getField());
        }
        if(error.getRejectedValue() != null && !StringUtils.isEmpty(error.getRejectedValue().toString())){
            builder.append(" - ").append(error.getRejectedValue());
        }
        return builder.toString();
    }
}

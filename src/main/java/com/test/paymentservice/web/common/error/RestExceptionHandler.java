package com.test.paymentservice.web.common.error;

import com.test.paymentservice.domain.exception.InsufficientFundException;
import com.test.paymentservice.domain.exception.NotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InsufficientFundException.class})
    public ResponseEntity<ApiError> handleAlreadyExist(final InsufficientFundException ex) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ApiError> handleNotFound(final NotFoundException ex) {
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
        final String error = "%s should be of type %s".formatted(
                ex.getName(),
                Objects.requireNonNull(ex.getRequiredType()).getName()
        );

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleConstraintViolation(final ConstraintViolationException ex) {
        final List<String> errors = ex.getConstraintViolations().stream()
                .map(v -> v.getRootBeanClass().getName() + "#" + v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.toList());

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors.toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> handleAll(final Exception ex) {
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        return getObjectResponseEntity(ex, headers, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
            final BindException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        return getObjectResponseEntity(ex, headers, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            final TypeMismatchException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        final String error = "%s value for %s should be of type %s".formatted(
                ex.getValue(),
                ex.getPropertyName(),
                ex.getRequiredType()
        );

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            final MissingServletRequestPartException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        final String error = "%s part is missing".formatted(ex.getRequestPartName());

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            final MissingServletRequestParameterException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        final String error = "%s parameter is missing".formatted(ex.getParameterName());

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            final NoHandlerFoundException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        final String error = "No handler found for %s %s".formatted(ex.getHttpMethod(), ex.getRequestURL());

        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            final HttpRequestMethodNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        final StringBuilder errorBuilder = new StringBuilder();
        errorBuilder.append(ex.getMethod());
        errorBuilder.append(" method is not supported for this request. Supported methods are ");
        Objects.requireNonNull(ex.getSupportedHttpMethods()).forEach(t -> errorBuilder.append(t).append(" "));

        final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, errorBuilder.toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            final HttpMediaTypeNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        final StringBuilder errorBuilder = new StringBuilder();
        errorBuilder.append(ex.getContentType());
        errorBuilder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> errorBuilder.append(t).append(" "));

        final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, errorBuilder.toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getCode());
    }

    private ResponseEntity<Object> getObjectResponseEntity(BindException ex, HttpHeaders headers, WebRequest request) {
        final List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(e
                -> errors.add(e.getField() + ": " + e.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(e
                -> errors.add(e.getObjectName() + ": " + e.getDefaultMessage()));

        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors.toString());
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }
}

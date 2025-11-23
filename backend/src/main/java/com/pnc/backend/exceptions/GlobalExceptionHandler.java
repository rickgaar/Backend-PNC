package com.pnc.backend.exceptions;


import com.pnc.backend.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, errors);
    }

    public ResponseEntity<ApiErrorResponse> buildErrorResponse(Exception e, HttpStatus status, Object data){
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status).body(ApiErrorResponse.builder()
                .message(data).status(status.value()).time(LocalDate.now()).uri(uri)
                .build());
    }

    @ExceptionHandler(MateriaNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleMateriaNotFoundException(MateriaNotFoundException e){
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(TemaNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleTemaNotFoundException(TemaNotFoundException e){
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleUserNotFoundException(UserNotFoundException e){
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleDuplicatedFieldException(DuplicatedFieldException e){
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ConcurrentModificationException.class)
    public ResponseEntity<ApiErrorResponse> handleConcurrentModificationException(ConcurrentModificationException e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, e.getMessage());
    }

    @ExceptionHandler(PreguntaNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlePreguntaNotFoundException(PreguntaNotFoundException e){
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UserXExamNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserXExamNotFoundException(UserXExamNotFoundException e){
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }
    @ExceptionHandler(RespuestaNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleRespuestaNotFoundException(RespuestaNotFoundException e){
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }
    @ExceptionHandler(ExamenNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleExamenNotFoundException(ExamenNotFoundException e){
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

}

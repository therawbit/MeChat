package com.therawbit.MM;


import com.therawbit.MM.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MMHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse> handler(Exception e){
        return new ResponseEntity<>(new ApiResponse(e.getLocalizedMessage(), false), HttpStatus.BAD_REQUEST);
    }
}

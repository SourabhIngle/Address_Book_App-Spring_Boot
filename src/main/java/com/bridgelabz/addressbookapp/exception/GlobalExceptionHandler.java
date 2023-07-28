package com.bridgelabz.addressbookapp.exception;

import com.bridgelabz.addressbookapp.DTO.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errMsg = errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("Exception while performingREST API",errMsg);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(com.bridgelabz.addressbookapp.exception.CustomException.class)
    public ResponseEntity<ResponseDTO> handleCustomException(com.bridgelabz.addressbookapp.exception.CustomException customException){
        ResponseDTO responseDTO = new ResponseDTO("Exception while performing REST API",customException.getMessage());
        return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
    }
}

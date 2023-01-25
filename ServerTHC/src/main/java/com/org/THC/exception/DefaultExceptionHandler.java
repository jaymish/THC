package com.org.THC.exception;

import com.org.THC.THCApplication;
import com.org.THC.model.ErrorExceptionMessage;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {


    //private static final Logger logger = LogManager.getLogger(THCApplication.class);
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorExceptionMessage> errorMessageResponseEntity(Exception exception){
        ErrorExceptionMessage errorMessage=new ErrorExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"Error", exception.getMessage());
        //logger.error("Exception:"+exception);
        return new ResponseEntity<ErrorExceptionMessage>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



package com.org.THC.exception;

import com.org.THC.THCApplication;
import com.org.THC.model.ErrorExceptionMessage;
import lombok.Data;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {


    private static final Logger logger = LogManager.getLogger(THCApplication.class);
    @ExceptionHandler(Exception.class)
    public final String errorMessageResponseEntity(Exception exception){
        ErrorExceptionMessage errorMessage=new ErrorExceptionMessage(exception.getMessage());
        ResponseEntity<ErrorExceptionMessage> errorExceptionMessageResponseEntity= new ResponseEntity<ErrorExceptionMessage>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
        //log errorExceptionMessageResponseEntity and return user to default page
        logger.error("Exception:"+exception);

        return "redirect:/error";
    }
}



package com.java.exam;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExtHandler {

	@ExceptionHandler(value=BaseException.class)
	ResponseEntity handleException(BaseException e,HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();

	        switch (e.getErrorCode()) {
        		case "201":
        			return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.CREATED);
	        	case "400":
	    	        return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
	        	case "401":
	    	        return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.UNAUTHORIZED);
	        	case "403":
	    	        return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.FORBIDDEN);
	        	case "404":
	    	        return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.NOT_FOUND);
	        }
	        
			return null;
    }
}

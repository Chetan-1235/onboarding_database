package com.onboard.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.onboard.util.Response;




@ControllerAdvice
public class GlobalOnboardingException 
{
	@ExceptionHandler
	public ResponseEntity<Response> hadlingVendorStatusException(OnboardingStatusException e ){
		return new ResponseEntity<Response>(Response.builder().errorCode(e.errorMessage).errorMsg
				("Because Status of Vendor is "+e.errorCode.toString()).build(), HttpStatus.EXPECTATION_FAILED);
	}

}
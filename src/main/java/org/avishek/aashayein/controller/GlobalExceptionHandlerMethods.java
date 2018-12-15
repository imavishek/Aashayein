/**
 * @Project_Name Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.exception;
 * @File_Name GlobalExceptionHandlerMethods.java
 * @Created_Date 17-Oct-2018
 * Modified by @author avishekdas last on 2018-10-17 00:30:23
 */

package org.avishek.aashayein.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlerMethods {

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e) {
		String view = "";
		view = "error";
		//Log the exception
		return view;
		
	}
}

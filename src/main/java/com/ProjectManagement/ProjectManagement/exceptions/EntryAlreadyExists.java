package com.ProjectManagement.ProjectManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Always all custom/user-defined exceptions must inherit from either Exception or RuntimeException
//If your class extends from Exception, it becomes checked/compile-time exception
//If your class extends from RuntimeException, it becomes unchecked/runtime exception
@ResponseStatus(HttpStatus.CONFLICT)
public class EntryAlreadyExists  extends RuntimeException{
	public EntryAlreadyExists(String message) {
		super(message);
	}
}

package com.ProjectManagement.ProjectManagement.exceptions;

import java.util.List;

public class ErrorHandling {
	private String message;
	private List<String> details;

	public ErrorHandling(String message, List<String> details)
	{
		this.setMessage(message); this.setDetails(details);
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

package com.intuit.tms.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.intuit.tms.services.ErrorBuilderService;

@Service
public class ErrorBuilderServiceImpl implements ErrorBuilderService {

	@Override
	public String generateErrorMessage(BindingResult result) {
		StringBuilder errorMessage = new StringBuilder();

		for (FieldError fieldError : result.getFieldErrors()) {
			if (errorMessage.toString().isEmpty()) {
				errorMessage.append(fieldError.getField() + ": " + fieldError.getDefaultMessage());
			} else {
				errorMessage.append(", " + fieldError.getField() + ": " + fieldError.getDefaultMessage());
			}
		}
		return errorMessage.toString();
	}
}

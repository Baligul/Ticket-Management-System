package com.intuit.tms.services;

import org.springframework.validation.BindingResult;

public interface ErrorBuilderService {
	String generateErrorMessage(BindingResult result);
}
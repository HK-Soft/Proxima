package com.proximar.exception;

import org.springframework.validation.BindingResult;

import com.proximar.web.form.RegistrationForm;

@SuppressWarnings("serial")
public class RegistrationException extends Exception {

	private RegistrationForm registrationForm;

	private BindingResult bindingResult;

	public RegistrationException(RegistrationForm registrationForm, BindingResult bindingResult) {
		this.setRegistrationForm(registrationForm);
		this.setBindingResult(bindingResult);
	}

	public RegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

	public String getMessage() {
		return super.getMessage() + 
					"\n\t Form Data : " + registrationForm.toString() + 
					"\n\t Errors List : " + bindingResult.getAllErrors();
	}

}

package com.proximar.exception;

import org.springframework.validation.BindingResult;

import com.proximar.core.dto.AccountDTO;

@SuppressWarnings("serial")
public class RegistrationException extends Exception {

	private AccountDTO accountDTO;

	private BindingResult bindingResult;

	public RegistrationException(AccountDTO accountDTO, BindingResult bindingResult) {
		this.setAccountDTO(accountDTO);
		this.setBindingResult(bindingResult);
	}

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

	public String getMessage() {
		return super.getMessage() + "\n\t Form Data : " + accountDTO.toString() + "\n\t Errors List : "
				+ bindingResult.getAllErrors();
	}

}

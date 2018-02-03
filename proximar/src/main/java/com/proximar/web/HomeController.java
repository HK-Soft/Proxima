package com.proximar.web;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proximar.core.dto.AccountDTO;
import com.proximar.core.model.User;
import com.proximar.exception.RegistrationException;
import com.proximar.services.userServices.UserServices;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class.getName());

	// User login
	@GetMapping("/login")
	public String showLoginPage() {
		return "login-form";
	}

	// New account
	@GetMapping({ "/create-an-account" })
	public String showSignupForm(@ModelAttribute AccountDTO accountDTO) {
		return "signup-form";
	}

	@PostMapping({ "/create-an-account" })
	public String doSignupForm(Model model, @Valid AccountDTO accountDTO, BindingResult bindingResult)
			throws RegistrationException {

		if (bindingResult.hasErrors()) {
			logger.warn("Invalid registration form : " + accountDTO.toString() + "Reasons : "
					+ bindingResult.getAllErrors());
			return "signup-form";
		}

		User user = null;

		try {
			user = userServices.create(accountDTO.getUsername(), passwordEncoder.encode(accountDTO.getPassword()));
		} catch (Exception ex) {
			throw new RegistrationException(accountDTO, bindingResult);
		}

		logger.info(
				"Account created : ID {" + user.getUuid().toUpperCase() + "}{" + user.getAccount().getUsername() + "}");

		return "login-form";
	}

	// Index Page
	@GetMapping({ "/", "/dashboard", "/accueil" })
	public String showDashboard() {
		return "main-dashboard";
	}

	@ExceptionHandler(RegistrationException.class)
	public String registrationExceptionHandler(RegistrationException ex) {
		logger.error("Failed registration : " + ex.getMessage());
		return null;
	}

	@Autowired
	private UserServices userServices;

	@Autowired
	private PasswordEncoder passwordEncoder;

}

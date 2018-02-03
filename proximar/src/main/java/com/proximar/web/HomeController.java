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

import com.proximar.core.model.User;
import com.proximar.exception.RegistrationException;
import com.proximar.services.userServices.UserServices;
import com.proximar.web.form.RegistrationForm;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class.getName());

	@GetMapping("/login")
	public String showLoginPage() {
		return "login-form";
	}

	@GetMapping({ "/create-an-account" })
	public String showSignupForm(@ModelAttribute RegistrationForm registrationForm) {
		return "signup-form";
	}

	@PostMapping({ "/create-an-account" })
	public String doSignupForm(Model model, @Valid RegistrationForm registrationForm, BindingResult bindingResult)
			throws RegistrationException {

		if (bindingResult.hasErrors()) {
			logger.warn("Invalid registration form : " + registrationForm.toString() + "Reasons : "
					+ bindingResult.getAllErrors());
			return "signup-form";
		}

		User user = null;

		try {
			user = userServices.create(registrationForm.getUsername(),
					passwordEncoder.encode(registrationForm.getPassword()));
		} catch (Exception ex) {
			throw new RegistrationException(registrationForm, bindingResult);
		}

		logger.info("New user was created " + user.getUuid());

		return "login-form";
	}

	@ExceptionHandler(RegistrationException.class)
	public String registrationExceptionHandler(RegistrationException ex) {
		logger.error("Failed registration : " + ex.getMessage());
		return null;
	}

	@GetMapping({ "/", "/dashboard","/accueil" })
	public String showDashboard() {
		return "main-dashboard";
	}

	@Autowired
	private UserServices userServices;

	@Autowired
	private PasswordEncoder passwordEncoder;

}

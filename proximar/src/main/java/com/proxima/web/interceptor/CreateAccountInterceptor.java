package com.proxima.web.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.proxima.services.accountServices.AccountServices;

public class CreateAccountInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {

		if (noAccount() && !request.getServletPath().equalsIgnoreCase("/create-an-account")) { // avoid redirection
																								// infinite loop!
			redirect(request, response, "/create-an-account");
			return false; // request handled, no need to bother controller
		}

		return true;
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException {
		try {
			response.sendRedirect(request.getContextPath() + path);
		} catch (java.io.IOException e) {
			throw new ServletException(e);
		}
	}

	private boolean noAccount() {
		return (accountServices.countAccount() == 0);
	}

	@Autowired
	private AccountServices accountServices;
}
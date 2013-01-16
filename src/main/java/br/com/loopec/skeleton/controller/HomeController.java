package br.com.loopec.skeleton.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.loopec.skeleton.persistence.entity.User;
import br.com.loopec.skeleton.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/**
	 * Simply returns a String which is shown in the browser.
	 */
	@RequestMapping("responseBody")
	public @ResponseBody String responseBody() {
		return "An arbirtrary String.";
	}
	
	/**
	 * Simply selects the i18n view ("i18n.html" file) to show the usage of internacionalization messages.
	 */
	@RequestMapping("i18n")
	public String i18n() {
		return "i18n";
	}
	
	/**
	 * Simple Form Handling Example.
	 * 
	 * The code below is necessary if you want to take user input from a web form and 
	 * persist the information. It's a good example of how Spring works because data goes through all the 
	 * layers of the application.
	 */
	
	/* Constructor injection */
	private UserService userService;
	
	@Inject
	public HomeController(UserService userService) {
		this.userService = userService;
	}
	
	/* 
	 * The method below simply adds a User into the Model (making it available to the view layer) and 
	 * returns the form page. The User we've just added is used as the form backing bean. Thymeleaf 
	 * automatically figures out how to bind the values if the attributes are Strings. For more 
	 * advanced uses, as binding to Date objects, take a look at the "Advanced Form Handling Example" 
	 * or refer to "Thymeleaf + Spring 3" tutorial (Property Editors section) available at:
	 * http://www.thymeleaf.org/documentation.html
	 */
	@RequestMapping(value = "simpleForm", method = RequestMethod.GET)
	public String showSimpleForm(User user) {
		//model.addAttribute("user", new User()); // Adding a parameter "User user" is the same as doing this!
		return "simpleForm";
	}
	
	/*
	 * Here we handle the form submission. The input is validated against Hibernate constraints defined 
	 * in the entity class User (@Valid annotation). If any error occur, they're reported in the 
	 * "bindingResult" variable and are shown in the view. If input is valid, we try to persist the new 
	 * user through the UserService object method: addUser().
	 */
	@RequestMapping(value = "simpleForm", method = RequestMethod.POST)
	public String submitSimpleForm(Model model, @Valid User user, BindingResult bindingResult, 
			HttpServletResponse response) {
		if(bindingResult.hasErrors()) {
			return "simpleForm";
		}
				
		try {
			userService.addUser(user);
		} catch(Exception e) {
			bindingResult.addError(new ObjectError("Exception", "An error occurred while adding user."));
			return "simpleForm";
		}
		
		final String statusMessage = "User successfully added!"; // This message'd better being in a .properties file.
		model.addAttribute("status", statusMessage);
		return "simpleForm"; 
	}
	

	/**
	 * Advanced Form Handling Example.
	 */
	
	@RequestMapping(value = "advancedForm", method = RequestMethod.GET)
	public String showAdvancedForm(Model model) {
		model.addAttribute("user", new User());
		return "advancedForm";
	}

	@RequestMapping(value = "advancedForm", method = RequestMethod.POST)
	public String submitAdvancedForm(Model model, @Valid User user, BindingResult bindingResult, 
			HttpServletResponse response) {
		if(bindingResult.hasErrors()) {
			return "advancedForm";
		}
				
		try {
			userService.addUser(user);
		} catch(Exception e) {
			bindingResult.addError(new ObjectError("Exception", "An error occurred while adding user."));
			return "advancedForm";
		}
		
		final String statusMessage = "User successfully added!"; // This message'd better being in a .properties file.
		model.addAttribute("status", statusMessage);
		return "advancedForm"; 
	}
	
}

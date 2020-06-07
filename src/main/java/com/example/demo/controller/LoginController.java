package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping(value= {"/","/login"},method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	@RequestMapping(value= "/registration",method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user",user);
		model.setViewName("registration");
		return model;
	}
	@RequestMapping(value= "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user,BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userExists = userService.findUserByUserName(user.getUserName());
		if(userExists != null) {
			bindingResult
					.rejectValue("userName", "error.user","There is already a user registered with the user name provided");
			
		}
		if(bindingResult.hasErrors()) {
			model.setViewName("registration");
		}else {
			userService.saveUser(user);
			model.addObject("successMessage","User has been registered successfully");
			model.addObject("user",new User());
			model.setViewName("registration");
		}
		return model;
	}
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		model.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        model.addObject("adminMessage","Content Available Only for Users with Admin Role");
        model.setViewName("admin/home");
        return model;
	}
}

package com.example.catering.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	
	@GetMapping("/index")
	public String index(Model model) {
			return "index.html";
	}
	
	@GetMapping("/admin/home")
    public String getHomeAdmin(Model model) {
		return "adminHome.html";
    }
    
}
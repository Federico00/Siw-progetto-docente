package com.example.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.catering.model.Buffet;
import com.example.catering.model.Chef;
import com.example.catering.service.ChefService;
import com.example.catering.validator.ChefValidator;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private ChefValidator validator;
	
	@GetMapping("/admin/chef")
	public String newChef( Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm.html";
	}
	
	@PostMapping("/admin/chef")
	public String getChef(@Valid @ModelAttribute("chef") Chef chef, Model model, BindingResult bindingResult) {
		this.validator.validate(chef, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.chefService.add(chef);
			model.addAttribute("chef", chef);
			return "chef.html";
		}
		return "chefForm.html";
	}
	
	@GetMapping("/chefAll")
	public String getBuffets(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefAll", chefs);
		return "elencoChef.html";
	}
	
	@GetMapping("/chefAll/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", chefService.findById(id));
		model.addAttribute("buffetAll",chefService.findById(id).getBuffets());
		return "chef.html";
	}
	
	@GetMapping("/admin/deleteChef")
    public String deleteChef(Model model) {

    	model.addAttribute("chefAll", chefService.findAll());
		return "deleteChef.html";
    }
	
	@PostMapping("/admin/deleteChef")
	public String deleteDoneChef(Model model, @RequestParam(required=false,name="chef")Long id) {
		if (id==null) {
			model.addAttribute("chefAll", chefService.findAll());
			return "deleteChef.html";
		}
		chefService.remove(id);
    	model.addAttribute("buffetAll", chefService.findAll());
		return "elencoChef.html";
	}
	

	
	
	
}

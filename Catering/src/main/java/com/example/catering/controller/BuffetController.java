package com.example.catering.controller;


import java.util.ArrayList;
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

import com.example.catering.service.BuffetService;
import com.example.catering.validator.BuffetValidator;
import com.example.catering.model.Chef;

import com.example.catering.model.Buffet;
import com.example.catering.service.ChefService;

@Controller
public class BuffetController {

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private BuffetValidator validator;

	@Autowired
	private ChefService chefService;

	@GetMapping("/admin/buffet")
	public String buffetPage(Model model) {
		model.addAttribute("buffet", new Buffet());
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefAll", chefs);

		return "buffetForm.html";
	}

	@PostMapping("/admin/buffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResult, Model model,@RequestParam(required=false,name="chef")Long id ) {

		validator.validate(buffet,bindingResult);
		if(!bindingResult.hasErrors()) {
			Chef chef = this.chefService.findById(id);
			buffet.setChef(chef);
			List<Buffet> chefBuffet = chef.getBuffets();
			chefBuffet.add(buffet);
			chef.setBuffets(chefBuffet);
			this.buffetService.save(buffet);
			
			model.addAttribute("buffet", buffet);
			model.addAttribute("chef", chef);

			return "buffet.html";
		}
		
		return "buffetForm.html";
	}

	@GetMapping("/buffetAll/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		Buffet buffet = buffetService.findById(id);
		
		model.addAttribute("buffet", buffet);
		model.addAttribute("chef", buffet.getChef());
		model.addAttribute("piatti",buffetService.findById(id).getPiatti());
		
		return "buffet.html";
	}

	@GetMapping("/buffetAll")
	public String getBuffets(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffetAll", buffets);
		return "elencoBuffets.html";
	}
	
	@GetMapping("/admin/deleteBuffet")
    public String deleteBuffet(Model model) {

    	model.addAttribute("buffetAll", buffetService.findAll());
		return "deleteBuffet.html";
    }
	
	@PostMapping("/admin/deleteBuffet")
	public String deleteDoneBuffet(Model model, @RequestParam(required=false,name="buffet")Long id) {
		if (id==null) {
			model.addAttribute("buffetAll", buffetService.findAll());
			return "deleteBuffet.html";
		}
		buffetService.remove(id);
    	model.addAttribute("buffetAll", buffetService.findAll());
		return "elencoBuffets.html";
	}


}

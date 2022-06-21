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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.catering.service.PiattoService;
import com.example.catering.service.BuffetService;
import com.example.catering.validator.PiattoValidator;
import com.example.catering.model.Chef;
import com.example.catering.model.Piatto;
import com.example.catering.model.Buffet;
import com.example.catering.service.ChefService;

@Controller
public class PiattoController {

	@Autowired
	private PiattoService piattoService;

	@Autowired
	private PiattoValidator validator;
	
	@Autowired
	private BuffetService buffetService;

	@GetMapping("/admin/piatto")
	public String piattoPage(Model model) {
		model.addAttribute("piatto", new Piatto());
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffetAll", buffets);

		return "PiattoForm.html";
	}

	@PostMapping("/admin/piatto")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResult, Model model,@RequestParam(required=false,name="buffet")Long id ) {

		validator.validate(piatto,bindingResult);
		if(!bindingResult.hasErrors()) {
			Buffet buffet = this.buffetService.findById(id);
			List<Piatto> piatti = buffet.getPiatti();
			piatti.add(piatto);
			buffet.setPiatti(piatti);
			
			this.piattoService.save(piatto);
			
			model.addAttribute("piatto", piatto);

			return "piatto.html";
		}
		
		return "piattoForm.html";
	}

	@GetMapping("/piattoAll/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		Piatto piatto = piattoService.getById(id);
		
		model.addAttribute("piatto", piatto);
		model.addAttribute("ingredienti",piattoService.getById(id).getIngredienti());
		return "piatto.html";
	}

	@GetMapping("/piattoAll")
	public String getPiatti(Model model) {
		List<Piatto> piatto = piattoService.findAll();
		model.addAttribute("piattoAll", piatto);
		return "elencoPiatti.html";
	}
	
	@GetMapping("/admin/deletePiatto")
    public String deletePiatto(Model model) {

    	model.addAttribute("piattoAll", piattoService.findAll());
		return "deletePiatto.html";
    }
	
	@PostMapping("/admin/deletePiatto")
	public String deleteDonePiatto(Model model, @RequestParam(required=false,name="piatto")Long id) {
		if (id==null) {
			model.addAttribute("piattoAll", piattoService.findAll());
			return "deletePiatto.html";
		}
		piattoService.elimina(id);
    	model.addAttribute("piattoAll", piattoService.findAll());
		return "elencoPiatti.html";
	}


}

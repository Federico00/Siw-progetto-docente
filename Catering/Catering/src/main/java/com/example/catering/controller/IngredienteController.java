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

import com.example.catering.service.IngredienteService;
import com.example.catering.service.PiattoService;
import com.example.catering.validator.IngredienteValidator;
import com.example.catering.model.Chef;
import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;
import com.example.catering.model.Buffet;
import com.example.catering.service.ChefService;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private IngredienteValidator validator;
	
	@Autowired
	private PiattoService piattoService;

	@GetMapping("/admin/ingrediente")
	public String ingredientePage(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piattoAll", piatti);

		return "ingredienteForm.html";
	}

	@PostMapping("/admin/ingrediente")
	public String addingrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model,@RequestParam(required=false,name="piatto")Long id ) {

		validator.validate(ingrediente,bindingResult);
		if(!bindingResult.hasErrors()) {
			Piatto piatto = this.piattoService.getById(id);
			List<Ingrediente> ingredientiPiatto = piatto.getIngredienti();
			ingredientiPiatto.add(ingrediente);
			piatto.setIngredienti(ingredientiPiatto);
			
			this.ingredienteService.inserisci(ingrediente);
			
			model.addAttribute("ingrediente", ingrediente);

			return "ingrediente.html";
		}
		
		return "ingredienteForm.html";
	}

	@GetMapping("/ingredienteAll/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		Ingrediente ingrediente = ingredienteService.getById(id);
		
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente.html";
	}

	@GetMapping("/ingredienteAll")
	public String getIngredienti(Model model) {
		List<Ingrediente> ingrediente = ingredienteService.findAll();
		model.addAttribute("ingredienteAll", ingrediente);
		return "elencoIngrediente.html";
	}


}

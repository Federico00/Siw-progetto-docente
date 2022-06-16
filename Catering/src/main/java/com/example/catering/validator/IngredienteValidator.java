package com.example.catering.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.catering.model.Buffet;
import com.example.catering.model.Ingrediente;
import com.example.catering.service.BuffetService;
import com.example.catering.service.IngredienteService;



@Component
public class IngredienteValidator implements Validator {
	@Autowired
	private IngredienteService ingredienteService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "origine", "required");
		if (this.ingredienteService.aldreadyExist((Ingrediente)target))
			errors.reject("ingrediente.duplicato");
		
	}

}

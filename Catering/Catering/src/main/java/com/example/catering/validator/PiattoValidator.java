package com.example.catering.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.catering.model.Buffet;
import com.example.catering.model.Chef;
import com.example.catering.model.Piatto;
import com.example.catering.service.BuffetService;
import com.example.catering.service.ChefService;
import com.example.catering.service.PiattoService;



@Component
public class PiattoValidator implements Validator {
	@Autowired
	private PiattoService piattoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Piatto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		if (this.piattoService.aldreadyExist((Piatto)target))
			errors.reject("piatto.duplicato");
		
	}

}

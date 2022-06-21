package com.example.catering.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.catering.model.Buffet;
import com.example.catering.service.BuffetService;



@Component
public class BuffetValidator implements Validator {
	@Autowired
	private BuffetService buffetService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (this.buffetService.aldreadyExist((Buffet)target))
			errors.reject("buffet.duplicato");
		
	}

}

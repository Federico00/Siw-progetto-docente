package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.model.Chef;
import com.example.catering.repository.ChefRepository;





@Service
public class ChefService {
	@Autowired
	private ChefRepository chefRepository;
	
	@Transactional
	public void save(Chef chef) {
		 chefRepository.save(chef);
	}


	public List<Chef> findAll() {
		List<Chef> elencoChef = new ArrayList<Chef>();
		for(Chef b : chefRepository.findAll()) {
			elencoChef.add(b);
		}
		return elencoChef;
	
	}
	//vedere nel repo
	public boolean aldreadyExist(Chef buffet) {
		return this.chefRepository.existsByNomeAndCognome(buffet.getNome(), buffet.getCognome());
	}

	
	

	public Chef findById(Long id) {
	
		return chefRepository.findById(id).get();
	}

	public void add(Chef chef) {
		this.chefRepository.save(chef);
	}


	public void remove(Long id) {
		// TODO Auto-generated method stub
		this.chefRepository.deleteById(id);
		
	}


	



}

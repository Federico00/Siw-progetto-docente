package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.catering.model.Piatto;

import com.example.catering.repository.PiattoRepository;



@Service
public class PiattoService {
	@Autowired
	private PiattoRepository piattoRepository;
	
	@Transactional
	public void save(Piatto piatto) {
		 piattoRepository.save(piatto);
	}

	public List<Piatto> findAll() {
		List<Piatto > piatti = new ArrayList<Piatto>();
		for(Piatto b : piattoRepository.findAll()) {
			piatti.add(b);
		}
		return piatti;
	
	}
	public Piatto getById(Long id) {
		
		return piattoRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public List<Piatto> tutti() {
		return (List<Piatto>) piattoRepository.findAll();
	}

	

	//vedere nel repo
	public boolean aldreadyExist(Piatto piatto) {
		return this.piattoRepository.existsByNomeAndDescrizione(piatto.getNome(), piatto.getDescrizione());
	}

	public void delete(Piatto piatto) {
		piattoRepository.delete(piatto);
		
	}
	



	public void inserisci(Piatto piatto) {
		this.piattoRepository.save(piatto);
		
	}

	public void elimina(Long id) {
		this.piattoRepository.deleteById(id);
		
	}



}

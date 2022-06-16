package com.example.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.catering.repository.BuffetRepository;
import com.example.catering.model.Buffet;



@Service
public class BuffetService {
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Transactional
	public void save(Buffet buffet) {
		 buffetRepository.save(buffet);
	}

	public Buffet findById(Long id) {
		return buffetRepository.findById(id).get();
	}

	public List<Buffet> findAll() {
		List<Buffet > buffets = new ArrayList<Buffet>();
		for(Buffet b : buffetRepository.findAll()) {
			buffets.add(b);
		}
		return buffets;
	
	}
	//vedere nel repo
	public boolean aldreadyExist(Buffet buffet) {
		return this.buffetRepository.existsByNomeAndDescrizione(buffet.getNome(), buffet.getDescrizione());
	}

	public void remove(Long id) {
		// TODO Auto-generated method stub
		this.buffetRepository.deleteById(id);
		
	}



}

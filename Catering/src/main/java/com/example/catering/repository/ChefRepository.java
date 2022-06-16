package com.example.catering.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {

	boolean existsByNomeAndCognome(String nome, String cognome);

}

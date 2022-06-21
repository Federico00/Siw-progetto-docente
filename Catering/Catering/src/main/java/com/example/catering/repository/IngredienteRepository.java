package com.example.catering.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Ingrediente;
import com.example.catering.model.Piatto;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

	boolean existsByNomeAndDescrizione(String nome, String descrizione);

}

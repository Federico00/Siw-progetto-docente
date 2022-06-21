package com.example.catering.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.catering.model.Credentials;

import java.util.Optional;





public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
	public Optional<Credentials> findByUsername(String username);

}
package com.arthur.repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.arthur.model.Persona;

public interface IPersonaRepo {
	
	Mono<Persona> registrar(Persona persona);

	Mono<Persona> modificar(Persona persona);
	
	Flux<Persona> listar();
	
	Mono<Persona> listarPorId(Integer id);
	
	Mono<Void> eliminar(Integer id);
	
 }

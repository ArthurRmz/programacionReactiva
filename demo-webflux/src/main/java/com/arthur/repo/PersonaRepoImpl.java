package com.arthur.repo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.log4j.Log4j2;

import com.arthur.model.Persona;

@Log4j2
@Repository
public class PersonaRepoImpl implements IPersonaRepo{
	private List<Persona> personas;

	public PersonaRepoImpl() {
		personas = new ArrayList<Persona>();
		personas.add(new Persona(1,"Arthur"));
		personas.add(new Persona(2,"Jessica"));
		personas.add(new Persona(3,"Pamela"));
	}
	
	@Override
	public Mono<Persona> registrar(Persona persona) {
		log.info(persona.toString());
		return Mono.just(persona);
	}

	@Override
	public Mono<Persona> modificar(Persona persona) {
		log.info(persona.toString());
		return Mono.just(persona);
	}

	@Override
	public Flux<Persona> listar() {
		return Flux.fromIterable(personas);
	}

	@Override
	public Mono<Persona> listarPorId(Integer id) {
		return Mono.just(new Persona(id,"Arthur"));
	}

	@Override
	public Mono<Void> eliminar(Integer id) {
		return Mono.empty();
	}

}

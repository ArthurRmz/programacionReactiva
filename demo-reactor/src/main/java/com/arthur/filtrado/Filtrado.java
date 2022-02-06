package com.arthur.filtrado;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.arthur.models.Persona;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Log4j2
public class Filtrado {
	private List<Persona> personas;
	private Consumer<Persona> imprimirPersona = p -> log.info(p.toString());
	
	public Filtrado() {
		personas = new ArrayList<>();
		personas.add(new Persona(1,"Arthur",25));
		personas.add(new Persona(2,"Jessica",26));
		personas.add(new Persona(3,"Pamela",27));
		personas.add(new Persona(1,"Jaime",27));
	}
	
	public void filter() {
		Flux.fromIterable(personas)
		.filter(p->p.getEdad()>26)
		.subscribe(imprimirPersona);
	}
	
	public void distinct() {
		Flux.fromIterable(personas)
		.distinct()
		.subscribe(imprimirPersona);
	}
	
	public void distinctWithPrimitives() {
		Flux.fromIterable(List.of(1,1,2,2))
		.distinct()
		.subscribe(num -> log.info(num));
	}
	
	public void take() {
		Flux.fromIterable(personas)
		.take(2)
		.subscribe(imprimirPersona);
	}
	
	public void skip() {
		Flux.fromIterable(personas)
		.skip(1)
		.subscribe(imprimirPersona);
	}
	
	public void skipLast() {
		Flux.fromIterable(personas)
		.skipLast(2)
		.subscribe(imprimirPersona);
	}

}

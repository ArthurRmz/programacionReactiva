package com.arthur.operador.matematico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

import com.arthur.models.Persona;

@Log4j2
public class Matematico {
	
private List<Persona> personas;
	
	private Consumer<Persona> imprimirPersona = p -> log.info(p.toString());
	
	public Matematico(){
		personas = new ArrayList<>();
		personas.add(new Persona(1,"Arthur",25));
		personas.add(new Persona(2,"Jessica",26));
		personas.add(new Persona(3,"Pamela",27));
	}

	public void average() {
		Flux.fromIterable(personas)
		.collect(Collectors.averagingInt(p -> p.getEdad()))
		.subscribe(log::info);
	}
	
	public void count() {
		Flux.fromIterable(personas)
		.count()
		.subscribe(x -> log.info("Cantidad: ".concat(x.toString())));
	}
	
	public void min() {
		Flux.fromIterable(personas)
		.collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
		.subscribe(p -> log.info(p.get().toString()));
	}
	
	public void sum() {
		Flux.fromIterable(personas)
		.collect(Collectors.summingInt(Persona::getEdad))
		.subscribe(sum -> log.info("Suma: ".concat(sum.toString())));
	}
	
	public void summarizing() {
		Flux.fromIterable(personas)
		.collect(Collectors.summarizingInt(Persona::getEdad))
		.subscribe(res -> log.info("Resumen: ".concat(res.toString())));
	}
}

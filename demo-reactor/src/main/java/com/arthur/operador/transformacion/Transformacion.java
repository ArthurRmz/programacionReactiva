package com.arthur.operador.transformacion;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.arthur.models.Persona;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
public class Transformacion {
	
	private Consumer<Persona> imprimirPersona = p -> log.info(p.toString());
	
	List<Persona> personas;
	
	public Transformacion() {
		personas = new ArrayList<>();
		personas.add(new Persona(1,"Arthur",25));
		personas.add(new Persona(2,"Jessica",25));
		personas.add(new Persona(3,"Pamela",25));
		personas.add(new Persona(1,"Jaime",25));
	}

	//map returns a data type
	public void map() {
		
		Flux.fromIterable(personas)
		.map(p -> {
			p.setEdad(p.getEdad()+10);
			return p;
		})
		.subscribe(imprimirPersona);
	}
	
	public void mapInteger() {
		Flux<Integer> fx = Flux.range(0, 10);
		Flux<Integer> fx2 = fx.map(x->x+10);
		fx2.subscribe(x -> log.info("X: "+x));
	}
	
	public void createPersons() {
		Flux<Integer> fx = Flux.range(0, 10);
		fx.map(num -> new Persona(num, "Persona"+num,25)).subscribe(imprimirPersona);
	}
	
	//Flat map returns a data stream
	public void flatMap() {
		Flux.fromIterable(personas)
		.flatMap(p ->{
			p.setEdad(p.getEdad()+10);
			return Mono.just(p);
		})
		.subscribe(imprimirPersona);
	}
	
	public void groupBy() {
		Flux.fromIterable(personas)
		.groupBy(Persona::getIdPersona)
		.flatMap(idFlux -> idFlux.collectList())
		.subscribe(x->log.info(x.toString()));
	}
	
	
}

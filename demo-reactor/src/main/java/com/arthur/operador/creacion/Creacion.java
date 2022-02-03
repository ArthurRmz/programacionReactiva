package com.arthur.operador.creacion;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.arthur.models.Persona;

import io.reactivex.Observable;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
public class Creacion {
	
	private Consumer<Persona> imprimirPersona = p -> log.info(p.toString());

	public void justFrom() {
		Mono.just(new Persona(1,"Arthur",25));
	}
	
	public void empty() {
		Mono.empty();
		Flux.empty();
		Observable.empty();
	}
	
	public void range() {
		Flux.range(0, 3).doOnNext(i -> log.info("i: "+i)).subscribe();
	}
	
	public void repeat() {
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1,"Arthur",25));
		personas.add(new Persona(2,"Jessica",25));
		personas.add(new Persona(3,"Pamela",25));
		
		Flux.fromIterable(personas).repeat(3).subscribe(imprimirPersona);
//		Mono.just(new Persona(1,"Arthur",25)).repeat(3).subscribe(imprimirPersona);
	}
}

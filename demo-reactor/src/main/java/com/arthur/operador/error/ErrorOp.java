package com.arthur.operador.error;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.arthur.models.Persona;

@Log4j2
public class ErrorOp {
	
	private List<Persona> personas;
	
	private Consumer<Persona> imprimirPersona = p -> log.info(p.toString());
	
	public ErrorOp(){
		personas = new ArrayList<>();
		personas.add(new Persona(1,"Arthur",25));
		personas.add(new Persona(2,"Jessica",26));
		personas.add(new Persona(3,"Pamela",27));
	}

	public void retry() {
		Flux.fromIterable(personas)
		.concatWith(Flux.error(new RuntimeException("Un error")))
		.retry(1)
		.doOnNext(imprimirPersona)
		.subscribe();
	}
	
	public void errorReturn() {
		Flux.fromIterable(personas)
		.concatWith(Flux.error(new RuntimeException("Un error")))
		.onErrorReturn(new Persona(0,"XYZ", 99))
		.subscribe(imprimirPersona);
	}
	
	public void errorResume() {
		Flux.fromIterable(personas)
		.concatWith(Flux.error(new RuntimeException("Un error")))
		.onErrorResume(e -> Mono.just(new Persona(0,"XYZ",99)))
		.subscribe(imprimirPersona);
	}
	
	public void errorMap() {
		Flux.fromIterable(personas)
		.concatWith(Flux.error(new RuntimeException("Un error")))
		.onErrorMap(e -> new InterruptedException(e.getMessage()))
		.subscribe(imprimirPersona);
	}
}

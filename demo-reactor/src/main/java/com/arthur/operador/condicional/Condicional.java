package com.arthur.operador.condicional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.arthur.models.Persona;

@Log4j2
public class Condicional {
	
	private List<Persona> personas;
	
	private Consumer<Persona> imprimirPersona = p -> log.info(p.toString());
	
	public Condicional(){
		personas = new ArrayList<>();
		personas.add(new Persona(1,"Arthur",25));
		personas.add(new Persona(2,"Jessica",26));
		personas.add(new Persona(3,"Pamela",27));
	}

	public void defaultIfEmpty() {
		Mono.just(new Persona(1, "Arthur", 25))
//		Flux.empty()
//		Mono.empty()
		.defaultIfEmpty(new Persona(0,"DEFAULT",99))
		.subscribe(p -> log.info(p.toString()));
	}
	
	public void takeUntil() {
		Flux.fromIterable(personas)
		.takeUntil(p -> p.getEdad()>25)
		.subscribe(imprimirPersona);
	}
	
	public void timeOut() throws InterruptedException{
		Flux.fromIterable(personas)
		.delayElements(Duration.ofSeconds(1))
		.timeout(Duration.ofSeconds(2))
		.subscribe(imprimirPersona);
		
		Thread.sleep(10000);
	}
}

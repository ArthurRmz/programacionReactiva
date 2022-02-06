package com.arthur;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.reactivex.Observable;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.arthur.filtrado.Filtrado;
import com.arthur.models.Persona;
import com.arthur.operador.combinacion.Combinacion;
import com.arthur.operador.creacion.Creacion;
import com.arthur.operador.error.ErrorOp;
import com.arthur.operador.transformacion.Transformacion;

@Log4j2
@SpringBootApplication
public class DemoReactorApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoReactorApplication.class, args);
	}
	
	public void reactor() {
		Mono.just(new Persona(1, "Arthur", 25))
		.doOnNext(p -> {
			//Logica adicional
			log.info("[Reactor] Persona"+p.toString());
		})
		.subscribe(p -> log.info("[Reactor] Persona"+p.toString()));
	}
	
	public void rxJava2() {
		Observable.just(new Persona(1, "Arthur", 25))
		.doOnNext(p->log.info("[rxJava]"+p.toString()))
		.subscribe(p->log.info("[rxJava]"+p.toString()));
	}
	
	public void mono() {
		Mono.just(new Persona(1,"Arthur",25)).subscribe(p->log.info("Mono: "+p.toString()));
	}
	
	public void flux() {
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1,"Arthur",25));
		personas.add(new Persona(2,"Jessica",25));
		personas.add(new Persona(3,"Pamela",25));
		
		Flux.fromIterable(personas).subscribe(p->log.info("Flux: "+p.toString()));
	}
	
	public void fluxMono() {
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1,"Arthur",25));
		personas.add(new Persona(2,"Jessica",25));
		personas.add(new Persona(3,"Pamela",25));
		
		Flux<Persona> fx = Flux.fromIterable(personas);
		fx.collectList().subscribe(lista -> log.info("FluxMono: "+lista.toString()));
	}

	@Override
	public void run(String... args) throws Exception {
//		reactor();
//		rxJava2();
//		mono();
//		flux();
//		fluxMono();
//		Creacion app = new Creacion();
//		app.range();
//		app.repeat();
		
//		Transformacion app = new Transformacion();
//		app.createPersons();
//		app.map();
//		app.mapInteger();
//		app.flatMap();
//		app.groupBy();
		
//		Filtrado app = new Filtrado();
//		app.filter();
//		app.distinctWithPrimitives();
//		app.distinct();
//		app.take();
//		app.skip();
//		app.skipLast();
		
//		Combinacion app = new Combinacion();
//		app.merge();
//		app.mergeOtherObjects();
//		app.zip();
//		app.zipWith();
		
		ErrorOp app = new ErrorOp();
//		app.retry();
//		app.errorReturn();
//		app.errorResume();
		app.errorMap();
	}
	
	

}

package com.arthur.operador.combinacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

import com.arthur.models.Persona;
import com.arthur.models.Venta;

@Log4j2
public class Combinacion {
	
	private List<Persona> personas1;

	private List<Persona> personas2;
	
	private List<Venta> ventas;
	
	private Consumer<Persona> imprimirPersona = p -> log.info(p.toString());
	
	public Combinacion() {
		personas1 = new ArrayList<>();
		personas1.add(new Persona(1,"Arthur",25));
		personas1.add(new Persona(2,"Jessica",26));
		personas1.add(new Persona(3,"Pamela",27));

		personas2 = new ArrayList<>();
		personas2.add(new Persona(4,"Harry",25));
		personas2.add(new Persona(5,"Ron",26));
		personas2.add(new Persona(6,"Hermione",27));
		
		ventas = new ArrayList<>();
		ventas.add(new Venta(1,LocalDateTime.now()));
	}
	
	public void merge() {
		Flux<Persona> fx1 = Flux.fromIterable(personas1);
		Flux<Persona> fx2 = Flux.fromIterable(personas2);
		
		Flux.merge(fx1, fx2)
		.subscribe(imprimirPersona);
	}
	
	public void mergeOtherObjects() {
		Flux<Persona> fx1 = Flux.fromIterable(personas1);
		Flux<Persona> fx2 = Flux.fromIterable(personas2);

		Flux<Venta> fx3 = Flux.fromIterable(ventas);
		
		Flux.merge(fx1, fx2, fx3).subscribe(p -> log.info(p.toString()));
	}
	
	public void zip() {
		Flux<Persona> fx1 = Flux.fromIterable(personas1);
		Flux<Persona> fx2 = Flux.fromIterable(personas2);

		Flux<Venta> fx3 = Flux.fromIterable(ventas);
		
//		Flux.zip(fx1, fx2, (p1,p2)->String.format("Flux1: %s, Flux2: %s", p1, p2))
//		.subscribe(p -> log.info(p.toString()));
		
		Flux.zip(fx1, fx2, fx3)
		.subscribe(p->log.info(p.toString()));
	}
	
	public void zipWith() {
		Flux<Persona> fx1 = Flux.fromIterable(personas1);
		Flux<Persona> fx2 = Flux.fromIterable(personas2);

		Flux<Venta> fx3 = Flux.fromIterable(ventas);
		
//		fx1.zipWith(fx2, (p1,p2)->String.format("Flux1: %s, Flux2: %s", p1, p2))
//		.subscribe(p -> log.info(p.toString()));

		fx1.zipWith(fx3, (p1,v1)->String.format("Flux1: %s, Flux2: %s", p1, v1))
		.subscribe(p -> log.info(p.toString()));
	}

}

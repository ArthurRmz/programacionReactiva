package com.arthur;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arthur.models.Persona;

import io.reactivex.Observable;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

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

	@Override
	public void run(String... args) throws Exception {
		reactor();
		rxJava2();
		
	}
	
	

}

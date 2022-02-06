package com.arthur.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Persona {

	private Integer idPersona;
	
	@EqualsAndHashCode.Exclude
	private String nombres;
	
	@EqualsAndHashCode.Exclude
	private Integer edad;
}

package com.arthur.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

	private Integer idPersona;
	private String nombres;
	private Integer edad;
}

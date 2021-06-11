package com.veterinario.modelo;

/**
 * Metodo que recoge las posibles excepciones que puedan ocurrir mientras se
 * ejecuta la aplicación
 * 
 * @author Cristina Gonzalez Baizan
 *
 */
public class GestionVeterinarioException extends Exception {

	private static final long serialVersionUID = 1L;

	public GestionVeterinarioException(String msg) {
		super(msg);
	}
}

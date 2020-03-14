package com.uca.foodPlanner.exception;

public class NotFoundException extends Exception{
	
	private static final long serialVersionUID = -683615199613899314L;
	
	private static final String MSG = "La entidad buscada no existe";

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException() {
		super(MSG);
	}
}
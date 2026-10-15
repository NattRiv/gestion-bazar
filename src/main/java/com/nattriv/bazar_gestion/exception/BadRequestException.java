package com.nattriv.bazar_gestion.exception;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BadRequestException (String msj) {
		super(msj);
	}
	
}

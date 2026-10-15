package com.nattriv.bazar_gestion.exception;

public class NotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NotFoundException (String msj) {
		super(msj);
	}
}

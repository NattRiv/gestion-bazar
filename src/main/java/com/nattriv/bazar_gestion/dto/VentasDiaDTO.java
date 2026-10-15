package com.nattriv.bazar_gestion.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class VentasDiaDTO {

	private Double monto;
	private int cantidad;
	
}

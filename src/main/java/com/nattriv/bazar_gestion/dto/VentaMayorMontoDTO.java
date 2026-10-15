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
public class VentaMayorMontoDTO {

	private Long codigo_venta;
	private Double total;
	private int cantProductos;
	private String nombreCliente;
	private String apellidoCliente;
	
}

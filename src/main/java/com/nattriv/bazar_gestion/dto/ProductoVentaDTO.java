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
public class ProductoVentaDTO {
	private Long codigo_producto;
	private String nombre;
	private Double costo;
}

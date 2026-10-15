package com.nattriv.bazar_gestion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ProductoDTO {
	private Long codigo_producto;
	@NotBlank
	private String nombre;
	@NotBlank
	private String marca;
	@NotNull
	@Positive
	private Double costo;
	@NotNull
	@PositiveOrZero
	private int cantidad_disponible;
}

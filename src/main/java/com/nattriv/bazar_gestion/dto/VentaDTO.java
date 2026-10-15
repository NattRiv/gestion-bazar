package com.nattriv.bazar_gestion.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class VentaDTO {

	private Long codigo_venta;
	@NotNull
	private LocalDate fecha_venta;
	private Double total;
	@NotNull
	private Long idCliente;
	@NotEmpty
	@Valid
	private List<ProductoVentaDTO> listaProductos;
}

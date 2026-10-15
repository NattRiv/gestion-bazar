package com.nattriv.bazar_gestion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class ClienteDTO {

	private Long id_cliente;
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellido;
	@NotBlank
	@Pattern(regexp = "\\d{7,8}", message = "El DNI debe tener 7 u 8 dígitos")
	private String dni;
}

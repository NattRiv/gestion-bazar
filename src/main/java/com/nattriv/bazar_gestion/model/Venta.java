package com.nattriv.bazar_gestion.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Builder
public class Venta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo_venta;
	private LocalDate fecha_venta;
	private Double total;
	@ManyToMany
	private List<Producto> listaProductos;
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente unCliente;
	
	public Venta() {
		
	}
	
	public Venta(Long codigo_venta,LocalDate fecha_venta,Double total,List<Producto> listaProductos,Cliente unCliente) {
		this.codigo_venta = codigo_venta;
		this.fecha_venta = fecha_venta;
		this.total = total;
		this.listaProductos = listaProductos;
		this.unCliente = unCliente;
	}
}

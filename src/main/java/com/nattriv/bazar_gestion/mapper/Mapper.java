package com.nattriv.bazar_gestion.mapper;

import java.util.List;

import com.nattriv.bazar_gestion.dto.ClienteDTO;
import com.nattriv.bazar_gestion.dto.ProductoDTO;
import com.nattriv.bazar_gestion.dto.ProductoVentaDTO;
import com.nattriv.bazar_gestion.dto.VentaDTO;
import com.nattriv.bazar_gestion.dto.VentaMayorMontoDTO;
import com.nattriv.bazar_gestion.model.Cliente;
import com.nattriv.bazar_gestion.model.Producto;
import com.nattriv.bazar_gestion.model.Venta;

public class Mapper {

	//Mapeo de Producto a ProductoDTO
	public static ProductoDTO toDTO(Producto p) {
		if(p == null) return null;
		
		return ProductoDTO.builder()
				.codigo_producto(p.getCodigo_producto())
				.nombre(p.getNombre())
				.marca(p.getMarca())
				.costo(p.getCosto())
				.cantidad_disponible(p.getCantidad_disponible())
				.build();	
	}
	
	//Mapeo de Producto a ProductoVentaDTO
		public static ProductoVentaDTO toProductoVentaDTO(Producto p) {
			if(p == null) return null;
			
			return ProductoVentaDTO.builder()
					.codigo_producto(p.getCodigo_producto())
					.nombre(p.getNombre())
					.costo(p.getCosto())
					.build();	
		}

	//Mapeo de Cliente a ClienteDTO
	public static ClienteDTO toDTO(Cliente c) {
		if(c == null) return null;
		
		return ClienteDTO.builder()
				.id_cliente(c.getId_cliente())
				.nombre(c.getNombre())
				.apellido(c.getApellido())
				.dni(c.getDni())
				.build();	
	}
	//Mapeo de Venta a VentaDTO
	public static VentaDTO toDTO(Venta v) {
		if(v == null) return null;
		
		List<ProductoVentaDTO> listaProd = v.getListaProductos().stream()
											.map(Mapper::toProductoVentaDTO)
											.toList();
		
		return VentaDTO.builder()
				.codigo_venta(v.getCodigo_venta())
				.fecha_venta(v.getFecha_venta())
				.total(v.getTotal())
				.idCliente(v.getUnCliente().getId_cliente())
				.listaProductos(listaProd)
				.build();
		
	}
	
	//Mapeo de Venta a VentaMayorMontoDTO
	public static VentaMayorMontoDTO toVentaMayorDTO(Venta v) {
		if(v == null) return null;
		
		int cantProd = v.getListaProductos().size();
		
		return VentaMayorMontoDTO.builder()
								.codigo_venta(v.getCodigo_venta())
								.total(v.getTotal())
								.cantProductos(cantProd)
								.nombreCliente(v.getUnCliente().getNombre())
								.apellidoCliente(v.getUnCliente().getApellido())
								.build();
	}
}

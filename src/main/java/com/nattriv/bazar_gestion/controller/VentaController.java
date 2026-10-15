package com.nattriv.bazar_gestion.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nattriv.bazar_gestion.dto.ProductoVentaDTO;
import com.nattriv.bazar_gestion.dto.VentaDTO;
import com.nattriv.bazar_gestion.dto.VentaMayorMontoDTO;
import com.nattriv.bazar_gestion.dto.VentasDiaDTO;
import com.nattriv.bazar_gestion.service.IVentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	private IVentaService service;
	
	public VentaController(IVentaService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<VentaDTO>> getVentas(){
		return ResponseEntity.ok(service.getVentas());
	}
			
	@GetMapping("/{id}")
	public ResponseEntity<VentaDTO> getVenta(@PathVariable Long id) {
		return ResponseEntity.ok(service.getVenta(id));
	}
	
	@PostMapping("/crear")
	public ResponseEntity<VentaDTO> saveProducto(@Valid @RequestBody VentaDTO ventaDTO){
		
		VentaDTO ventaCreada = service.saveVenta(ventaDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ventaCreada);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> deleteVenta(@PathVariable Long id){
		
		service.deleteVenta(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<VentaDTO> editVenta(@PathVariable Long id, 
											@Valid @RequestBody VentaDTO ventaDTO){
		
		return ResponseEntity.ok(service.editVenta(id, ventaDTO));
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<List<ProductoVentaDTO>> getListaProductos(@PathVariable Long id){
		return ResponseEntity.ok(service.getListaProductos(id));
	}
	
	@GetMapping("/dia/{fecha}")
	public ResponseEntity<VentasDiaDTO> getVentasDelDia(@PathVariable LocalDate fecha){
		return ResponseEntity.ok(service.getVentasDelDia(fecha));
	}
	
	@GetMapping("/mayor-venta")
	public ResponseEntity<VentaMayorMontoDTO> getVentaMayorMonto(){
		return ResponseEntity.ok(service.getVentaMayorMonto());
	}
}

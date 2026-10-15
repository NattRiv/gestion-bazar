package com.nattriv.bazar_gestion.controller;

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

import com.nattriv.bazar_gestion.dto.ProductoDTO;
import com.nattriv.bazar_gestion.service.IProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	private IProductoService service;
	
	public ProductoController (IProductoService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductoDTO>> getProductos(){
		return ResponseEntity.ok(service.getProductos());
	}
			
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> getProducto(@PathVariable Long id) {
		return ResponseEntity.ok(service.getProducto(id));
	}
	
	@PostMapping("/crear")
	public ResponseEntity<ProductoDTO> saveProducto(@Valid @RequestBody ProductoDTO prodDTO){
		
		ProductoDTO prodCreado = service.saveProducto(prodDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(prodCreado);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> deleteProducto(@PathVariable Long id){
		
		service.deleteProducto(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<ProductoDTO> editProducto(@PathVariable Long id, 
													@Valid @RequestBody ProductoDTO prodDTO){
		
		return ResponseEntity.ok(service.editProducto(id, prodDTO));
	}
	
	@GetMapping("/falta-stock")
	public ResponseEntity<List<ProductoDTO>> getProdCantMenor5(){
		
		return ResponseEntity.ok(service.getProdCantMenor5());
	}

}
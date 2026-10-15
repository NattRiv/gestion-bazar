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

import com.nattriv.bazar_gestion.dto.ClienteDTO;
import com.nattriv.bazar_gestion.service.IClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private IClienteService service;
	
	public ClienteController(IClienteService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getClientes(){
		return ResponseEntity.ok(service.getClientes());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getCliente(@PathVariable Long id){
		return ResponseEntity.ok(service.getCliente(id));
	}
	
	@PostMapping("/crear")
	public ResponseEntity<ClienteDTO> saveCliente (@Valid @RequestBody ClienteDTO cliDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCliente(cliDTO));
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
		service.deleteCliente(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<ClienteDTO> editCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO cliDTO){
		return ResponseEntity.ok(service.editCliente(id, cliDTO));
	}
}

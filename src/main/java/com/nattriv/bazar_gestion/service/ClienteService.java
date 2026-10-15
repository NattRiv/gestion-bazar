package com.nattriv.bazar_gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nattriv.bazar_gestion.dto.ClienteDTO;
import com.nattriv.bazar_gestion.exception.NotFoundException;
import com.nattriv.bazar_gestion.mapper.Mapper;
import com.nattriv.bazar_gestion.model.Cliente;
import com.nattriv.bazar_gestion.repository.IClienteRepository;

@Service
public class ClienteService implements IClienteService {

	private IClienteRepository repo;
	
	public ClienteService(IClienteRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<ClienteDTO> getClientes() {
	
		return repo.findAll()
				.stream()
				.map(Mapper::toDTO)
				.toList();
	}

	@Override
	public ClienteDTO getCliente(Long id) {
		
		Cliente cli = repo.findById(id).orElseThrow(() -> new NotFoundException("Cliente con id: " + id + " no encontrado"));
		
		return Mapper.toDTO(cli);
	}

	@Override
	public ClienteDTO saveCliente(ClienteDTO cliDTO) {
		
		Cliente cli = Cliente.builder()
							.nombre(cliDTO.getNombre())
							.apellido(cliDTO.getApellido())
							.dni(cliDTO.getDni())
							.build();
		
		return Mapper.toDTO(repo.save(cli));
	}

	@Override
	public ClienteDTO editCliente(Long id, ClienteDTO cliDTO) {
		
		Cliente cli = repo.findById(id).orElseThrow(() -> new NotFoundException("Cliente con id: " + id + " no encontrado"));
	
		cli.setNombre(cliDTO.getNombre());
		cli.setApellido(cliDTO.getApellido());
		cli.setDni(cliDTO.getDni());
		
		
		return Mapper.toDTO(repo.save(cli));
	}

	@Override
	public void deleteCliente(Long id) {

		if(!repo.existsById(id)) throw new NotFoundException("Cliente con id: " + id + " no encontrado");
		
		repo.deleteById(id);
	}

	@Override
	public Cliente getClienteEntidad(Long id) {
	    return repo.findById(id)
	            .orElseThrow(() -> new NotFoundException("Cliente no encontrado de id: " + id));
	}
}

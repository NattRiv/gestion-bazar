package com.nattriv.bazar_gestion.service;

import java.util.List;

import com.nattriv.bazar_gestion.dto.ClienteDTO;
import com.nattriv.bazar_gestion.model.Cliente;

public interface IClienteService {
	List<ClienteDTO> getClientes();
	ClienteDTO getCliente(Long id);
	ClienteDTO saveCliente(ClienteDTO cliDTO);
	ClienteDTO editCliente(Long id, ClienteDTO cliDTO);
	void deleteCliente(Long id);
	Cliente getClienteEntidad(Long id);
}

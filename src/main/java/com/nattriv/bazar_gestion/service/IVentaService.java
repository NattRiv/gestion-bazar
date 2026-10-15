package com.nattriv.bazar_gestion.service;

import java.time.LocalDate;
import java.util.List;

import com.nattriv.bazar_gestion.dto.ProductoVentaDTO;
import com.nattriv.bazar_gestion.dto.VentaDTO;
import com.nattriv.bazar_gestion.dto.VentaMayorMontoDTO;
import com.nattriv.bazar_gestion.dto.VentasDiaDTO;

public interface IVentaService {

	List<VentaDTO> getVentas();
	VentaDTO getVenta(Long id);
	VentaDTO saveVenta(VentaDTO vDTO);
	VentaDTO editVenta(Long id, VentaDTO vDTO);
	void deleteVenta(Long id);
	List<ProductoVentaDTO> getListaProductos(Long idVenta);
	VentasDiaDTO getVentasDelDia(LocalDate fecha);
	VentaMayorMontoDTO getVentaMayorMonto();
}

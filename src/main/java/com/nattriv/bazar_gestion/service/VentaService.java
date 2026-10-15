package com.nattriv.bazar_gestion.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nattriv.bazar_gestion.dto.ProductoVentaDTO;
import com.nattriv.bazar_gestion.dto.VentaDTO;
import com.nattriv.bazar_gestion.dto.VentaMayorMontoDTO;
import com.nattriv.bazar_gestion.dto.VentasDiaDTO;
import com.nattriv.bazar_gestion.exception.NotFoundException;
import com.nattriv.bazar_gestion.mapper.Mapper;
import com.nattriv.bazar_gestion.model.Cliente;
import com.nattriv.bazar_gestion.model.Producto;
import com.nattriv.bazar_gestion.model.Venta;
import com.nattriv.bazar_gestion.repository.IVentaRepository;


@Service
public class VentaService implements IVentaService {

	private IVentaRepository repo;
	private IClienteService cliServ;
	private IProductoService prodServ;
	
	public VentaService (IVentaRepository repo,ClienteService cliServ,IProductoService prodServ) {
		this.repo = repo;
		this.cliServ = cliServ;
		this.prodServ = prodServ;
	}
	
	@Override
	public List<VentaDTO> getVentas() {
		
		return repo.findAll()
				.stream()
				.map(Mapper::toDTO)
				.toList();
	}

	@Override
	public VentaDTO getVenta(Long id) {
		
		Venta venta = repo.findById(id).orElseThrow(() -> new NotFoundException("Venta de id: " + id + " no encontrada"));
		
		return Mapper.toDTO(venta);
	}

	@Transactional
	@Override
	public VentaDTO saveVenta(VentaDTO vDTO) {
		
		Cliente cliente = cliServ.getClienteEntidad(vDTO.getIdCliente());
		
		List<Producto> listaProd = vDTO.getListaProductos().stream()
	            					.map(p -> prodServ.getProductoEntidad(p.getCodigo_producto()))
	            					.collect(Collectors.toList());
		
		double total = listaProd.stream()
								.mapToDouble(Producto::getCosto)
								.sum();
				
		for (Producto p : listaProd) {
			prodServ.restarCantidad(p.getCodigo_producto());
		}
		
		Venta venta = Venta.builder()
							.fecha_venta(vDTO.getFecha_venta())
							.total(total)
							.listaProductos(listaProd)
							.unCliente(cliente)
							.build();
		
		return Mapper.toDTO(repo.save(venta));
	}

	@Transactional
	@Override
	public VentaDTO editVenta(Long id, VentaDTO vDTO) {
		
		Venta venta = repo.findById(id).orElseThrow(() -> new NotFoundException("Venta de id: " + id + " no encontrada"));
		
		for (Producto p : venta.getListaProductos()) {
			prodServ.sumarCantidad(p.getCodigo_producto());
		}
		
		List<Producto> listaProd = vDTO.getListaProductos().stream()
				.map(p -> prodServ.getProductoEntidad(p.getCodigo_producto()))
				.collect(Collectors.toList());
		
		for (Producto p : listaProd) {
			prodServ.restarCantidad(p.getCodigo_producto());
		}
		
		double total = listaProd.stream()
				.mapToDouble(Producto::getCosto)
				.sum();
		
		venta.setFecha_venta(vDTO.getFecha_venta());
		venta.setTotal(total);
		venta.setListaProductos(listaProd);
		venta.setUnCliente(cliServ.getClienteEntidad(vDTO.getIdCliente()));
		
		return Mapper.toDTO(repo.save(venta));
	}

	@Transactional
	@Override
	public void deleteVenta(Long id) {

		Venta venta = repo.findById(id).orElseThrow(() -> new NotFoundException("Venta no encontrada de id: " + id));
			
		for (Producto p : venta.getListaProductos()) {
			prodServ.sumarCantidad(p.getCodigo_producto());
		}

		repo.deleteById(id);
	}

	@Override
	public List<ProductoVentaDTO> getListaProductos(Long idVenta) {
		
		Venta venta = repo.findById(idVenta).orElseThrow(() -> new NotFoundException("Venta de id: " + idVenta + " no encontrada"));

		return venta.getListaProductos().stream()
					.map(Mapper::toProductoVentaDTO)
					.toList();
	}

	@Override
	public VentasDiaDTO getVentasDelDia(LocalDate fecha) {
		
		List<Venta> ventasDelDia = repo.findAll().stream()
	            						.filter(v -> v.getFecha_venta().equals(fecha))
	            						.toList();

	    double monto = ventasDelDia.stream()
						            .mapToDouble(Venta::getTotal)
						            .sum();

	    int cant = ventasDelDia.size();

	    return VentasDiaDTO.builder()
	            		.monto(monto)
	            		.cantidad(cant)
	            		.build();
	}

	@Override
	public VentaMayorMontoDTO getVentaMayorMonto() {
		
		Venta ventaMayor = repo.findAll().stream()
								.max(Comparator.comparingDouble(Venta::getTotal))
								.orElseThrow(() -> new NotFoundException("No hay ventas registradas"));
		
		return Mapper.toVentaMayorDTO(ventaMayor);
	}

}

package com.nattriv.bazar_gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nattriv.bazar_gestion.dto.ProductoDTO;
import com.nattriv.bazar_gestion.exception.BadRequestException;
import com.nattriv.bazar_gestion.exception.NotFoundException;
import com.nattriv.bazar_gestion.mapper.Mapper;
import com.nattriv.bazar_gestion.model.Producto;
import com.nattriv.bazar_gestion.repository.IProductoRepository;

@Service
public class ProductoService implements IProductoService {

	private IProductoRepository repo;
	
	public ProductoService (IProductoRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<ProductoDTO> getProductos() {
		
		return repo.findAll()
				.stream()
				.map(Mapper::toDTO)
				.toList();

	}

	@Override
	public ProductoDTO getProducto(Long id) {
		
		Producto prod = repo.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado de id: " + id));
		
		return Mapper.toDTO(prod);
		
	}

	@Override
	public ProductoDTO saveProducto(ProductoDTO pDTO) {
		
		Producto prod = Producto.builder()
						.nombre(pDTO.getNombre())
						.marca(pDTO.getMarca())
						.costo(pDTO.getCosto())
						.cantidad_disponible(pDTO.getCantidad_disponible())
						.build();
		
		return Mapper.toDTO(repo.save(prod));
	}

	@Override
	public ProductoDTO editProducto(Long id, ProductoDTO pDTO) {
		
		Producto prod = repo.findById(id)
							.orElseThrow(()-> new NotFoundException("Producto no encontrado de id: " + id));
		
		prod.setNombre(pDTO.getNombre());
		prod.setMarca(pDTO.getMarca());
		prod.setCosto(pDTO.getCosto());
		prod.setCantidad_disponible(pDTO.getCantidad_disponible());
		
		return Mapper.toDTO(repo.save(prod));
	}

	@Override
	public void deleteProducto(Long id) {

		if(!repo.existsById(id)) {
			throw new NotFoundException("Producto no encontrado de id: " + id);
		}
		
		repo.deleteById(id);
		
		
	}

	@Override
	public List<ProductoDTO> getProdCantMenor5() {
		
		return repo.findAll()
			.stream()
			.filter(p -> p.getCantidad_disponible() < 5)
			.map(Mapper::toDTO)
			.toList();
	
	}
	
	@Override
	public Producto getProductoEntidad(Long id) {
	    return repo.findById(id)
	            .orElseThrow(() -> new NotFoundException("Producto no encontrado de id: " + id));
	}
	
	@Override
	public void restarCantidad(Long id) {
		Producto prod = repo.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado de id: " + id));
		if(prod.getCantidad_disponible() < 1) throw new BadRequestException("No hay stock suficiente del producto: " + prod.getNombre());
		prod.setCantidad_disponible(prod.getCantidad_disponible() - 1);
		
		repo.save(prod);
	
	}
	
	@Override
	public void sumarCantidad(Long id) {
		Producto prod = repo.findById(id).orElseThrow(() -> new NotFoundException("Producto no encontrado de id: " + id));
		prod.setCantidad_disponible(prod.getCantidad_disponible() + 1);
		
		repo.save(prod);
	
	}

}

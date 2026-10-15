package com.nattriv.bazar_gestion.service;

import java.util.List;

import com.nattriv.bazar_gestion.dto.ProductoDTO;
import com.nattriv.bazar_gestion.model.Producto;

public interface IProductoService {

	List<ProductoDTO> getProductos();
	ProductoDTO getProducto(Long id);
	ProductoDTO saveProducto(ProductoDTO pDTO);
	ProductoDTO editProducto(Long id, ProductoDTO pDTO);
	void deleteProducto(Long id);
	List<ProductoDTO> getProdCantMenor5();
	Producto getProductoEntidad(Long id);
	void restarCantidad(Long id);
	void sumarCantidad(Long id);
}

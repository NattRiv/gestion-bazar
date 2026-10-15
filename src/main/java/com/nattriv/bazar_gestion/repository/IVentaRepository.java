package com.nattriv.bazar_gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nattriv.bazar_gestion.model.Venta;

@Repository
public interface IVentaRepository extends JpaRepository <Venta, Long>{

}

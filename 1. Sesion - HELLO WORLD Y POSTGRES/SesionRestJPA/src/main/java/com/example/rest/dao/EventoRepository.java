package com.example.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest.bean.Eventos;

@Repository
public interface EventoRepository extends JpaRepository<Eventos, Long> {
	
		List<Eventos> findByTitulo(String titulo);

}

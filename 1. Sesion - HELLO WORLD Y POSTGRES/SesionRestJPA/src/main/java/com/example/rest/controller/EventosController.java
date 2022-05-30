package com.example.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.bean.Eventos;
import com.example.rest.dao.EventoRepository;

@RestController
@RequestMapping("eventos")
public class EventosController {

	@Autowired
	private EventoRepository repository;
	
	@GetMapping("/listar")
	public List<Eventos> listar(){
		return repository.findAll();
	}
	@GetMapping("/listarPorTitulo")
	public List<Eventos> listarPorTitulo(@RequestParam(name = "titulo") String titulo){
		return repository.findByTitulo(titulo);
	}
	
}

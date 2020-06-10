package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Signos;
import com.mitocode.service.ISignosService;

@RestController
@RequestMapping("/signos")
public class SignosController {
	
	@Autowired
	private ISignosService service;
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Signos signos) throws Exception{
		Signos s = service.registrar(signos);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(s.getIdSignos()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Signos>>listarSignos() throws Exception{
		List<Signos> lista = service.listar();
		return new ResponseEntity<List<Signos>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Signos> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Signos signos = service.listarPorId(id);
		
		if(signos == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Signos>(signos, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Signos signos) throws Exception{
		Signos s = service.modificar(signos);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(s.getIdSignos()).toUri();
		return ResponseEntity.created(location).build();
		
		/*
		 * Signos s = null; try { s = service.modificar(signos); }catch(Exception e) {
		 * throw e; }
		 * 
		 * return new ResponseEntity<Object>(s, HttpStatus.OK);
		 */
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception{
		Signos signos = service.listarPorId(id);
		if(signos == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Signos>>listarPaginado(Pageable pageable){
		Page<Signos> signos = service.listarPaginado(pageable);
		return new ResponseEntity<Page<Signos>>(signos, HttpStatus.OK);
	}
	
}

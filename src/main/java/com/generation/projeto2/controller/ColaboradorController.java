package com.generation.projeto2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.projeto2.model.Colaborador;
import com.generation.projeto2.repository.ColaboradorRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/colaboradores")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ColaboradorController{
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@GetMapping
	public ResponseEntity<List<Colaborador>> getAll(){
		//SELECT * FROM tb_colaboradores;
		return ResponseEntity.ok(colaboradorRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> getById(@PathVariable Long id){
		return colaboradorRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Colaborador>> getAllByNome(@PathVariable String nome){		
		return ResponseEntity.ok(colaboradorRepository.findAllByNomeColaboradorContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Colaborador> post(@Valid @RequestBody Colaborador colaborador){
		return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorRepository.save(colaborador));
	}
	
	@PutMapping
	public ResponseEntity<Colaborador> put(@Valid @RequestBody Colaborador colaborador){
		
		if(colaborador.getId() == null)
			return ResponseEntity.badRequest().build();
		
		if(colaboradorRepository.existsById(colaborador.getId()))			
			return ResponseEntity.status(HttpStatus.OK).body(colaboradorRepository.save(colaborador));
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		Optional<Colaborador> categoria = colaboradorRepository.findById(id);
		
		if(categoria.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
		}
		colaboradorRepository.deleteById(id);
		
	}	
	
}

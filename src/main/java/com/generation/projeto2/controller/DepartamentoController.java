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
import com.generation.projeto2.model.Departamento;
import com.generation.projeto2.repository.DepartamentoRepository;

@RestController
@RequestMapping("/departamento")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    // GET - Listar todos os departamentos
    @GetMapping
    public ResponseEntity<List<Departamento>> getAll() {
        return ResponseEntity.ok(departamentoRepository.findAll());
    }

    // GET - Buscar departamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getById(@PathVariable Long id) {
        return departamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // GET - Buscar departamentos por nome
    @GetMapping("/nome/{nome}")
	public ResponseEntity<List<Departamento>> getAllByNome(@PathVariable String nome){		
		return ResponseEntity.ok(departamentoRepository.findAllByNomeContainingIgnoreCase(nome)); 
		
    }

    // POST - Criar novo departamento
    @PostMapping
    public ResponseEntity<Departamento> post(@RequestBody Departamento departamento) {
        return ResponseEntity.ok(departamentoRepository.save(departamento));
    }

    // PUT - Atualizar departamento
    @PutMapping
    public ResponseEntity<Departamento> put(@RequestBody Departamento departamento) {
        return ResponseEntity.ok(departamentoRepository.save(departamento));
    }
    
    // DELETE - Deleta o departamento por ID
    @DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		departamentoRepository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		departamentoRepository.deleteById(id);
	}	
}

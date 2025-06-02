package com.generation.projeto2.controller;

import java.math.BigDecimal;
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

import com.generation.projeto2.dto.CalculoSalario;
import com.generation.projeto2.model.Colaborador;
import com.generation.projeto2.repository.ColaboradorRepository;
import com.generation.projeto2.repository.DepartamentoRepository;
import com.generation.projeto2.service.ColaboradorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/colaboradores")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ColaboradorController{

    private final ColaboradorService colaboradorService;
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

    ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }
	
    // GET - Listar todos os colaboradores
	@GetMapping
	public ResponseEntity<List<Colaborador>> getAll(){
		//SELECT * FROM tb_colaboradores;
		return ResponseEntity.ok(colaboradorRepository.findAll());
	}
	
	// GET - Buscar colaborador por ID
	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> getById(@PathVariable Long id){
		return colaboradorRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	// GET - Buscar colaboradores por nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<?> getAllByNome(@PathVariable String nome) {
	    List<Colaborador> colaboradores = colaboradorRepository.findAllByNomeColaboradorContainingIgnoreCase(nome);

	    if (colaboradores.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Nenhum colaborador encontrado com o nome informado.");
	    }

	    return ResponseEntity.ok(colaboradores);
	}
	
	// POST - Calcula o salario com os descontos
	@PostMapping("/salario/{id}")
	public BigDecimal calcularSalario(@PathVariable Long id, @RequestBody CalculoSalario calculoSalario) {
		Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
		if(colaborador.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return colaboradorService.calcularSalario(id, calculoSalario);
	}
	
	// POST - Criar novo colaborador
	@PostMapping
	public ResponseEntity<?> post(@Valid @RequestBody Colaborador colaborador) {

	    Long idDepartamento = colaborador.getDepartamento() != null ? colaborador.getDepartamento().getId() : null;

	    if (idDepartamento == null || !departamentoRepository.existsById(idDepartamento))
	        return ResponseEntity.badRequest().body("Erro: o departamento é obrigatório ou não existe.");

	    return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorRepository.save(colaborador));
	}
	
	// PUT - Atualizar colaborador
	@PutMapping
	public ResponseEntity<?> put(@Valid @RequestBody Colaborador colaborador) {

		if (colaborador.getId() == null || !colaboradorRepository.existsById(colaborador.getId()))
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Erro: o ID é obrigatório ou o colaborador não foi encontrado.");

	    Long idDepartamento = colaborador.getDepartamento() != null ? colaborador.getDepartamento().getId() : null;

	    if (idDepartamento == null || !departamentoRepository.existsById(idDepartamento))
	        return ResponseEntity.badRequest().body("Erro: o departamento é obrigatório ou não existe.");

	    return ResponseEntity.ok(colaboradorRepository.save(colaborador));
	}

	
	// DELETE - Deleta o colaborador por ID
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		
		colaboradorRepository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		colaboradorRepository.deleteById(id);
	}	
	
}

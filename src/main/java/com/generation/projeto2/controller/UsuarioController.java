package com.generation.projeto2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.projeto2.model.Usuario;
import com.generation.projeto2.repository.UsuarioRepository;
import com.generation.projeto2.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	   @Autowired
	   private UsuarioService usuarioService; 
	
	    @Autowired
	    private UsuarioRepository usuarioRepository; // Pode ser útil para buscas simples

	    @GetMapping("/todos") // Endpoint para listar todos, pode precisar de proteção
	    public ResponseEntity<List<Usuario>> getAll() {
	        return ResponseEntity.ok(usuarioRepository.findAll());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
	        return usuarioRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }
	   	
	    @GetMapping("/usuario/{usuario}")
		public ResponseEntity<List<Usuario>> getAllByUsuario(@PathVariable String usuario){		
			return ResponseEntity.ok(usuarioRepository.findAllByUsuarioContainingIgnoreCase(usuario));
		}

	    @PostMapping("/cadastrar")
	    public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
	        return usuarioService.cadastrarUsuario(usuario)
	                .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
	                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	    }

	     
	    @PutMapping("/atualizar")
	    public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario) {
	        return usuarioService.atualizarUsuario(usuario)
	                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
}




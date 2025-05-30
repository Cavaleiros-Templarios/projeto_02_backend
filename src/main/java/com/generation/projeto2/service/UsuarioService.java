package com.generation.projeto2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.generation.projeto2.model.Usuario;
import com.generation.projeto2.repository.UsuarioRepository;

public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) { 
			return Optional.empty();
		}
		
		return Optional.ofNullable(usuarioRepository.save(usuario));
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			
			return Optional.ofNullable(usuarioRepository.save(usuario));
		}
		return Optional.empty();
	}	
}

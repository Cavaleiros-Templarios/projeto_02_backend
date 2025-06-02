package com.generation.projeto2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.projeto2.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
	
	List<Usuario> findAllByUsuarioContainingIgnoreCase(String usuario);
	Optional<Usuario> findByUsuario(String usuario);
	
	
}

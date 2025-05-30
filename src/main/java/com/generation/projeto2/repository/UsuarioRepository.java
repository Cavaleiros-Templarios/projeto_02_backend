package com.generation.projeto2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.projeto2.model.Departamento;
import com.generation.projeto2.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
	
	List<Departamento> findAllByNomeContainingIgnoreCase(String nome);
	
}

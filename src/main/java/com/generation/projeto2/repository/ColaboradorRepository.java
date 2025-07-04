package com.generation.projeto2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.projeto2.model.Colaborador;

@Repository
public interface ColaboradorRepository  extends JpaRepository<Colaborador, Long> {
	
	List<Colaborador> findAllByNomeColaboradorContainingIgnoreCase(String nomeColaborador);

}

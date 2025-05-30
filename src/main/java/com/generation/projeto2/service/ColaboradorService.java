package com.generation.projeto2.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.projeto2.dto.CalculoSalario;
import com.generation.projeto2.model.Colaborador;
import com.generation.projeto2.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
	
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	public BigDecimal calcularSalario(Long id, CalculoSalario calculoSalario){
		Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
		double salario = colaborador.get().getSalario().doubleValue();
		double salarioFinal = (salario/160) * calculoSalario.getHorasTrabalhadas() + calculoSalario.getBonus() - calculoSalario.getDescontos();
		return BigDecimal.valueOf(salarioFinal);
	}
	
}

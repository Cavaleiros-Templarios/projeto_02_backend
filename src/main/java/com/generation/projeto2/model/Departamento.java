package com.generation.projeto2.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_departamentos")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
    @NotBlank(message = "O atributo nome é obrigatorio")
    @Size(min = 5, max = 100, message = "O nome deve ter no minimo 5 caracteres e no maximo 100")
    private String nome;
	
	@NotNull(message = "O orçamento é obrigatório!")
    @Positive(message = "O orçamento deve ser positivo!")
    private BigDecimal orcamento;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departamento", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("departamento")
	private List<Colaborador> colaborador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(BigDecimal orcamento) {
		this.orcamento = orcamento;
	}

	public List<Colaborador> getColaborador() {
		return colaborador;
	}

	public void setColaborador(List<Colaborador> colaborador) {
		this.colaborador = colaborador;
	}
	
	
}

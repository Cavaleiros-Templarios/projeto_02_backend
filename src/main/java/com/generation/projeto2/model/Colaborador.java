package com.generation.projeto2.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_colaboradores")
public class Colaborador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O salário é obrigatório!")
    @Positive(message = "O salário deve ser positivo!")
    private BigDecimal salario;

	@Column(length = 100)
	@NotBlank(message = "O atributo cargo é obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo cargo deve ter entre 5 e 100 caracteres")
	private String cargo;
	
	@Column(length = 100)
    @NotBlank(message = "O atributo nome é obrigatorio")
    @Size(min = 5, max = 100, message = "O nome deve ter no minimo 5 caracteres e no maximo 100")
    private String nome_colaborador;
	
	@Past(message = "A data de nascimento precisar ser no passado")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_nascimento;
	
	@ManyToOne
	@JsonIgnoreProperties("colaborador")
	private Departamento departamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNome_colaborador() {
		return nome_colaborador;
	}

	public void setNome_colaborador(String nome_colaborador) {
		this.nome_colaborador = nome_colaborador;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
}

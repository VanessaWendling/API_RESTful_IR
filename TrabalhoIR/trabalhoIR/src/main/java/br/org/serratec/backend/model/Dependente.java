package br.org.serratec.backend.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


import io.swagger.annotations.ApiModelProperty;

@Entity
public class Dependente extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dependente")
	@ApiModelProperty(value = "Identificador unico do Dependente")
	private Long id;
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value = "Tipo de parentesco do Dependente", required = true)
	private Parentesco parentesco;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	public Dependente() {

	}

	public Dependente(Long id, Parentesco parentesco, Funcionario funcionario) {
		super();
		this.id = id;
		this.parentesco = parentesco;
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

}

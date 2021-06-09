package br.org.serratec.backend.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;



@Entity
public class Funcionario extends Pessoa{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_funcionario")
	@ApiModelProperty(value = "Identificador único do Funcionário")
	private Long id;
	
	@Column (name = "salario_bruto")
	@ApiModelProperty(value = "Salário Bruto", required = true)
	private Double salarioBruto;
	
	@Column (name = "desconto_INSS")
	@ApiModelProperty(value = "Desconto INSS", required = true)
	private Double descontoInss;
	
	@Column (name = "desconto_IR")
	@ApiModelProperty(value = "Desconto do IR", required = true)
	private Double descontoIR;
	
	@Column (name = "salario_liquido")
	@ApiModelProperty(value = "Salário Liquido", required = true)
	private Double salarioLiquido;
	
	@JsonManagedReference
	@OneToMany (mappedBy = "funcionario", cascade = CascadeType.ALL)
	@Size(max = 3)
	@ApiModelProperty(value = "Dependentes do Funcionário")
	private Set<Dependente> dependentes = new HashSet<Dependente>();
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}


	public Funcionario(Long id, Double salarioBruto, Double descontoInss, Double descontoIR, Double salarioLiquido,
			Set<Dependente> dependentes) {
		super();
		this.id = id;
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIR = descontoIR;
		this.salarioLiquido = salarioLiquido;
		this.dependentes = dependentes;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Double getSalarioBruto() {
		return salarioBruto;
	}


	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}


	public Double getDescontoInss() {
		return descontoInss;
	}


	public void setDescontoInss(Double descontoInss) {
		this.descontoInss = descontoInss;
	}


	public Double getDescontoIR() {
		return descontoIR;
	}


	public void setDescontoIR(Double descontoIR) {
		this.descontoIR = descontoIR;
	}


	public Double getSalarioLiquido() {
		return salarioLiquido;
	}


	public void setSalarioLiquido(Double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}


	public Set<Dependente> getDependentes() {
		return dependentes;
	}


	public void setDependentes(Set<Dependente> dependentes) {
		this.dependentes = dependentes;
	}
	
}

package br.org.serratec.backend.dto;


import br.org.serratec.backend.model.Funcionario;

public class FuncionarioMostrarDTO {
	private String nome;
	private Double salario;

	public FuncionarioMostrarDTO() {
		// TODO Auto-generated constructor stub
	}

	public FuncionarioMostrarDTO(Funcionario funcionario, Double salario) {
		this.nome = funcionario.getNome();
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}

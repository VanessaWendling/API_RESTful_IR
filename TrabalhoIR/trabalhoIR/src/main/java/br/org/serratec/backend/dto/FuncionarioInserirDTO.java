package br.org.serratec.backend.dto;

import java.time.LocalDate;

import br.org.serratec.backend.model.Funcionario;

public class FuncionarioInserirDTO {
	protected String nome;
	protected String cpf;
	protected LocalDate dataNascimento;
	private double salarioBruto;

	public FuncionarioInserirDTO() {
		// TODO Auto-generated constructor stub
	}

	public FuncionarioInserirDTO(Funcionario funcionario) {
		this.nome = funcionario.getNome();
		this.cpf = funcionario.getCpf();
		this.dataNascimento = funcionario.getDataNascimento();
		this.salarioBruto = funcionario.getSalarioBruto();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

}

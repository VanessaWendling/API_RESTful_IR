package br.org.serratec.backend.repository;

import br.org.serratec.backend.exception.DependenteException;
import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.model.Funcionario;

public interface CalcularImposto {
	double calculaSalarioLiquido(Funcionario funcionario);
	double calculaIR(Funcionario funcionario, Integer cont);
	double dependenteIR = 189.59;
	double calculaInss(Funcionario funcionario);
	void verificarIdade(Dependente dependente) throws DependenteException;
}
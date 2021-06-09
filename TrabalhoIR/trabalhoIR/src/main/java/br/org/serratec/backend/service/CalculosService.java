package br.org.serratec.backend.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import br.org.serratec.backend.exception.DependenteException;
import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.repository.CalcularImposto;

@Service
public class CalculosService implements CalcularImposto{
	

	@Override
	public double calculaIR(Funcionario funcionario, Integer dep) {		
		//System.out.println(funcionario.getSalarioBruto());
		double calculoBase = funcionario.getSalarioBruto() - (dep * dependenteIR) - funcionario.getDescontoInss();
		if (calculoBase < 1903.98) {
			funcionario.setDescontoIR(0.0);
			return calculoBase;
		}
		else if (calculoBase >= 1903.98 && calculoBase <= 2826.65) {
			funcionario.setDescontoIR((((funcionario.getSalarioBruto() - (dep * dependenteIR) - funcionario.getDescontoInss())*7.5)/100) - 142.80);
			return funcionario.getDescontoIR();
		}
		else if (calculoBase >= 2826.66 && calculoBase <= 3751.05) {
			funcionario.setDescontoIR((((funcionario.getSalarioBruto() - (dep * dependenteIR) - funcionario.getDescontoInss())*15)/100) - 354.80);
			return funcionario.getDescontoIR();
		}
		else if (calculoBase >= 3751.06 && calculoBase <= 4664.68) {
			funcionario.setDescontoIR((((funcionario.getSalarioBruto() - (dep * dependenteIR) -funcionario.getDescontoInss())*22.5)/100) - 636.13);
			return funcionario.getDescontoIR();
		}else {
			funcionario.setDescontoIR((((funcionario.getSalarioBruto() - (dep * dependenteIR) - funcionario.getDescontoInss())*27.5)/100) - 869.36);
			return funcionario.getDescontoIR();
		}
	}
	
	@Override
	public double calculaInss(Funcionario funcionario) {
		if (funcionario.getSalarioBruto() <= 1100) {
			funcionario.setDescontoInss((funcionario.getSalarioBruto() * 7.5)/100);
		}
		else if (funcionario.getSalarioBruto() >= 1100.01 && funcionario.getSalarioBruto()<= 2203.48) {
			funcionario.setDescontoInss(((funcionario.getSalarioBruto() * 9)/100) - 16.5);
			System.out.println(funcionario.getDescontoInss());
		}
		else if (funcionario.getSalarioBruto() >= 2203.49 && funcionario.getSalarioBruto() <= 3305.22) {
			funcionario.setDescontoInss(((funcionario.getSalarioBruto() * 12)/100) - 82.61);
			
		}
		else if (funcionario.getSalarioBruto() >= 3305.22 && funcionario.getSalarioBruto() <= 6433.57) {
			funcionario.setDescontoInss(((funcionario.getSalarioBruto() * 14)/100) - 148.72);
		} else{
			funcionario.setDescontoInss(751.97);
		}
		return funcionario.getDescontoInss();
	}

	@Override
	public double calculaSalarioLiquido(Funcionario funcionario) {
		return funcionario.getSalarioBruto() - funcionario.getDescontoInss() - funcionario.getDescontoIR();
	}

	@Override
	public void verificarIdade(Dependente dependente) throws DependenteException{
        LocalDate data = LocalDate.now();
        Period idade = Period.between(dependente.getDataNascimento(), data);

        if (idade.getYears() > 18) {
            throw new DependenteException("Idade maior que 18!");
        }
    }
	
}

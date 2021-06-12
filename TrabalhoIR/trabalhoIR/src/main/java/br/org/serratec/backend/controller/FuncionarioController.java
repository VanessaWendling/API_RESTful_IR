package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.org.serratec.backend.exception.DependenteException;
import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.model.Funcionario;
import br.org.serratec.backend.repository.DependenteRepository;
import br.org.serratec.backend.repository.FuncionarioRepository;
import br.org.serratec.backend.service.CalculosService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private DependenteRepository dependenteRepository;

	/*
	 * @Autowired private FuncionarioService funcionarioService;
	 */

	@Autowired
	private CalculosService calculosService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar o Funcionário", notes = "Cadastro Funcionário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Funcionário cadastrado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public Funcionario inserir(@Valid @RequestBody Funcionario funcionario) throws DependenteException {
		funcionario.setDescontoInss(calculosService.calculaInss(funcionario));
		calculosService.calculaIR(funcionario, funcionario.getDependentes().size());
		for (Dependente dependente : funcionario.getDependentes()) {
			calculosService.verificarIdade(dependente);
		}
		funcionario.setSalarioLiquido(calculosService.calculaSalarioLiquido(funcionario));
		return funcionarioRepository.save(funcionario);
	}

	@GetMapping("/dependentes")
	@ApiOperation(value = "Listar Dependentes", notes = "Listar Dependentes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dependentes listados com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<Dependente>> listardep() {
		List<Dependente> dependente = dependenteRepository.findAll();
		return ResponseEntity.ok(dependente);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Listar determinado Funcionário", notes = "Listar determinado Funcionário")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Funcionário listado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Funcionario> buscar(@PathVariable Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		if (funcionario.isPresent()) {
			return ResponseEntity.ok(funcionario.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	@ApiOperation(value = "Listar todos os Funcionários", notes = "Listar todos Funcinários")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Funcionários listados com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionario = funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionario);
	}

	@PutMapping("{id}")
	@ApiOperation(value = "Atualizar determinado Funcionário", notes = "Atualizar Funcionário")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Funcionário atualizado com sucesso"),
			@ApiResponse(code = 201, message = "Funcionário criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario)
			throws DependenteException {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionario.setId(id);
		funcionario.setDescontoInss(calculosService.calculaInss(funcionario));
		calculosService.calculaIR(funcionario, funcionario.getDependentes().size());
		for (Dependente dependente : funcionario.getDependentes()) {
			calculosService.verificarIdade(dependente);
		}
		funcionario.setSalarioLiquido(calculosService.calculaSalarioLiquido(funcionario));
		funcionario = funcionarioRepository.save(funcionario);
		return ResponseEntity.ok(funcionario);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Deletar determinado Funcionário", notes = "Deletar Funcionário")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Funcionário deletado com sucesso"),
			@ApiResponse(code = 204, message = "Sem Conteúdo"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

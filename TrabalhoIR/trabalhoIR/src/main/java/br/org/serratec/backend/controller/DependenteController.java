package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

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

import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.repository.DependenteRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/dependentes")
public class DependenteController {
	@Autowired
	private DependenteRepository dependenteRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar o Dependente", notes = "Cadastro Dependente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Dependente cadastrado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public Dependente inserir(@RequestBody Dependente dependente) {
		return dependenteRepository.save(dependente);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Listar determinado Dependente", notes = "Listar determinado Dependente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dependente listado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Dependente> buscar(@PathVariable Long id) {
		Optional<Dependente> dependente = dependenteRepository.findById(id);
		if (dependente.isPresent()) {
			return ResponseEntity.ok(dependente.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	@ApiOperation(value = "Listar todos os dependente", notes = "Listar todos Funcinários")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Veiculos listados com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<Dependente>> listar() {
		List<Dependente> dependente = dependenteRepository.findAll();
		return ResponseEntity.ok(dependente);
	}

	@PutMapping("{id}")
	@ApiOperation(value = "Atualizar determinado Dependente", notes = "Atualizar Dependente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dependente atualizado com sucesso"),
			@ApiResponse(code = 201, message = "Dependente criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Dependente> atualizar(@PathVariable Long id, @RequestBody Dependente dependente) {
		if (!dependenteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dependente.setId(id);
		dependente = dependenteRepository.save(dependente);
		return ResponseEntity.ok(dependente);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Deletar determinado Dependente", notes = "Deletar Dependente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Dependente deletado com sucesso"),
			@ApiResponse(code = 204, message = "Sem Conteúdo"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		if (!dependenteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dependenteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
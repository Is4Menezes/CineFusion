package com.isabella.filmFusion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isabella.filmFusion.model.Pessoa;
import com.isabella.filmFusion.service.PessoaService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
	
	private final PessoaService pessoaService;
	
	public PessoaController(PessoaService ps) {
		this.pessoaService = ps;
	}
	
	@PostMapping({"", "/"})
	public ResponseEntity<Pessoa> inserir(@Valid @RequestBody Pessoa p) {
		System.out.println("Recebido: " + p);
        return ResponseEntity.ok(pessoaService.inserir(p));
    }
	
	@GetMapping("/{email}/{senha}/authenticate")
	public ResponseEntity<Pessoa> autenticar(@PathVariable String email, @PathVariable String senha) {
	    Pessoa pessoa = pessoaService.autenticar(email, senha);
	    if (pessoa == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	    return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("/{email}/exists")
    public ResponseEntity<Boolean> verificarLogin(@PathVariable String email) {
        boolean exists = pessoaService.verificarEmail(email);
        return ResponseEntity.ok(exists);
    }
	
	@GetMapping("/{cpf}/cpf/exists")
    public ResponseEntity<Boolean> verificarCpf(@PathVariable String cpf) {
        boolean exists = pessoaService.verificarCpf(cpf);
        return ResponseEntity.ok(exists);
    }
	
	@GetMapping({"/", ""})
	public List<Pessoa> consultarTodos() {
		List<Pessoa> pessoaList = pessoaService.consultar();
		return pessoaList;
	}
	
	@GetMapping("/{idPessoa}")
	public Pessoa consultarPorId(@PathVariable int idPessoa) {
		Pessoa pessoa = pessoaService.consultarPorId(idPessoa);
		return pessoa;
	}
	
	@PutMapping({"", "/"})
	public Pessoa alterar(@RequestBody Pessoa p) {
		pessoaService.alterar(p);
		return p;
	}
	
	@DeleteMapping("/{idPessoa}")
	public Pessoa excluir(@PathVariable int idPessoa) {
		Pessoa pessoa = pessoaService.consultarPorId(idPessoa);
		if (pessoa == null) {
			throw new RuntimeException("Não existe uma pessoa com esse id...");
		}
		pessoaService.excluir(idPessoa);
		return pessoa;
	}
	
}

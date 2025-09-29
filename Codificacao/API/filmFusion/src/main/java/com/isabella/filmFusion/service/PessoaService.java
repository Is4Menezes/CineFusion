package com.isabella.filmFusion.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.isabella.filmFusion.dao.PessoaDAO;
import com.isabella.filmFusion.model.Pessoa;

@Service
public class PessoaService {
	private final PessoaDAO pessoaDao;

	public PessoaService(Jdbi jdbi) {
		this.pessoaDao = jdbi.onDemand(PessoaDAO.class);
	}

	public Pessoa inserir(Pessoa p) {
		if (p.getIdPessoa() != 0) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id - informacao ilegal.");
		}

		int idPessoa = pessoaDao.inserir(p);
		p.setIdPessoa(idPessoa);
		return p;
	}

	public Pessoa autenticar(String email, String senha) {
		Pessoa pessoa = pessoaDao.verificarLogin(email);
		if (pessoa != null && pessoa.getSenha().equals(senha)) {
			return pessoa;
		}
		return null; // Se a autenticação falhar
	}

	public boolean verificarEmail(String email) {
		Pessoa pessoa = pessoaDao.verificarLogin(email);
		return pessoa != null; // Retorna true se o login existir
	}
	
	public boolean verificarCpf(String cpf) {
		Pessoa pessoa = pessoaDao.verificarCpf(cpf);
		return pessoa != null; // Retorna true se o login existir
	}

	public List<Pessoa> consultar() {
		return pessoaDao.consultar();
	}

	public Pessoa consultarPorId(int idPessoa) {
		return pessoaDao.consultarPorId(idPessoa);
	}

	public void alterar(Pessoa p) {
		pessoaDao.alterar(p);
	}

	public void excluir(int idPessoa) {
		pessoaDao.excluir(idPessoa);
	}
}

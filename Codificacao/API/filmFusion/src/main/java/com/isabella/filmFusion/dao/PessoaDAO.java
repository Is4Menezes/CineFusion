package com.isabella.filmFusion.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import com.isabella.filmFusion.model.Pessoa;

@Repository
@RegisterBeanMapper(Pessoa.class)
public interface PessoaDAO {
	
	@GetGeneratedKeys
	@SqlUpdate("insert into pessoa (nome, cpf, dataNascimento, email, senha, status)" 
	+ " values (:nome, :cpf, :dataNascimento, :email, :senha, true);")
	public int inserir(@BindBean Pessoa p);
	
	@SqlQuery("select * from pessoa where email = :email;")
	public Pessoa verificarLogin(@Bind String email);
	
	@SqlQuery("select * from pessoa where cpf = :cpf;")
	public Pessoa verificarCpf(@Bind String cpf);
	
	@SqlQuery("select * from pessoa order by nome;")
	public List<Pessoa> consultar();
	
	@SqlQuery("select * from pessoa where idPessoa = :idPessoa;")
	public Pessoa consultarPorId(@Bind int idPessoa);
	
	@SqlQuery("select * from pessoa where nome like :nome;")
	public Pessoa consultarPorNome(@Bind String nome);
	
	@SqlUpdate("update pessoa set nome = :nome, cpf = :cpf," 
			+ "dataNascimento = :dataNascimento, email = :email, senha = :senha, status = :status, where id = :id;")
	public int alterar(@BindBean Pessoa p);
	
	@SqlUpdate("delete from pessoa where idPessoa = :idPessoa")
	public int excluir(@Bind int idPessoa);
}

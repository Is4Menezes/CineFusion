package com.isabella.filmFusion.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import com.isabella.filmFusion.model.Cinema;

@Repository
@RegisterBeanMapper(Cinema.class)
public interface CinemaDAO {
	
	@GetGeneratedKeys
	@SqlUpdate("insert into cinema (nome, cnpj, email, senha, status, idCidade)" 
	+ " values (:nome, :cnpj, :email, :senha, true, :idCidade);")
	public int inserir(@BindBean Cinema c);
	
	@SqlQuery("select * from cinema order by nome;")
	public List<Cinema> consultar();
	
	@SqlQuery("select * from cinema where idCinema = :idCinema;")
	public Cinema consultarPorId(@Bind int id);
	
	@SqlQuery("select * from cinema where nome like :nome;")
	public Cinema consultarPorNome(@Bind String nome);
	
	@SqlUpdate("update cinema set nome = :nome, cnpj = :cnpj, email = :email, "
			+ "senha = :senha, status = :status, idCidade = :idCidade where idCinema = :idCinema;")
	public int alterar(@BindBean Cinema c);
	
	@SqlUpdate("delete from cinema where idCinema = :idCinema")
	public int excluir(@Bind int id);
}

package com.isabella.filmFusion.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.isabella.filmFusion.dao.CinemaDAO;
import com.isabella.filmFusion.model.Cinema;

@Service
public class CinemaService {
	private final CinemaDAO cinemaDao;
	
	public CinemaService(Jdbi jdbi) {
		this.cinemaDao = jdbi.onDemand(CinemaDAO.class);
	}
	
	public Cinema inserir(Cinema c) {		
		if (c.getIdCidade() != 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id - informacao ilegal.");
        }

        int idCinema = cinemaDao.inserir(c);
        c.setIdCinema(idCinema);
        return c;
	}
	
	public List<Cinema> consultar() {
		return cinemaDao.consultar();
	}
	
	public Cinema consultarPorId(int idCinema) {
		return cinemaDao.consultarPorId(idCinema);
	}
	
	public void alterar(Cinema c) {
		cinemaDao.alterar(c);
	}
	
	public void excluir(int idCinema) {
		cinemaDao.excluir(idCinema);
	}
}

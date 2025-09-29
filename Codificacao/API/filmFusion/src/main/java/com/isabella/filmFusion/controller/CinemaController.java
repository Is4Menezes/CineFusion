package com.isabella.filmFusion.controller;

import java.util.List;

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

import com.isabella.filmFusion.model.Cinema;
import com.isabella.filmFusion.service.CinemaService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api/cinema")
public class CinemaController {
	
	private final CinemaService cinemaService;
	
	public CinemaController(CinemaService cs) {
		this.cinemaService = cs;
	}
	
	@PostMapping({"", "/"})
	public ResponseEntity<Cinema> inserir(@Valid @RequestBody Cinema c) {
        return ResponseEntity.ok(cinemaService.inserir(c));
    }
	
	@GetMapping({"/", ""})
	public List<Cinema> consultarTodos() {
		List<Cinema> cinemaList = cinemaService.consultar();
		return cinemaList;
	}
	
	@GetMapping("/{idCinema}")
	public Cinema consultarPorId(@PathVariable int idCinema) {
		Cinema cinema = cinemaService.consultarPorId(idCinema);
		return cinema;
	}
	
	@PutMapping({"", "/"})
	public Cinema alterar(@RequestBody Cinema c) {
		cinemaService.alterar(c);
		return c;
	}
	
	@DeleteMapping("/{idCinema}")
	public Cinema excluir(@PathVariable int idCinema) {
		Cinema cinema = cinemaService.consultarPorId(idCinema);
		if (cinema == null) {
			throw new RuntimeException("Não existe um cinema com esse id...");
		}
		cinemaService.excluir(idCinema);
		return cinema;
	}
	
}

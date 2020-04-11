package com.demo.restservice.controllers;

import com.demo.restservice.domain.Film;
import com.demo.restservice.repositories.FilmRepository;
import com.demo.restservice.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FilmController {
    private FilmService filmsService;
    private FilmRepository filmRepository;

    // Setter based dependency injection
    @Autowired
    public void setFilmsService(FilmService filmsService) {
        this.filmsService = filmsService;
    }

    // Inject the JpaRepository
    @Autowired
    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/api/films";
    }

    // Returns JSON response List of films
    @GetMapping("/api/films")
    public List<Film> listFilms() {
        // Using CRUD repository override of findAll()
       // return filmsService.listAll();
        return filmRepository.findAll();
    }

    // Returns JSON response of Film with specific id
    @GetMapping("/api/films/{id}")
    public Film getFilmById(@PathVariable String id) {
        Optional<Film> var = filmRepository.findById(Long.valueOf(id));
        return var.get();
    }

    @PostMapping("/api/")
    public void insertFilm() {

    }
}
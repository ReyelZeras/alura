package com.example.demo.controller;

import com.example.demo.dto.EpisodioDto;
import com.example.demo.dto.SerieDto;
import com.example.demo.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDto> obterSeries(){
        return service.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDto> obterTop5Series(){
        return service.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDto> obterLancamentos() {
        return service.obterLancamentos();
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDto> obterTodasTemporadas(@PathVariable Long id){
        return service.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}")
    public SerieDto obterPorId(@PathVariable Long id) {
        return service.obterPorID(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDto> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero){
        return service.obterTemporadasPorNumero(id, numero);

    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDto> obterSeriesPorCategoria(@PathVariable String nomeGenero){
        return service.obterSeriesPorCategoria(nomeGenero);
    }
}



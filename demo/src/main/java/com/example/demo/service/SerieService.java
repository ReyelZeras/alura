package com.example.demo.service;

import com.example.demo.dto.EpisodioDto;
import com.example.demo.dto.SerieDto;
import com.example.demo.model.Categoria;
import com.example.demo.model.Serie;
import com.example.demo.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;

    public List<SerieDto> obterTodasAsSeries(){
        return converteDados(repository.findAll());
    }

    public List<SerieDto> obterTop5Series() {
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());

    }

    public List<SerieDto> obterLancamentos() {
        return converteDados(repository.lancamentosMaisRecentes());
    }

    public List<EpisodioDto> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDto(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDto> obterTemporadasPorNumero(Long id, Long numero) {
        return repository.obterEpisodiosPorTemporada(id, numero)
                .stream()
                .map(e -> new EpisodioDto(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());

    }

    public List<SerieDto> obterSeriesPorCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteDados(repository.findByGenero(categoria));
    }


    public SerieDto obterPorID(Long id){
        Optional<Serie> serie = repository.findById(id);

        if (serie.isPresent()){
            Serie s = serie.get();
            return new SerieDto(s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getAvaliacao(),
                    s.getGenero(),
                    s.getAtores(),
                    s.getPoster(),
                    s.getSinopse());
        }
        return null;
    }

    private List<SerieDto> converteDados(List<Serie> series){
        return series.stream().map(s ->
                        new SerieDto(s.getId(),
                                s.getTitulo(),
                                s.getTotalTemporadas(),
                                s.getAvaliacao(),
                                s.getGenero(),
                                s.getAtores(),
                                s.getPoster(),
                                s.getSinopse()
                        )).collect(Collectors.toList());
    }
}

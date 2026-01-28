package com.example.demo.dto;

import com.example.demo.model.Categoria;

public record SerieDto(Long id,
                       String titulo,
                       Integer totalTemporada,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String poster,
                       String sinopse) {
}

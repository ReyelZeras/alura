package org.example.excecao;

public class ErroDeCOnversaoDeAnoException extends RuntimeException {
    private String mensagem;
    public ErroDeCOnversaoDeAnoException(String s) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}

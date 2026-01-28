package com.example.demo.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        if (texto == null || texto.isBlank()) return "";

        try {
            // Codificamos o texto da sinopse
            String textoCodificado = URLEncoder.encode(texto, StandardCharsets.UTF_8);

            // O caractere '|' precisa ser escrito como %7C para o Java não dar erro
            String url = "https://api.mymemory.translated.net/get?q=" + textoCodificado + "&langpair=en%7Cpt-br";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject obj = new JSONObject(response.body());

            // Verificamos se a resposta veio correta
            if (obj.has("responseData")) {
                return obj.getJSONObject("responseData").getString("translatedText").trim();
            }

            return texto;

        } catch (Exception e) {
            System.err.println("Erro na tradução MyMemory: " + e.getMessage());
            return texto;
        }
    }
}
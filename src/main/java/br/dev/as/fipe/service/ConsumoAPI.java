package br.dev.as.fipe.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    private final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJlYmViZTdhNC00NzMwLTQ1YWQtYTBhNi04ZGYxYjhiZjQyN2EiLCJlbWFpbCI6InNjaG5laWRfYWxlc3NhbmRyb0Bob3RtYWlsLmNvbSIsImlhdCI6MTcyMDU1MTk1Mn0.5lDOcs1tgoZmyUAt_5wWrr9BKOsJ5R7du_foOod8f60";
    public String obterDados(String endereco) throws RuntimeException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .header("X-Subscription-Token",token)
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

//            if(response.statusCode() == 200)
//                return response.body();

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(e);

        }

        return response.body();
    }
}

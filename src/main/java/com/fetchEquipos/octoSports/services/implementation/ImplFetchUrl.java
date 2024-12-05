package com.fetchEquipos.octoSports.services.implementation;

import com.fetchEquipos.octoSports.config.ConfigApiKey;
import com.fetchEquipos.octoSports.consts.Constant;
import com.fetchEquipos.octoSports.services.interfaces.IFetchUrl;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Data
@AllArgsConstructor
@Builder
public class ImplFetchUrl<T> implements IFetchUrl<T> {

    private final ConfigApiKey configApiKey;
    private Gson gson;

    @Override
    public T fetch(String url,Type type) {
        T teams = null;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-host", Constant.XRAPIDAPIHOST)
                .header("x-rapidapi-key", configApiKey.getApiKey())
                .build();

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> responseApi = client.send(request, HttpResponse.BodyHandlers.ofString());
            teams = gson.fromJson(responseApi.body(), type);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return teams;
    }
}

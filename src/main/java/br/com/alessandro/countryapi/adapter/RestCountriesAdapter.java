package br.com.alessandro.countryapi.adapter;

import br.com.alessandro.countryapi.dto.CountryApiResponse;
import br.com.alessandro.countryapi.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class RestCountriesAdapter implements CountryProvider {

    private final RestClient restClient;

    public RestCountriesAdapter(
            RestClient.Builder builder,
            @Value("${restcountries.base-url}") String baseUrl) {

        this.restClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public List<CountryApiResponse> findByName(String name) {

        List<CountryApiResponse> response = restClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3.1/name/{name}")
                                .build(name))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        if (response == null || response.isEmpty()) {
            throw new CountryNotFoundException(
                    "País não encontrado: " + name);
        }

        return response;
    }

    @Override
    public List<CountryApiResponse> findByRegion(String region) {

        List<CountryApiResponse> response = restClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3.1/region/{region}")
                                .build(region))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        if (response == null || response.isEmpty()) {
            throw new CountryNotFoundException(
                    "Nenhum país encontrado para a região: " + region);
        }

        return response;
    }
}
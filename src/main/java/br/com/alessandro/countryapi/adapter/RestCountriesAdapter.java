package br.com.alessandro.countryapi.adapter;

import br.com.alessandro.countryapi.dto.CountryApiResponse;
import br.com.alessandro.countryapi.dto.RestCountriesResponse;
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
            @Value("${restcountries.base-url}") String baseUrl,
            @Value("${restcountries.api-key}") String apiKey) {

        this.restClient = builder
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    @Override
    public List<CountryApiResponse> findByName(String name) {

        RestCountriesResponse response = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/countries/v5")
                        .queryParam("q", name)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<RestCountriesResponse>() {});

        if (response == null
                || response.data() == null
                || response.data().objects() == null
                || response.data().objects().isEmpty()) {

            throw new CountryNotFoundException(
                    "País não encontrado: " + name);
        }

        return response.data().objects();
    }

    @Override
    public List<CountryApiResponse> findByRegion(String region) {

        RestCountriesResponse response = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/countries/v5")
                        .queryParam("region", region)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<RestCountriesResponse>() {});

        if (response == null
                || response.data() == null
                || response.data().objects() == null
                || response.data().objects().isEmpty()) {

            throw new CountryNotFoundException(
                    "Nenhum país encontrado para a região: " + region);
        }

        return response.data().objects();
    }
}
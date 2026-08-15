package br.com.alessandro.countryapi.dto;

import java.util.List;

public record RestCountriesResponse(
        Data data
) {

    public record Data(
            List<CountryApiResponse> objects
    ) {
    }
}
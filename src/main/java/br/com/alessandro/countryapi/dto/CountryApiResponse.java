package br.com.alessandro.countryapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryApiResponse(
        Name name,
        List<String> capital,
        String region,
        String subregion,
        Long population
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Name(
            String common,
            String official
    ) {
    }
}
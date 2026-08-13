package br.com.alessandro.countryapi.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryApiResponse(
        Name name,
        List<String> capital,
        String region,
        String subregion,
        Long population,
        Map<String, Currency> currencies,
        Map<String, String> languages
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Name(
            String common,
            String official
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Currency(
            String name,
            String symbol
    ) {
    }
}

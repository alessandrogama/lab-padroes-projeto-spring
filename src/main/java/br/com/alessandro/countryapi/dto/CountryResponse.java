package br.com.alessandro.countryapi.dto;

public record CountryResponse(
        String name,
        String officialName,
        String capital,
        String region,
        String subregion,
        Long population
) {
}
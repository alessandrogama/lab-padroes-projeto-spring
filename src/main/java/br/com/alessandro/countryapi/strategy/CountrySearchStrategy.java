package br.com.alessandro.countryapi.strategy;

import br.com.alessandro.countryapi.dto.CountryApiResponse;

import java.util.List;

public interface CountrySearchStrategy {
    List<CountryApiResponse> search(String value);
}

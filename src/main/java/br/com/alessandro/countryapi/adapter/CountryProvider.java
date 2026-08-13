package br.com.alessandro.countryapi.adapter;

import br.com.alessandro.countryapi.dto.CountryApiResponse;

import java.util.List;

public interface CountryProvider {

    List<CountryApiResponse> findByName(String name);

    List<CountryApiResponse> findByRegion(String region);
}
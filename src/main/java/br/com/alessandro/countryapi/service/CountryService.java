package br.com.alessandro.countryapi.service;

import br.com.alessandro.countryapi.dto.CountryApiResponse;
import br.com.alessandro.countryapi.dto.CountryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CountryService {

    public List<CountryResponse> convert(
            List<CountryApiResponse> countries) {

        if (countries == null) {
            return List.of();
        }

        return countries.stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .filter(Objects::nonNull)
                .toList();
    }

    private CountryResponse convert(CountryApiResponse country) {

        if (country == null) {
            return null;
        }

        String capital = extractCapital(country);

        String commonName = null;
        String officialName = null;

        if (country.name() != null) {
            commonName = country.name().common();
            officialName = country.name().official();
        }

        return new CountryResponse(
                commonName,
                officialName,
                capital,
                country.region(),
                country.subregion(),
                country.population()
        );
    }

    private String extractCapital(CountryApiResponse country) {

        if (country.capital() == null
                || country.capital().isEmpty()) {

            return null;
        }

        return country.capital().getFirst();
    }
}
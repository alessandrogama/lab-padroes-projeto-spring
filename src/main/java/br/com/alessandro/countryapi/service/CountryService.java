package br.com.alessandro.countryapi.service;

import br.com.alessandro.countryapi.dto.CountryApiResponse;
import br.com.alessandro.countryapi.dto.CountryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    public List<CountryResponse> convert(
            List<CountryApiResponse> countries) {

        return countries.stream()
                .map(this::convert)
                .toList();
    }

    private CountryResponse convert(CountryApiResponse country) {

        String capital = extractCapital(country);

        String currency = null;
        String currencySymbol = null;

        if (country.currencies() != null
                && !country.currencies().isEmpty()) {

            var currencyData =
                    country.currencies()
                            .values()
                            .iterator()
                            .next();

            currency = currencyData.name();
            currencySymbol = currencyData.symbol();
        }

        String language = null;

        if (country.languages() != null
                && !country.languages().isEmpty()) {

            language = country.languages()
                    .values()
                    .iterator()
                    .next();
        }

        return new CountryResponse(
                country.name().common(),
                country.name().official(),
                capital,
                country.region(),
                country.subregion(),
                country.population(),
                currency,
                currencySymbol,
                language
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
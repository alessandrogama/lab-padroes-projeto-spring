package br.com.alessandro.countryapi.strategy;

import br.com.alessandro.countryapi.adapter.CountryProvider;
import br.com.alessandro.countryapi.dto.CountryApiResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("region")
public class SearchByRegionStrategy implements CountrySearchStrategy {

    private final CountryProvider countryProvider;

    public SearchByRegionStrategy(CountryProvider countryProvider) {
        this.countryProvider = countryProvider;
    }

    @Override
    public List<CountryApiResponse> search(String value) {
        return countryProvider.findByRegion(value);
    }
}
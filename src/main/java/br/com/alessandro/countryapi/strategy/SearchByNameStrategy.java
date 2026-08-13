package br.com.alessandro.countryapi.strategy;

import br.com.alessandro.countryapi.adapter.CountryProvider;
import br.com.alessandro.countryapi.dto.CountryApiResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("name")
public class SearchByNameStrategy implements CountrySearchStrategy {

    private final CountryProvider countryProvider;

    public SearchByNameStrategy(CountryProvider countryProvider) {
        this.countryProvider = countryProvider;
    }

    @Override
    public List<CountryApiResponse> search(String value) {
        return countryProvider.findByName(value);
    }
}
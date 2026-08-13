package br.com.alessandro.countryapi.facade;

import br.com.alessandro.countryapi.dto.CountryResponse;
import br.com.alessandro.countryapi.factory.CountrySearchStrategyFactory;
import br.com.alessandro.countryapi.service.CountryService;
import br.com.alessandro.countryapi.strategy.CountrySearchStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryFacade {

    private final CountrySearchStrategyFactory strategyFactory;
    private final CountryService countryService;

    public CountryFacade(
            CountrySearchStrategyFactory strategyFactory,
            CountryService countryService) {

        this.strategyFactory = strategyFactory;
        this.countryService = countryService;
    }

    public List<CountryResponse> search(
            String type,
            String value) {

        CountrySearchStrategy strategy =
                strategyFactory.getStrategy(type);

        var countries = strategy.search(value);

        return countryService.convert(countries);
    }
}
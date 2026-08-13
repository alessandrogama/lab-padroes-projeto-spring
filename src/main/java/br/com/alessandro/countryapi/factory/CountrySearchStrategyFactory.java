package br.com.alessandro.countryapi.factory;

import br.com.alessandro.countryapi.strategy.CountrySearchStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CountrySearchStrategyFactory {

    private final Map<String, CountrySearchStrategy> strategies;

    public CountrySearchStrategyFactory(
            Map<String, CountrySearchStrategy> strategies) {

        this.strategies = strategies;
    }

    public CountrySearchStrategy getStrategy(String type) {

        CountrySearchStrategy strategy =
                strategies.get(type.toLowerCase());

        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Tipo de pesquisa não suportado: " + type);
        }

        return strategy;
    }
}
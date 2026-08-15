package br.com.alessandro.countryapi.service;

import br.com.alessandro.countryapi.dto.CountryApiResponse;
import br.com.alessandro.countryapi.dto.CountryResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryServiceTest {

    private final CountryService countryService = new CountryService();

    @Test
    void shouldConvertCountriesSuccessfully() {
        // Given
        CountryApiResponse.Name name = new CountryApiResponse.Name("Brazil", "Federative Republic of Brazil");
        CountryApiResponse country = new CountryApiResponse(
                name,
                List.of("Brasília"),
                "Americas",
                "South America",
                214000000L
        );

        // When
        List<CountryResponse> result = countryService.convert(List.of(country));

        // Then
        assertEquals(1, result.size());
        CountryResponse response = result.get(0);
        assertEquals("Brazil", response.name());
        assertEquals("Federative Republic of Brazil", response.officialName());
        assertEquals("Brasília", response.capital());
        assertEquals("Americas", response.region());
        assertEquals("South America", response.subregion());
        assertEquals(214000000L, response.population());
    }

    @Test
    void shouldHandleNullCountryList() {
        // When
        List<CountryResponse> result = countryService.convert(null);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldHandleNullElementsInList() {
        // Given
        CountryApiResponse.Name name = new CountryApiResponse.Name("Brazil", "Federative Republic of Brazil");
        CountryApiResponse country = new CountryApiResponse(
                name,
                List.of("Brasília"),
                "Americas",
                "South America",
                214000000L
        );
        List<CountryApiResponse> listWithNulls = new ArrayList<>();
        listWithNulls.add(null);
        listWithNulls.add(country);
        listWithNulls.add(null);

        // When
        List<CountryResponse> result = countryService.convert(listWithNulls);

        // Then
        assertEquals(1, result.size());
        assertEquals("Brazil", result.get(0).name());
    }

    @Test
    void shouldHandleNullNameAndCapital() {
        // Given
        CountryApiResponse country = new CountryApiResponse(
                null,
                null,
                "Americas",
                "South America",
                214000000L
        );

        // When
        List<CountryResponse> result = countryService.convert(List.of(country));

        // Then
        assertEquals(1, result.size());
        CountryResponse response = result.get(0);
        assertNull(response.name());
        assertNull(response.officialName());
        assertNull(response.capital());
        assertEquals("Americas", response.region());
        assertEquals("South America", response.subregion());
        assertEquals(214000000L, response.population());
    }
}

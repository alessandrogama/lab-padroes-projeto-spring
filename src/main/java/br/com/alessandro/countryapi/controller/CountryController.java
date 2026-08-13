package br.com.alessandro.countryapi.controller;

import br.com.alessandro.countryapi.dto.CountryResponse;
import br.com.alessandro.countryapi.facade.CountryFacade;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Validated
public class CountryController {

    private final CountryFacade countryFacade;

    public CountryController(CountryFacade countryFacade) {
        this.countryFacade = countryFacade;
    }

    @GetMapping("/search")
    public List<CountryResponse> search(
            @RequestParam
            @NotBlank(message = "O tipo é obrigatório")
            String type,

            @RequestParam
            @NotBlank(message = "O valor é obrigatório")
            String value) {

        return countryFacade.search(type, value);
    }
}
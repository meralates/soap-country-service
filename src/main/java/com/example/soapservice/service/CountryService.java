package com.example.soapservice.service;

import com.example.soapservice.model.Country;
import com.example.soapservice.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    private static final List<String> VALID_COUNTRIES = Arrays.asList(
            "Turkey", "Spain", "Poland", "United Kingdom", "Germany", "France"
    );

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public String getCapitalByCountry(String countryName) {

        if (!isValidCountry(countryName)) {
            return null;
        }

        Country country = countryRepository.findByName(countryName);

        if (country != null) {
            return country.getCapital();
        }
        return null;
    }

    private boolean isValidCountry(String countryName) {
        return VALID_COUNTRIES.contains(countryName);
    }

    public Country getCountryDetails(String countryName) {
        if (!isValidCountry(countryName)) {
            return null;
        }

        return countryRepository.findByName(countryName);
    }
}

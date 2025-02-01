package com.example.soapservice.repository;

import com.example.soapservice.model.Country;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import com.example.soapservice.model.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CountryRepository {
    private static final Map<String, Country> countries = new HashMap<>();

    public static final String SPAIN = "Spain";
    public static final String POLAND = "Poland";
    public static final String UK = "United Kingdom";
    public static final String TURKEY = "Turkey";
    public static final String GERMANY = "Germany";
    public static final String FRANCE = "France";

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setName(SPAIN);
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setPopulation(46704314);
        countries.put(spain.getName(), spain);

        Country poland = new Country();
        poland.setName(POLAND);
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38186860);
        countries.put(poland.getName(), poland);

        Country uk = new Country();
        uk.setName(UK);
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);
        countries.put(uk.getName(), uk);

        Country turkey = new Country();
        turkey.setName(TURKEY);
        turkey.setCapital("Ankara");
        turkey.setCurrency(Currency.TRY);
        turkey.setPopulation(85561976);
        countries.put(turkey.getName(), turkey);

        Country germany = new Country();
        germany.setName(GERMANY);
        germany.setCapital("Berlin");
        germany.setCurrency(Currency.EUR);
        germany.setPopulation(83166711);
        countries.put(germany.getName(), germany);

        Country france = new Country();
        france.setName(FRANCE);
        france.setCapital("Paris");
        france.setCurrency(Currency.EUR);
        france.setPopulation(67081000);
        countries.put(france.getName(), france);
    }

    public Country findByName(String name) {
        Assert.notNull(name, "The country's name must not be null");

        // Veritabanında arama yap
        return countries.get(name);
    }
}

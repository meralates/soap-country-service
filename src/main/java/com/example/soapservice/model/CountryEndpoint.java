package com.example.soapservice.model;

import com.example.soapservice.model.GetCountryResponse;
import com.example.soapservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//import io.spring.guides.gs_producing_web_service.GetCountryRequest;
//import io.spring.guides.gs_producing_web_service.Country;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private final CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        String countryName = request.getName();

        // Ülke veritabanında bulunuyor mu kontrol et
        Country country = countryRepository.findByName(countryName);

        // Ülke bulunmazsa, response'u null olarak ayarla
        if (country == null) {
            GetCountryResponse response = new GetCountryResponse();
            // null dönebilir, ya da error message ekleyebilirsiniz
            return response;
        }

        // Ülke bulunduysa response'u ayarla
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(country);

        return response;
    }
}

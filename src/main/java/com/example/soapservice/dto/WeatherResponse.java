package com.example.soapservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    @JsonProperty("name")
    private String cityName;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("weather")
    private Weather[] weather;

    public String getCityName() {
        return cityName;
    }

    public Main getMain() {
        return main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        @JsonProperty("temp")
        private double temp;

        @JsonProperty("humidity")
        private int humidity;

        public double getTemp() {
            return temp;
        }

        public int getHumidity() {
            return humidity;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        @JsonProperty("description")
        private String description;

        public String getDescription() {
            return description;
        }
    }
}

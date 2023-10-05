package com.ucatolica.springrestapi.Config;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Esta clase proporciona configuración para la integración con la API de Google Maps.
 */
@Configuration
public class GoogleMapsConfig {

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    /**
     * Crea y configura un contexto de GeoApiContext para interactuar con la API de Google Maps.
     *
     * @return El contexto de GeoApiContext configurado con la clave de la API de Google Maps.
     */
    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(googleMapsApiKey)
                .build();
    }
}

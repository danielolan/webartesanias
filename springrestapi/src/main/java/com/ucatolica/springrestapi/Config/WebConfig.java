package com.ucatolica.springrestapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import org.springframework.web.servlet.config.annotation.*;
/**
 * Clase de configuración que define la configuración de CORS (Cross-Origin Resource Sharing)
 * para permitir peticiones desde dominios diferentes al servidor de la aplicación.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///C:/Users/Daniel/Documents/aprendiendoweb/webartesanias/front/img/");
    }

    /**
     * Configura un filtro CORS que permite peticiones desde cualquier origen (*),
     * con credenciales permitidas, y admite los métodos OPTIONS, GET, POST, PUT y DELETE.
     *
     * @return El filtro CORS configurado.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

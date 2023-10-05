package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.Location;

import java.util.List;

/**
 * Interfaz que define los métodos para el servicio de ubicaciones (Location).
 */
public interface LocationService {

    /**
     * Guarda una ubicación.
     *
     * @param location La ubicación a guardar.
     */
    void saveLocation(Location location);

    /**
     * Obtiene una ubicación por su ID.
     *
     * @param id El ID de la ubicación a obtener.
     * @return La ubicación encontrada o null si no se encuentra.
     */
    Location getLocation(Long id);

    /**
     * Obtiene todas las ubicaciones.
     *
     * @return Una lista de todas las ubicaciones.
     */
    List<Location> getAllLocations();
}

package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.Location;
import com.ucatolica.springrestapi.repository.LocationRepository;
import com.ucatolica.springrestapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de ubicaciones (LocationService).
 */
@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository lRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.lRepository = locationRepository;
    }

    /**
     * Guarda una ubicación en la base de datos.
     *
     * @param location La ubicación a guardar.
     */
    @Override
    public void saveLocation(Location location) {
        // Simplemente guarda la ubicación en la base de datos
        lRepository.save(location);
    }

    /**
     * Obtiene todas las ubicaciones almacenadas en la base de datos.
     *
     * @return Una lista de todas las ubicaciones.
     */
    @Override
    public List<Location> getAllLocations() {
        // Obtiene todas las ubicaciones almacenadas en la base de datos
        return lRepository.findAll();
    }

    /**
     * Obtiene una ubicación por su ID.
     *
     * @param id El ID de la ubicación a obtener.
     * @return La ubicación encontrada o null si no se encuentra.
     */
    @Override
    public Location getLocation(Long id) {
        // Busca y devuelve una ubicación por su ID
        return lRepository.findById(id).orElse(null);
    }
}

package com.ucatolica.springrestapi.service.Impl;


import com.ucatolica.springrestapi.model.Location;
import com.ucatolica.springrestapi.repository.LocationRepository;
import com.ucatolica.springrestapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository lRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.lRepository = locationRepository;
    }

    @Override
    public void saveLocation(Location location) {
        // Simplemente guarda la ubicación en la base de datos
        lRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        // Obtiene todas las ubicaciones almacenadas en la base de datos
        return lRepository.findAll();
    }

    @Override
    public Location getLocation(Long id) {
        // Busca y devuelve una ubicación por su ID
        return lRepository.findById(id).orElse(null);
    }
}

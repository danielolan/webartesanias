package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.Location;

import java.util.List;

public interface LocationService {
    void saveLocation(Location location);

    Location getLocation(Long id);


    List<Location> getAllLocations();
}

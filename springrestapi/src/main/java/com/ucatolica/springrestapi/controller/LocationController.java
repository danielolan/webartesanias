package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.Location;
import com.ucatolica.springrestapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las solicitudes relacionadas con las ubicaciones (Location).
 */
@RestController
@RequestMapping("/api")
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Guarda una nueva ubicación.
     *
     * @param location La ubicación a ser guardada.
     * @return Una ResponseEntity con un mensaje de éxito y el estado HTTP CREATED (201) después de guardar la ubicación.
     */
    @PostMapping("/maps/save")
    public ResponseEntity<String> saveLocation(@RequestBody Location location) {
        locationService.saveLocation(location);
        return new ResponseEntity<>("Location successfully saved", HttpStatus.CREATED);
    }

    /**
     * Obtiene una ubicación específica por su identificador.
     *
     * @param id El identificador único de la ubicación.
     * @return Una ResponseEntity que contiene la ubicación correspondiente al ID proporcionado y el estado HTTP OK (200).
     *         Si la ubicación no se encuentra, devuelve el estado HTTP NOT_FOUND (404).
     */
    @GetMapping("/maps/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Location location = locationService.getLocation(id);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene todas las ubicaciones disponibles.
     *
     * @return Una ResponseEntity que contiene una lista de objetos Location y el estado HTTP OK (200).
     */
    @GetMapping("/maps/all")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
}

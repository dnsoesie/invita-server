package com.invita.usstatsreviews.controllers;

import com.invita.usstatsreviews.models.Location;
import com.invita.usstatsreviews.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/location")
@CrossOrigin(origins = "http://localhost:3000")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<HttpStatus> createNewLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Location>> getAllLocations() {
        return locationService.getLocations();
    }

    @GetMapping(value = "/zippopo/{zipcode}")
    public ResponseEntity<Object> getZippoDetails(@PathVariable("zipcode") String zipcode) throws Exception {
        String url = "http://api.zippopotam.us/us/" + zipcode;
        RestTemplate restTemplate = new RestTemplate();
        try {
            Object data = restTemplate.getForObject(url, Object.class);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("A mistake was made");
        }
    }

    @GetMapping(value = "/{zipcode}")
    public ResponseEntity<Location> getReviewsForZipCode(@PathVariable("zipcode") String zipcode) {
        return locationService.getLocationReviews(zipcode);
    }

    @DeleteMapping(value = "/{locationId}/{reviewId}")
    public ResponseEntity<HttpStatus> deleteLocationWithId(@PathVariable("locationId") String locationId, @PathVariable("reviewId") String reviewId) {
        return locationService.deleteReviewWithId(locationId, reviewId);
    }
}

package com.invita.usstatsreviews.services;

import com.invita.usstatsreviews.models.Location;
import com.invita.usstatsreviews.models.Review;
import com.invita.usstatsreviews.repository.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationDao locationDao;

    @Autowired
    public LocationService(LocationDao locationDao) {
        this.locationDao = locationDao;
    }
    public ResponseEntity<HttpStatus> createLocation(Location location) {
        locationDao.save(location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Location>> getLocations() {
        List<Location> locationList = locationDao.findAll();
        return new ResponseEntity<>(locationList, HttpStatus.OK);
    }

    public ResponseEntity<Location> getLocationReviews(String zipcode) {
        Location location = new Location();
        Optional<Location> optionalLocation = locationDao.findByZipcode(zipcode);
        if(optionalLocation.isPresent()) {
            location = optionalLocation.get();
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteReviewWithId(String locationId, String reviewId) {
        Optional<Location> optionalLocation = locationDao.findById(locationId);
        if(optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            List<Review> reviewList = location.getReviews(); //get the list of reviews
            reviewList.removeIf(review -> review.getId() == Integer.parseInt(reviewId)); // remove the review with the reviewId
            location.setReviews(reviewList); //update the location with the filtered reviewlist
            locationDao.save(location); //save the updates
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}

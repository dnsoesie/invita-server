package com.invita.usstatsreviews.repository;

import com.invita.usstatsreviews.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationDao extends MongoRepository<Location, String> {
    Optional<Location> findByZipcode(String zipcode);
    // Optional<Location> findByZipCode(String zipcode);
}

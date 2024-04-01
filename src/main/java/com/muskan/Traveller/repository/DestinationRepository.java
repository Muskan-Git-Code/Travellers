package com.muskan.Traveller.repository;

import com.muskan.Traveller.model.Activity;
import com.muskan.Traveller.model.Destination;
import com.muskan.Traveller.model.PackageOffered;
import com.muskan.Traveller.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

}

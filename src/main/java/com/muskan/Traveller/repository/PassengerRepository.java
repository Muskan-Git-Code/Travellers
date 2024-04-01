package com.muskan.Traveller.repository;

import com.muskan.Traveller.model.Activity;
import com.muskan.Traveller.model.Destination;
import com.muskan.Traveller.model.PackageOffered;
import com.muskan.Traveller.model.Passenger;
import com.muskan.Traveller.service.PassengerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}

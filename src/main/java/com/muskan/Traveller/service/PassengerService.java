package com.muskan.Traveller.service;

import com.muskan.Traveller.model.Activity;
import com.muskan.Traveller.model.Destination;
import com.muskan.Traveller.model.PackageOffered;
import com.muskan.Traveller.model.Passenger;
import org.springframework.stereotype.Service;

@Service
public interface PassengerService {

    public String addPassenger(Passenger passenger);

    public String addActivity(Activity activity);

    public String addDestination(Destination destination);

    public String addPackage(PackageOffered packageOffered);

    public String enrollForActivity(Integer passengerId, Integer activityId);
}

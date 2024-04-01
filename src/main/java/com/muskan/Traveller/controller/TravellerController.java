package com.muskan.Traveller.controller;

import com.muskan.Traveller.model.*;
import com.muskan.Traveller.service.PassengerService;
import com.muskan.Traveller.service.PassengerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TravellerController {

    @Autowired
    PassengerService passengerService;

    @PostMapping("/addPassenger")
    public String addPassenger(@RequestBody Passenger passenger){
        return passengerService.addPassenger(passenger);
    }

    @PostMapping("/addActivity")
    public String addActivity(@RequestBody Activity activity){
        return passengerService.addActivity(activity);
    }

    @PostMapping("/addDestination")
    public String addDestination(@RequestBody Destination destination){
        return passengerService.addDestination(destination);
    }

    @PostMapping("/addPackage")
    public String addPackageOffered(@RequestBody PackageOffered packageOffered){
        return passengerService.addPackage(packageOffered);
    }

    @PostMapping("/enrollForActivity/{passengerId}/{activityId}")
    public String enrollForActivity(@PathVariable Integer passengerId, @PathVariable Integer activityId){
        return passengerService.enrollForActivity(passengerId, activityId);
    }

}

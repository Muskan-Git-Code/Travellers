package com.muskan.Traveller.service;

import com.muskan.Traveller.model.*;
import com.muskan.Traveller.repository.ActivityRepository;
import com.muskan.Traveller.repository.DestinationRepository;
import com.muskan.Traveller.repository.PackageOfferedRepository;
import com.muskan.Traveller.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    PackageOfferedRepository packageOfferedRepository;

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    ActivityRepository activityRepository;


    @Override
    public String addPassenger(Passenger passenger){

        try {
            if(passenger.equals(null)){
                throw new NullPointerException("Invalid details entered");
            }

            passengerRepository.save(passenger);
            return "New Passenger Added Successfully";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Unexpected error";
        }
    }

    @Override
    public String addActivity(Activity activity) {
        try {
            if(activity.equals(null)){
                throw new NullPointerException("Invalid details entered");
            }

            activityRepository.save(activity);
            return "New Activity Added Successfully";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Unexpected error";
        }
    }

    @Override
    public String addDestination(Destination destination) {
        try {
            if(destination.equals(null)){
                throw new NullPointerException("Invalid details entered");
            }

            destinationRepository.save(destination);
            return "New Destination Added Successfully";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Unexpected error";
        }
    }

    @Override
    public String addPackage(PackageOffered packageOffered) {
        try {
            if(packageOffered.equals(null)){
                throw new NullPointerException("Invalid details entered");
            }

            packageOfferedRepository.save(packageOffered);
            return "New Destination Added Successfully";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Unexpected error";
        }
    }


    public String enrollForActivity(Integer passengerId, Integer activityId){
        try {
            Optional<Passenger> passenger= passengerRepository.findById(passengerId);
            Optional<Activity> activity= activityRepository.findById(activityId);

            if(passenger.isPresent() && activity.isPresent()){

                //check for space availability for an activity
                Integer space= activity.get().getCapacity() - activity.get().getPassengers().size();
                if(space<=0){
                    throw new RuntimeException("All seats are full!");
                }

                //check for class and deduct amt
                ClassGroup gp = passenger.get().getClassGroup();

                if(!gp.equals(ClassGroup.PREMIUM)) {
                    Double bal= passenger.get().getBalance();
                    Double cost= activity.get().getCost();

                    if(gp.equals(ClassGroup.GOLD)){ cost= cost- (cost*0.1); }

                    if(bal > cost){
                        //Can apply for this activity
                        passenger.get().setBalance(bal-cost);
                    }
                    else{
                        throw new RuntimeException("Insufficient Balance");
                    }
                }

                ArrayList<Integer> passengerList= activity.get().getPassengers();
                passengerList.add(passengerId);
                activity.get().setPassengers(passengerList);
            }
            else {
                throw new NullPointerException("Invalid details entered");
            }

            return passenger.get().getName()+ " enrolled to "+ activity.get().getName();
        }
        catch (Exception e){
            e.printStackTrace();
            return "Unexpected error";
        }
    }


    public void printPackageDetails(){
        try {

            //from package offeredlist(name, destinationIdList), destination List
            List<PackageOffered> packageOfferedList= packageOfferedRepository.findAll();
            System.out.println("List of packages offered: ");
            for (PackageOffered po: packageOfferedList) {
                System.out.print(po.getId()+ ", Name: "+ po.getName()+ ", Capacity: "+ po.getCapacity()+ ", Cost: "+ po.getCost());

                //now for each destination, get activities list
                ArrayList<Integer> destinationList= po.getDest();
                for (Integer i: destinationList){

                    System.out.println(", Destinations as: "+ i);
                    //for destination x, check in Destination table for activitiesList
                    Optional<Destination> des= destinationRepository.findById(i);

                    if(des.isPresent()) {
                        ArrayList<Integer> activityList = des.get().getActivities();

                        //for each activity, get name, desc, cost.
                        System.out.println(", Providing activities: ");
                        for (Integer j : activityList) {
                            Optional<Activity> activity = activityRepository.findById(i);
                            if (activity.isPresent()) {
                                Activity act= activity.get();

                                System.out.println(act.getId() + ", Name: " + act.getName() + ", Description: " + act.getDescription() + ", Cost: " + act.getCost() + ", Max number of people who can enroll: " + act.getCapacity());
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Unexpected Error");
        }
    }


    public void printOpenToEnrollActivities(){

        try {
            System.out.println("List of open to enroll Activities: ");
            List<Activity> activityList = activityRepository.findAll();

            for (Activity activity : activityList) {
                int val = activity.getCapacity() - activity.getPassengers().size();
                if (val > 0) {
                    System.out.println("Activity: " + activity.getId() + ", Name: " + activity.getName() + ", Description: " + activity.getDescription() + ", Capacity: " + activity.getCapacity() + ", Cost: " + activity.getCost());
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Unexpected Error");
        }
    }


    public void printTravelPassengers(){

        try {
            System.out.println("List of Travel passengers: ");
            List<PackageOffered> packageOfferedList= packageOfferedRepository.findAll();
            for(PackageOffered po: packageOfferedList){
                System.out.println("\n Package: "+ po.getId()+ ", Name: "+ po.getName()+ ", Cost: "+ po.getCost()+ ", Capacity: "+ po.getCapacity()+ "Having passengers: ");

                //for each package, the list of passengers enrolled
                ArrayList<Integer> passengerList= po.getPassengers();
                for(Integer p: passengerList){
                    Optional<Passenger> passenger= passengerRepository.findById(p);
                    if(passenger.isPresent()){
                        Passenger passenger1= passenger.get();
                        System.out.println("Id: "+ passenger1.getId()+ ", Name: "+ passenger1.getName()+ ", PhoneNo: "+ passenger1.getPhoneno());
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Unhandled Exception");
        }
    }
}

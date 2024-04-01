package com.muskan.Traveller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String name;

    public String description;

    public Double cost;

    public Integer capacity;

    public ArrayList<Integer> passengers = new ArrayList<>();

    public ArrayList<Integer> packages = new ArrayList<>();

    public Activity(){}

    public Activity(String name, String description, Double cost, Integer capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Integer> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Integer> passengers) {
        this.passengers = passengers;
    }

    public ArrayList<Integer> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Integer> packages) {
        this.packages = packages;
    }


}

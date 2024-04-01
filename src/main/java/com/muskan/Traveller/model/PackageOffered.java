package com.muskan.Traveller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class PackageOffered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String name;

    public Integer cost;

    public ArrayList<Integer> dest;

    public Integer capacity;

    public ArrayList<Integer> passengers = new ArrayList<>();


    public PackageOffered() {}

    public PackageOffered(String name, Integer cost, ArrayList<Integer> dest, Integer capacity) {
        this.name = name;
        this.cost = cost;
        this.dest = dest;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public ArrayList<Integer> getDest() {
        return dest;
    }

    public void setDest(ArrayList<Integer> dest) {
        this.dest = dest;
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
}

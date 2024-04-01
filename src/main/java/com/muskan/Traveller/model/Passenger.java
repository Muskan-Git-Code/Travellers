package com.muskan.Traveller.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String name;

    public String phoneno;

    @Enumerated(EnumType.STRING)
    @Column(length = 45)
    @NotNull
    public ClassGroup classGroup;

    public Double balance;

    public Passenger(){}

    public Passenger(String name, String phoneno, ClassGroup classGroup, Double balance) {
        this.name = name;
        this.phoneno = phoneno;
        this.classGroup = classGroup;
        this.balance = balance;
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

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public ClassGroup getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

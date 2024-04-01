package com.muskan.Traveller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.muskan.Traveller.controller.TravellerController;
import com.muskan.Traveller.model.*;
import com.muskan.Traveller.repository.*;
import com.muskan.Traveller.service.PassengerService;
import com.muskan.Traveller.service.PassengerServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    PackageOfferedRepository packageOfferedRepository;

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PassengerServiceImpl passengerService;

    @Before
    public void setup() {
        passengerRepository.deleteAll();
        activityRepository.deleteAll();
        packageOfferedRepository.deleteAll();
        destinationRepository.deleteAll();
    }

    @Test
    public void checkController() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(TravellerController.class, Controller.class);
        if (annotation != null) {
            hasAnnotation = true;
        }

        assertTrue(hasAnnotation);
    }


    @Test
    public void checkService() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(PassengerService.class, Service.class);
        if (annotation != null) {
            hasAnnotation = true;
        }

        assertTrue(hasAnnotation);
    }

    @Test
    public void checkRepository() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(ActivityRepository.class, Repository.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(DestinationRepository.class, Repository.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(PackageOfferedRepository.class, Repository.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(PassengerRepository.class, Repository.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);
    }

    @Test
    public void checkEntity() {
        boolean hasAnnotation = false;

        Annotation annotation = AnnotationUtils.findAnnotation(Activity.class, Entity.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(Destination.class, Entity.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(PackageOffered.class, Entity.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);

        hasAnnotation = false;
        annotation = AnnotationUtils.findAnnotation(Passenger.class, Entity.class);
        if (annotation != null) {
            hasAnnotation = true;
        }
        assertTrue(hasAnnotation);
    }


    @Test
    public void TravellerWorkingTest() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();


        //Add activity
        Activity activity1= new Activity("activity1", "activity1", 20.0, 10);

        MockHttpServletResponse response = mockMvc.perform(post("/api/addActivity")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(activity1)))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse();


        Activity activity2= new Activity("activity2", "activity2", 50.0, 7);

        response = mockMvc.perform(post("/api/addActivity")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(activity2)))
                .andExpect(status().isOk()).andReturn().getResponse();


        //Add a destination
        Destination destination1= new Destination("destination1", new ArrayList<>());

        //Set new activities for a destination
        ArrayList activityList = destination1.getActivities();
        activityList.add(1);    activityList.add(2);
        destination1.setActivities(activityList);

        response = mockMvc.perform(post("/api/addDestination")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(destination1)))
                .andExpect(status().isOk()).andReturn().getResponse();




        //Add Passenger
        Passenger passenger1= new Passenger("Muskan", "9999999999", ClassGroup.GOLD, 200.0);

        response = mockMvc.perform(post("/api/addPassenger")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(passenger1)))
                .andExpect(status().isOk()).andReturn().getResponse();


        //Add package
        PackageOffered package1= new PackageOffered("Package1", 100, new ArrayList<>(), 5);

        //Adding destinations to a package
        ArrayList destinationList = package1.getDest();
        destinationList.add(1);    destinationList.add(2);
        package1.setDest(destinationList);

        //Enrolling passenger1 to a package by checking if seats available for that package
        ArrayList passengerList = package1.getPassengers();
        int space= package1.getCapacity() - package1.getPassengers().size();
        if(space>0){
            passengerList.add(1);
            package1.setPassengers(passengerList);
        }

        response = mockMvc.perform(post("/api/addPackage")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(package1)))
                .andExpect(status().isOk()).andReturn().getResponse();


        //Enrolling passenger1 to activity2
        //Calling method enrollForActivity
        int activityId=2, passengerId=1;
        mockMvc.perform(post("/api/enrollForActivity/"+ passengerId +"/"+ activityId))
                .andDo(print())
                .andExpect(status().isOk());


        //Now execute print statements
        passengerService.printTravelPassengers();
        passengerService.printOpenToEnrollActivities();
        passengerService.printPackageDetails();
    }


}
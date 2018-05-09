package com.zarkary.designpatterns.eventaggregator.example.consultants.custom;

import com.zarkary.designpatterns.eventaggregator.api.EventAggregator;
import com.zarkary.designpatterns.eventaggregator.api.IPublisher;
import com.zarkary.designpatterns.eventaggregator.api.Publisher;
import com.zarkary.designpatterns.eventaggregator.example.consultants.Event;
import com.zarkary.designpatterns.eventaggregator.example.consultants.Location;
import com.zarkary.designpatterns.eventaggregator.example.consultants.MsgMovement;

/**
 * Test Application
 *
 * @author Ioannis Katsatos
 * @since 23.04.2018
 */
public class App {

    public static void main(String[] args) {

        final EventAggregator eventAggregator = new EventAggregator();

        Consultant john = new Consultant(eventAggregator, "John");
        Consultant simony = new Consultant(eventAggregator,  "Simony");
        Consultant peter = new Consultant(eventAggregator, "Peter");

        john.subscribe(Event.LOCATION_CHANGED);
        john.subscribe(Event.AVAILABILITY_CHANGED);

        simony.subscribe(Event.LOCATION_CHANGED);
        simony.subscribe(Event.AVAILABILITY_CHANGED);

        peter.subscribe(Event.LOCATION_CHANGED);
        peter.subscribe(Event.AVAILABILITY_CHANGED);

        System.out.println("\n****************************************************");

        System.out.println(john.getName() + " currentLocation: "
                + john.getCurrentLocation() + " is available: " + john.isAvailable());
        System.out.println(simony.getName() + " currentLocation: "
                + simony.getCurrentLocation() + " is available: " + simony.isAvailable());
        System.out.println(peter.getName() + " currentLocation: "
                + peter.getCurrentLocation() + " is available: " + peter.isAvailable());

        System.out.println("\n****************************************************");

        final IPublisher publisher = new Publisher(eventAggregator);

        publisher.publish(Event.LOCATION_CHANGED, new MsgMovement(john, Location.TRAIN_STATION));
        publisher.publish(Event.LOCATION_CHANGED, new MsgMovement(peter, Location.HOME));

        System.out.println(john.getName() + " currentLocation: "
                + john.getCurrentLocation() + " is available: " + john.isAvailable());
        System.out.println(simony.getName() + " currentLocation: "
                + simony.getCurrentLocation() + " is available: " + simony.isAvailable());
        System.out.println(peter.getName() + " currentLocation: "
                + peter.getCurrentLocation() + " is available: " + peter.isAvailable());

        System.out.println("\n****************************************************");

        publisher.publish(Event.LOCATION_CHANGED, new MsgMovement(simony, Location.OFFICE));
        publisher.publish(Event.AVAILABILITY_CHANGED, new MsgMovement(peter, true));

        System.out.println(john.getName() + " currentLocation: "
                + john.getCurrentLocation() + " is available: " + john.isAvailable());
        System.out.println(simony.getName() + " currentLocation: "
                + simony.getCurrentLocation() + " is available: " + simony.isAvailable());
        System.out.println(peter.getName() + " currentLocation: "
                + peter.getCurrentLocation() + " is available: " + peter.isAvailable());

        System.out.println("\n****************************************************");

    }
}

package kz.akbar.service;

import kz.akbar.model.Airplane;
import kz.akbar.model.Destination;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Flight implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Flight.class.getName());
    private Airplane airplane;
    private int distance;
    private Destination destination;
    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public Flight(Airplane airplane, Destination destination, CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.airplane = airplane;
        this.destination = destination;
    }

    public void setAirplane(Airplane airplane){
        this.airplane = airplane;
        this.distance = airplane.getFlightRange();
    }

    public void run() {
        LOGGER.info("The airplane " + airplane.getName() + " are in the flight for " + distance + " km" + " to " + destination);
        try {
            startSignal.await(airplane.getFlightRange()/100, TimeUnit.SECONDS);
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

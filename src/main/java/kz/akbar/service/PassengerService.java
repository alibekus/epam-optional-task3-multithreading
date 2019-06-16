package kz.akbar.service;

import kz.akbar.model.Airplane;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class PassengerService implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Flight.class.getName());
    private Airplane airplane;
    private int passengerCount;
    private PassengerServiceType type;
    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public PassengerService(Airplane airplane, PassengerServiceType type, CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.type = type;
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.airplane = airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public void setServiceType(PassengerServiceType type){
        this.type = type;
    }

    public void run() {
        LOGGER.info("Passengers are "+ type.name() + " into airplane " + airplane.getName());
        try {
            startSignal.await(passengerCount / 10, TimeUnit.SECONDS);
            doneSignal.countDown();
            LOGGER.info("All passengers are " + type.name());
        } catch (InterruptedException e) {
            LOGGER.info("Passengers can't "+ type.name() + " airplane " + airplane.getName());
        }
    }
}

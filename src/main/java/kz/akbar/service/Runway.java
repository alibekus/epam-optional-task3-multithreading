package kz.akbar.service;

import kz.akbar.model.Airplane;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Runway implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Flight.class.getName());
    private Airplane airplane;
    private RunwayType type;
    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    public Runway(Airplane airplane, RunwayType type, CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.airplane = airplane;
        this.type = type;
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        LOGGER.info("The airplane " + airplane.getName() + " on " + type.name());
        try {
            startSignal.await(10, TimeUnit.SECONDS);
            doneSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

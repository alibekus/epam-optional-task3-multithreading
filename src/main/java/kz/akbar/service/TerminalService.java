package kz.akbar.service;

import kz.akbar.model.Airplane;
import kz.akbar.model.Destination;
import kz.akbar.model.Terminal;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TerminalService implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Flight.class.getName());
    private TerminalServiceType type;
    private Terminal terminal;
    private BlockingQueue<Airplane> airplaneQueue;


    public TerminalService(Terminal terminal, BlockingQueue<Airplane> airplaneQueue) {
        this.type = type;
        this.terminal = terminal;
        this.type = terminal.getType();
        this.airplaneQueue = airplaneQueue;
    }

    public void setType(TerminalServiceType type) {
        this.type = type;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void setAirplaneQueue(BlockingQueue<Airplane> airplaneQueue) {
        this.airplaneQueue = airplaneQueue;
    }

    public void run() {
        LOGGER.info("Terminal of " + type.name() + " service");
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(6 * airplaneQueue.size());
        CountDownLatch latch = new CountDownLatch(airplaneQueue.size());
        startSignal.countDown();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (Airplane airplane : airplaneQueue) {
            executorService.submit((new PassengerService(airplane, PassengerServiceType.BOARDING, startSignal, doneSignal)));
            executorService.submit(new Runway(airplane, RunwayType.TAKEOFF, startSignal, doneSignal));
            executorService.submit(new Flight(airplane, Destination.DEST1, startSignal, doneSignal));
            executorService.submit(new PassengerService(airplane, PassengerServiceType.FLIGHT_SERVICE, startSignal, doneSignal));
            executorService.submit(new Runway(airplane, RunwayType.LANDING, startSignal, doneSignal));
            executorService.submit(new PassengerService(airplane, PassengerServiceType.DISEMBARKATION, startSignal, doneSignal));
            latch.countDown();
        }
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}

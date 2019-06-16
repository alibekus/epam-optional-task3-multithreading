package kz.akbar;

import kz.akbar.model.*;
import kz.akbar.service.AirportService;
import kz.akbar.service.TerminalServiceType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static final Airplane AIRPLANE_1 = new Airplane("Boeing 737", 103, 2592);
    private static final Airplane AIRPLANE_2 = new Airplane("Boeing 777", 110, 6820);
    private static final Airplane AIRPLANE_3 = new Airplane("Boeing 747", 305, 9695);
    private static final Airplane AIRPLANE_4 = new Airplane("Boeing 757", 43, 7275);
    private static final Airplane AIRPLANE_5 = new Airplane("Airbus 300", 228, 2700);
    private static final Airplane AIRPLANE_6 = new Airplane("Airbus 310", 200, 3500);
    private static final Airplane AIRPLANE_7 = new Airplane("Airbus 320", 150, 3200);
    private static final Airplane AIRPLANE_8 = new Airplane("Airbus 330", 320, 5000);
    private static final Airplane AIRPLANE_9 = new Airplane("Airbus 350", 350, 8000);
    private static final Airplane AIRPLANE_10 = new Airplane("Airbus 380", 600, 6500);

    private static BlockingQueue<Airplane> airplaneQueue = new ArrayBlockingQueue<Airplane>(10, true);

    public static void main(String[] args) {
        airplaneQueue.add(AIRPLANE_1);
        airplaneQueue.add(AIRPLANE_2);
        airplaneQueue.add(AIRPLANE_3);
        airplaneQueue.add(AIRPLANE_4);
        airplaneQueue.add(AIRPLANE_5);
        airplaneQueue.add(AIRPLANE_6);
        airplaneQueue.add(AIRPLANE_7);
        airplaneQueue.add(AIRPLANE_8);
        airplaneQueue.add(AIRPLANE_9);
        airplaneQueue.add(AIRPLANE_10);

        Gate gate1 = new Gate("Gate 1", GateType.TELESCOPIC);
        List<Gate> gates = new ArrayList<>();
        gates.add(gate1);

        Terminal terminal = new Terminal("Terminal 1", TerminalServiceType.GATE, gates);
        List<Terminal> terminals = new ArrayList<>();
        terminals.add(terminal);

        Airport airport = new Airport("Rammstein", terminals, airplaneQueue);

        AirportService airportService = new AirportService(airport);
        airportService.airportTerminalServiceInit();
        new Thread(airportService, airport.getName() + " service").start();
    }
}

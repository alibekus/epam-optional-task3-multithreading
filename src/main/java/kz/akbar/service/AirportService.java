package kz.akbar.service;

import kz.akbar.model.Airplane;
import kz.akbar.model.Airport;
import kz.akbar.model.Terminal;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class AirportService implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Flight.class.getName());
    private Airport airport;
    private BlockingQueue<Airplane> airplanes;
    private List<Terminal> terminals;
    private List<TerminalService> terminalServices;

    public AirportService(Airport airport) {
        this.airport = airport;
        this.terminals = airport.getTerminals();
        this.airplanes = airport.getAirplanes();
    }

    public void airportTerminalServiceInit() {
        terminalServices = new ArrayList<>();
        for (int i = 0; i < terminals.size(); i++) {
            terminalServices.add(new TerminalService(terminals.get(i), airplanes));
        }
    }

    public void run() {
        LOGGER.info("Airport service started");
        for (TerminalService service : terminalServices) {
            new Thread(service).start();
        }
    }
}

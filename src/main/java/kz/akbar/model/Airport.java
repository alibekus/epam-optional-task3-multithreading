package kz.akbar.model;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Airport {

    private String name;
    private List<Terminal> terminals;
    private BlockingQueue<Airplane> airplanes;

    public Airport(String name, List<Terminal> terminals, BlockingQueue<Airplane> airplanes) {
        this.name = name;
        this.terminals = terminals;
        this.airplanes = airplanes;
    }

    public String getName() {
        return name;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public BlockingQueue<Airplane> getAirplanes() {
        return airplanes;
    }
}

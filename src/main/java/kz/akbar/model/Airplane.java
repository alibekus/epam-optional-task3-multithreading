package kz.akbar.model;

import kz.akbar.exception.FlightDistanceException;

public class Airplane {
    private final String name;
    private final int capacity;
    private final int flightRange;
    private RangeRadius radius;
    private Destination destination;

    public Airplane(String name, int capacity, int flightRange) {
        this.name = name;
        this.capacity = capacity;
        this.flightRange = flightRange;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlightRange() {
        return flightRange;
    }

    public String getName() {
        return name;
    }

    public void setRadius(RangeRadius radius) throws FlightDistanceException {
        this.radius = radius;
        if (radius.getRange() < flightRange) {
            throw new FlightDistanceException("Too far for this airplane", name);
        }
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}

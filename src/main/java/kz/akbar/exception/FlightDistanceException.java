package kz.akbar.exception;

public class FlightDistanceException extends AirplaneException{
    public FlightDistanceException(String message, String airplaneName) {
        super(message, airplaneName);
    }
}

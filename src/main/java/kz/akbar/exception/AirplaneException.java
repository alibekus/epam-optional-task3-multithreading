package kz.akbar.exception;

public class AirplaneException extends Exception {

    private String airplaneName;

    public AirplaneException(String message, String airplaneName) {
        super(message);
        this.airplaneName = airplaneName;
    }

    public AirplaneException(String message) {
        super(message);
    }

    public String getAirplaneName() {
        return airplaneName;
    }
}

package kz.akbar.model;

public class Gate {
    private String name;
    private GateType type;

    public Gate(String name, GateType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public GateType getType() {
        return type;
    }
}

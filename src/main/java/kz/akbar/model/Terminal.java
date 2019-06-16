package kz.akbar.model;

import kz.akbar.service.TerminalServiceType;

import java.util.List;

public class Terminal {

    private String name;
    private List<Gate> gates;
    private TerminalServiceType type;

    public Terminal(String name, TerminalServiceType type, List<Gate> gates) {
        this.name = name;
        this.type = type;
        this.gates = gates;
    }

    public String getName() {
        return name;
    }

    public TerminalServiceType getType() {
        return type;
    }
}

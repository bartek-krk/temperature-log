package pl.bart.temperaturelog.commands;

import pl.bart.temperaturelog.security.Credentials;

public class MeasurementDTO {
    private Credentials credentials;
    private double temperature;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}

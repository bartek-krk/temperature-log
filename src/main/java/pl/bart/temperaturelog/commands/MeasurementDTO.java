package pl.bart.temperaturelog.commands;

import pl.bart.temperaturelog.security.Credentials;

public class MeasurementDTO {
    private Long stationId;
    private double temperature;

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}

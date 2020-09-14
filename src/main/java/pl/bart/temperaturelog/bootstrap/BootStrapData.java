package pl.bart.temperaturelog.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bart.temperaturelog.models.Measurement;
import pl.bart.temperaturelog.models.Station;
import pl.bart.temperaturelog.repositories.MeasurementRepository;
import pl.bart.temperaturelog.repositories.StationRepository;

import java.time.Instant;

@Component
public class BootStrapData implements CommandLineRunner {
    private final StationRepository stationRepository;
    private final MeasurementRepository measurementRepository;

    public BootStrapData(StationRepository stationRepository, MeasurementRepository measurementRepository) {
        this.stationRepository = stationRepository;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Station s1 = new Station();
        s1.setLocation("New York");

        Station s2 = new Station();
        s2.setLocation("Washington");

        Measurement m1 = new Measurement();
        m1.setTimestamp(Instant.now().getEpochSecond());
        m1.setTemperature(22.1);
        m1.setStation(s1);


        stationRepository.save(s1);
        stationRepository.save(s2);
        measurementRepository.save(m1);
    }
}

package pl.bart.temperaturelog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bart.temperaturelog.models.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement,Long> {
    public List<Measurement> getAllByStationId(Long stationId);
    public boolean existsByStationId(Long stationId);
}

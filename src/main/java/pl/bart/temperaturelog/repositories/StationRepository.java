package pl.bart.temperaturelog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bart.temperaturelog.models.Station;

public interface StationRepository extends JpaRepository<Station,Long> {
}

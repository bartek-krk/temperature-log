package pl.bart.temperaturelog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bart.temperaturelog.models.Station;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station,Long> {
    public Optional<Station> getByeMail(String email);
}

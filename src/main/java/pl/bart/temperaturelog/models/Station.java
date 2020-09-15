package pl.bart.temperaturelog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.bart.temperaturelog.utilities.ApiKeyGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;

    @JsonIgnore
    private String eMail;

    @OneToMany(mappedBy = "station", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @OrderBy("timestamp")
    @JsonBackReference
    private Set<Measurement> measurements = new HashSet<>();

    @JsonIgnore
    private String apiKey = ApiKeyGenerator.generate();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set<Measurement> measurements) {
        this.measurements = measurements;
    }

    public String geteMail() { return eMail; }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        return id != null ? id.equals(station.id) : station.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

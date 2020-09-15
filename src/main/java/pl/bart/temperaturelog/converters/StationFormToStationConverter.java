package pl.bart.temperaturelog.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.bart.temperaturelog.commands.StationForm;
import pl.bart.temperaturelog.models.Station;

@Component
public class StationFormToStationConverter implements Converter<StationForm, Station> {
    @Override
    public Station convert(StationForm stationForm) {
        Station returnStation = new Station();
        returnStation.setLocation(stationForm.getLocation());
        returnStation.seteMail(stationForm.geteMail());
        return returnStation;
    }
}

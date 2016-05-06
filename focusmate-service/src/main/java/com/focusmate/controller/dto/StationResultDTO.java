package com.focusmate.controller.dto;

import com.focusmate.datasource.entities.Station;

public class StationResultDTO extends DTO {
    /**
     * 
     */
    private static final long serialVersionUID = -4803142812306355635L;

    private Station           station;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

}

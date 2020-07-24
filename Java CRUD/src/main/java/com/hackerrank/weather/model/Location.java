package com.hackerrank.weather.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Location {
	
    @Id
    @JsonIgnore
    Long id;
    
	@Column
    private String cityName;
    
	@Column
	private String stateName;
    
	@Column
	private Float lat;
    
	@Column
	private Float lon;

    public Location() {
    }

    public Location(String cityName, String stateName, Float lat, Float lon) {
        this.cityName = cityName;
        this.stateName = stateName;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }
    
	@Override
	public String toString() {
		return "Location [id=" + id + ", city=" + cityName + ", state=" + stateName + ", lat=" + lat + ", lon=" + lon + "]";
	}    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
}

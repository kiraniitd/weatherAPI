package com.hackerrank.weather.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackerrank.weather.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather,Long>{		
	
	//No Input
	public List<Weather> findAll();
	
	//Only Date Input
    List<Weather> findByDate(Date date);
    
    //Only latitude and longitude Input
    List<Weather> findByLocationLatAndLocationLon(Float lat,Float lon);
    
    //Weather weather = weatherRepo.findOne(pWeather);
}

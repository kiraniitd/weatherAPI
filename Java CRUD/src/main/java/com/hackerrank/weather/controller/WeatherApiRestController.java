package com.hackerrank.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class WeatherApiRestController {
	private final Logger log = LoggerFactory.getLogger(WeatherApiRestController.class);
	
	@Autowired
	WeatherRepository weatherRepo;
	
	
	@DeleteMapping(path = "/erase", produces = "application/json")
	public ResponseEntity<String> deleteWeather(
			@RequestParam(required=false) String start, 
			@RequestParam(required=false) String end,
			@RequestParam(required=false) Float lat, 
			@RequestParam(required=false) Float lon) throws ParseException {
		
		
		ResponseEntity<String> response;
		if (start == null && end == null && lat == null && lon == null) {
			weatherRepo.deleteAll();
			response = new ResponseEntity<>("Successfully deleted --> ", HttpStatus.OK);
			return response;
		} else if (start !=null && end!=null && lat!=null && lon!=null) {
			Date sdt = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			Date edt = new SimpleDateFormat("yyyy-MM-dd").parse(end);
			List<Weather> weather=weatherRepo.findByLocationLatAndLocationLon(lat, lon);
			List<Weather> selectedWeather=weather.stream().filter(w->{
										return !sdt.after(w.getDate()) && !edt.before(w.getDate());
										}).collect(Collectors.toList());

			selectedWeather.forEach(w->{
				weatherRepo.delete(w.getId());
			});
			
			response = new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
			return response;
		}

		response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return response;

	}	
	
	@PostMapping(path = "/weather", produces = "application/json")
	public ResponseEntity<Weather> addNewWeather(@RequestBody Weather we) {
		ResponseEntity<Weather> response;
	
		Weather weather = weatherRepo.findOne(we.getId());
		log.info("weatherRepo.findOne(pWeather) -->",weather);
		if (null != weather) {
			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return response;
		}
		we.getLocation().setId(we.getId());
		weather = weatherRepo.save(we);
		response = new ResponseEntity<>(weather, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping(path="/weather", produces = "application/json")
    public ResponseEntity<List<Weather>> getWeather(
    		@RequestParam(required = false) String pDate,
			@RequestParam(required = false) Float pLat, 
			@RequestParam(required = false) Float pLon) throws ParseException 
	{
		Date date=null;
		
		if(pDate != null) {
		  date = new SimpleDateFormat("yyyy-MM-dd").parse(pDate);
		}
		
		//No Input
		if (pDate == null && pLat == null && pLon == null) 
		{
			
			List<Weather> weather = weatherRepo.findAll();
			log.info("weatherRepo.findAll() -->",weather);
			
			if (weather.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
			}
	        //HTTP Status 200
	        return new ResponseEntity<>(weather, HttpStatus.OK);
		}
		else if (pDate != null )
		{
			List<Weather> weather = weatherRepo.findByDate(date);
			log.info("weatherRepo.findByDate(date) --> ",weather);
			
			if (weather.isEmpty()) {
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(weather, HttpStatus.OK);
		} 
		else if (pLat !=null && pLon !=null ) {
			List<Weather> weather = weatherRepo.findByLocationLatAndLocationLon(pLat, pLon);
			log.info("weatherRepo.findByLocationLatAndLocationLon(lat, lon) -->",weather);
			
			if (weather.isEmpty()) {
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
			}
			return new ResponseEntity<>(weather, HttpStatus.OK);
		}
		//Other return
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
	

}

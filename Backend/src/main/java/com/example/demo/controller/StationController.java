package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Station;
import com.example.demo.serviceImpl.StationServiceImpl;


import jakarta.websocket.server.PathParam;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class StationController {
	@Autowired
	private StationServiceImpl stationserviceimpl;
	
	@GetMapping("/getAllStations")
	public List<Station> getAllStations() {
		return stationserviceimpl.getAllChargingStations();
	}
	
	//Bring top 10 records
	@GetMapping("/getbylimit")
	public List<Station> getStationWithPaging(
            @RequestParam(defaultValue = "10") Integer limit){
		System.out.println(limit);
		return stationserviceimpl.getStationsByPagination(0,limit);
	}
	
	@GetMapping("/getbysorted")
	public List<Station> getStationSorted(
            @RequestParam(defaultValue = "asc") String sort,@RequestParam(defaultValue = "station_id") String param){
		System.out.println(sort);
		System.out.println(param);
		return stationserviceimpl.getStationsBySorting(sort,param);
	}
	
	@GetMapping("/getStation/{id}")
	public Station getStation(@PathVariable("id") Long id) {

		return stationserviceimpl.getChargingStation(id);
	}
	
	@PostMapping("/addStation")
	public String addStation(@RequestParam("station_image") MultipartFile file,@RequestParam("station_name") String station_name,@RequestParam("station_pricing") Double station_pricing,@RequestParam("station_address") String station_address) throws IOException {
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getName());
//		System.out.println(file.getContentType());
//		System.out.println(file.getSize());
//		System.out.println(file.getBytes());
//		System.out.println(station_name);
//		System.out.println(station_address);
//		System.out.println(station_pricing);
		System.out.println(file.getBytes());
		return stationserviceimpl.addChargingStation(station_name,station_address,station_pricing,file.getBytes());
		
	}
	
	@PutMapping("/updateStation/{id}")
	public String updateStation(@PathVariable("id") Long id,@RequestParam("station_image") MultipartFile file,@RequestParam("station_name") String station_name,@RequestParam("station_pricing") Double station_pricing,@RequestParam("station_address") String station_address) throws IOException {
//		System.out.println(id);
		return stationserviceimpl.changeDetailsStation(id,station_name,station_address,station_pricing,file.getBytes());
	}	
	
	@DeleteMapping("/deleteStation/{id}")
	public String deleteStation(@PathVariable("id") Long id) {
		
		return stationserviceimpl.deleteStation(id);
	}
	
	@GetMapping("/testing")
	public String testing() {
		return "tesing comlpeted";
	}
	
}

package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Station;

public interface StationService {
	List<Station> getAllChargingStations(); 
	Station getChargingStation(Long id); 
	String addChargingStation(String station_name,String station_address,Double station_pricing,byte[] gg); 
	String changeDetailsStation(Long id, String station_name,String station_address,Double station_pricing,byte[] gg); 
	String deleteStation(Long id);
	List<Station> getStationsByPagination(int pageNo, int pageSize);
	List<Station> getStationsBySorting(String order,String parameter);
}

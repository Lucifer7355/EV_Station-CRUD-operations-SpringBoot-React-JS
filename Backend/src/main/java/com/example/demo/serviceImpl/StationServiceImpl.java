package com.example.demo.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.swing.SortOrder;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.*;
import com.example.demo.repository.StationRepository;
import com.example.demo.service.StationService;

import org.springframework.data.domain.Sort;
import jakarta.websocket.server.ServerEndpoint;

@Service
public class StationServiceImpl implements StationService{

	@Autowired
	private StationRepository stationrepository;
	//GET request to list all the charging stations details
	@Override
	public List<Station> getAllChargingStations() {
		List<Station> ll = stationrepository.findAll();
		ll.forEach(it->{
			it.setStation_image(decompressBytes(it.getStation_image()));
		});
		return ll;
	}
//	GET single charging station for the given id
	@Override
	public Station getChargingStation(Long id) {
		Station meo = stationrepository.findById(id).get();
		Station gg = new Station();
		gg.setStation_id(meo.getStation_id());
		gg.setStation_address(meo.getStation_address());
		gg.setStation_image(decompressBytes(meo.getStation_image()));
		gg.setStation_name(meo.getStation_name());
		gg.setStation_pricing(meo.getStation_pricing());
		return gg;
	}

	
	
//	Delete a particular station from database.
	@Override
	public String deleteStation(Long id) {
		stationrepository.deleteById(id);
		return "Station Deleted Successfully";
	}
	
	// uncompress the image bytes before returning it to the angular application
		public static byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}
		// compress the image bytes before storing it in the database
		public static byte[] compressBytes(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
			return outputStream.toByteArray();
		}
//		POST add charging station to database.
		
//		PUT change the details for the station in the database.
		@Override
		public String changeDetailsStation(Long id, String station_name, String station_address,
			Double station_pricing, byte[] tt) {
			Station meo = stationrepository.findById(id).get();
			meo.setStation_address(station_address);
			meo.setStation_image(compressBytes(tt));
			meo.setStation_name(station_name);
			meo.setStation_pricing(station_pricing);
			stationrepository.save(meo);
			return "Successfully updated the record";
		}
		@Override
		public List<Station> getStationsByPagination(int pageNo, int pageSize) {
	        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
	        Page<Station> pagingUser = stationrepository.findAll(pageRequest);
	        List<Station> ll = pagingUser.getContent();
	        ll.forEach(it->{
	    			it.setStation_image(decompressBytes(it.getStation_image()));
	    		});
	        return ll;
		}
		@Override
		public String addChargingStation(String station_name, String station_address, Double station_pricing,
				byte[] gg) {
			Station gg1 = new Station(station_name, compressBytes(gg), station_pricing, station_address);
			stationrepository.save(gg1);
			return "Station Added Successfully";
		}
		@Override
		public List<Station> getStationsBySorting(String order, String parameter) {
			List<Station> gg = stationrepository.findAll();
			if(parameter.equalsIgnoreCase("station_price")) {
				Collections.sort(gg, new Comparator<Station>(){
		            @Override
		            public int compare(Station a1, Station a2) {
		                 return (int) (a1.getStation_pricing()-a2.getStation_pricing());
		            }});
			}
			if(parameter.equalsIgnoreCase("station_address")) {
				Collections.sort(gg, new Comparator<Station>(){
		            @Override
		            public int compare(Station a1, Station a2) {
		                 return (int) (a1.getStation_address().compareTo(a2.getStation_address()));
		            }});
			}
			if(parameter.equalsIgnoreCase("station_name")) {
				Collections.sort(gg, new Comparator<Station>(){
		            @Override
		            public int compare(Station a1, Station a2) {
		                 return (int) (a1.getStation_name().compareTo(a2.getStation_name()));
		            }});
			}
			if(parameter.equalsIgnoreCase("station_id")) {
				Collections.sort(gg, new Comparator<Station>(){
		            @Override
		            public int compare(Station a1, Station a2) {
		                 return (int) (a1.getStation_id() - a2.getStation_id());
		            }});
			}
			if(order.equalsIgnoreCase("desc")) {
				Collections.reverse(gg);
			}
			gg.forEach(it->{
	    			it.setStation_image(decompressBytes(it.getStation_image()));
	    		});
			return gg;
		}
		
		
}

package com.example.demo.model;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table
public class Station {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "station_id")
	private Long station_id;
	
	@Column(name = "station_name")
	private String station_name;
	
	@Lob
    @Column(name = "station_image", length = Integer.MAX_VALUE)
    private byte[] station_image;
	
	@Column(name = "station_pricing")
	private Double station_pricing;	
	
	@Column(name = "station_address")
    private String station_address;

	public Station(String station_name, byte[] station_image, Double station_pricing, String station_address) {
		super();
		this.station_name = station_name;
		this.station_image = station_image;
		this.station_pricing = station_pricing;
		this.station_address = station_address;
	}

	public Station(Long station_id, String station_name, byte[] station_image, Double station_pricing,
			String station_address) {
		super();
		this.station_id = station_id;
		this.station_name = station_name;
		this.station_image = station_image;
		this.station_pricing = station_pricing;
		this.station_address = station_address;
	}

	public Station() {
		super();
	}

	public Long getStation_id() {
		return station_id;
	}

	public void setStation_id(Long station_id) {
		this.station_id = station_id;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	public byte[] getStation_image() {
		return station_image;
	}

	public void setStation_image(byte[] station_image) {
		this.station_image = station_image;
	}

	public Double getStation_pricing() {
		return station_pricing;
	}

	public void setStation_pricing(Double station_pricing) {
		this.station_pricing = station_pricing;
	}

	public String getStation_address() {
		return station_address;
	}

	public void setStation_address(String station_address) {
		this.station_address = station_address;
	}

	@Override
	public String toString() {
		return "Station [station_id=" + station_id + ", station_name=" + station_name + ", station_image="
				+ Arrays.toString(station_image) + ", station_pricing=" + station_pricing + ", station_address="
				+ station_address + ", getStation_id()=" + getStation_id() + ", getStation_name()=" + getStation_name()
				+ ", getStation_image()=" + Arrays.toString(getStation_image()) + ", getStation_pricing()="
				+ getStation_pricing() + ", getStation_address()=" + getStation_address() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	
    
   
	
}

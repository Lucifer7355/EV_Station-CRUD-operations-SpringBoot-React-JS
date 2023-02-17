package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Station;


@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
	@Query("from Station order by :n")
	public List<Station> DoctorSuggestions(@Param("n") String meo);
}

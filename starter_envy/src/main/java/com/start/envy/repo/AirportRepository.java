package com.start.envy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
//import org.springframework.stereotype.Repository;

import com.start.envy.model.AirportIndex;


public interface AirportRepository  extends Repository<AirportIndex,String>{
	@Query("select iata from AirportIndex")
	List<AirportIndex> findByIata(String iata);

	@Query("select latitude from AirportIndex where iata = ?1")
	Double findByLatitude(String iata);
	
	@Query("select longitude from AirportIndex where iata = ?1")
	Double findByLongitude(String iata);

	
	@Query("select airport from AirportIndex where iata = ?1")
	String findByAirport(String name);

}

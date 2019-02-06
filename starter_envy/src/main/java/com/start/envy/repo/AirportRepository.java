package com.start.envy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
//import org.springframework.stereotype.Repository;

import com.start.envy.model.AirportIndex;


public interface AirportRepository  extends Repository<AirportIndex,String>{
	@Query("select iata from AirportIndex")
	List<AirportIndex> findByIata(String iata);

}

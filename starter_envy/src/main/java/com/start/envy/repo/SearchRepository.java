package com.start.envy.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.start.envy.model.SearchDetails;

public interface SearchRepository extends CrudRepository<SearchDetails,String>{

}

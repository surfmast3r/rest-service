package com.ingsw.restservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ingsw.restservice.model.Accommodation;


@Repository
public interface AccommodationRepository extends CrudRepository<Accommodation, Long>{

}

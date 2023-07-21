package dev.spring.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.spring.petclinic.model.Pet;
@Repository
public interface PetRepository extends CrudRepository<Pet, Long>{
	
}

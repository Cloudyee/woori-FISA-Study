package dev.spring.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.PetType;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}

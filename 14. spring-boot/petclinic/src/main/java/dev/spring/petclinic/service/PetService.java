package dev.spring.petclinic.service;

import org.springframework.stereotype.Service;

import dev.spring.petclinic.model.Pet;
@Service
public interface PetService extends CrudService<Pet, Long>{


}

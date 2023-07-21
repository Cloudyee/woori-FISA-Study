package dev.spring.petclinic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.repository.PetRepository;
import lombok.Getter;
@Service
@Getter
public class PetServiceImp implements PetService {

	private final PetRepository petRepository;
	
	public PetServiceImp(PetRepository petRepository) {
		super();
		this.petRepository = petRepository;
	}
		
	@Override
	public List<Pet> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pet findById(Long id) {
		return petRepository.findById(id).orElse(null);
	}

	@Override
	public Pet save(Pet pet) {
		return petRepository.save(pet);
	}

	@Override
	public void delete(Pet object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}





}

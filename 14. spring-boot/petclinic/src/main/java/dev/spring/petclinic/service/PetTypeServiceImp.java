package dev.spring.petclinic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.model.PetType;
import dev.spring.petclinic.repository.PetTypeRepository;
@Service
public class PetTypeServiceImp implements PetTypeService {

	private PetTypeRepository petTypeRepository;
	
	public PetTypeServiceImp(PetTypeRepository petTypeRepository) {
		super();
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public List<PetType> findAll() {
		return (List<PetType>) petTypeRepository.findAll();
	}

	@Override
	public PetType findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PetType save(PetType object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PetType object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}



}

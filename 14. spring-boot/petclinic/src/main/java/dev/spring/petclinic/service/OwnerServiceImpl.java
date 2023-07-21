package dev.spring.petclinic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.spring.petclinic.dto.OwnerRequest;
import dev.spring.petclinic.dto.OwnerResponse;
import dev.spring.petclinic.dto.PetResponse;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.repository.OwnerRepository;


@Service
public class OwnerServiceImpl implements OwnerService {

	private final OwnerRepository ownerRepository;

	public OwnerServiceImpl(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public List<OwnerResponse> findAll() {
		
		List<Owner> owners = new ArrayList<>();
		ownerRepository.findAll().forEach(owners::add);
		
		List<OwnerResponse> ownerResponse = owners.stream()
				.map(OwnerResponse::from)
				.collect(Collectors.toList());
		
		return ownerResponse;
	}

	@Override
	public OwnerResponse findById(Long id) {

		Owner owner = ownerRepository.findById(id).orElseThrow();
		
      return OwnerResponse.from(owner); 
	}

	@Override
    public OwnerResponse save(OwnerRequest ownerRequest) {
		Owner owner = Owner.from(ownerRequest);
		
		ownerRepository.save(owner);
		
		OwnerResponse ownerResponse = OwnerResponse.from(owner);
		System.out.println("ownerResponse" + ownerResponse);
		
        return ownerResponse;
    }

	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public List<Owner> findAllByLastNameLike(String lastName) {
		return ownerRepository.findAllByLastNameLike(lastName);
	}


}
//
//@Service // 구현체 bean으로 등록
//public class OwnerServiceImpl implements OwnerService {
//	// Impl: Implement의 줄임 표현
//	// -> OwnerService 인터페이스를 구현한 구현 클래스 OwnerServiceImpl
//
//	private final OwnerRepository ownerRepository;
//
//	public OwnerServiceImpl(OwnerRepository ownerRepository) {
//		this.ownerRepository = ownerRepository;
//	}
//
//	
//	
//	@Override
//	public List<Owner> findAll() {
//		return ownerRepository.findAll();
//	}
//	
//	@Override
//	public Owner findByLastName(String lastName) {
//		return null;
//	}
//
//	@Override
//	public List<Owner> findAllByLastNameLike(String lastName) {
//		return ownerRepository.findAllByLastNameLike(lastName);
//	}
//
//	@Override
//	public Owner findById(Long ownerId) {
//		// java.util.Optional에서 import
//
////		Optional<Owner> owner = ownerRepository.findById(ownerId);
////		if(owner.isPresent()) {//owner가 null이 아니면
////			return owner.get(); //owner 객체 반환
////		}
////		return null;
////		
//		//위 코드를 한줄로 표현
//		return ownerRepository.findById(ownerId).orElse(null);
//	}
//	
//	@Override
//	public Owner save(Owner owner) {
//		return ownerRepository.save(owner);
//	}
//
//
//	@Override
//	public void delete(Owner object) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteById(Long id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//

	
	/*
	 * 이상적인 인터페이스 - 구현체 네이밍 List - 인터페이스 'Array'List - 구현체 'Linked'List - 구현체
	 * 
	 * OwnerFinder - 조회 관련 메서드가 정의된 인터페이스 OwnerEditor - Insert, Update, Delete 관련
	 * 메서드가 정의된 인터페이스
	 * 
	 * OwnerManager - OwnerFinder, Editor 구현체
	 */

//}

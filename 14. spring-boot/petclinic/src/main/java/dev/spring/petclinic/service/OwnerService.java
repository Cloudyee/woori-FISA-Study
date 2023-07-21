package dev.spring.petclinic.service;

import java.util.List;

import dev.spring.petclinic.dto.OwnerRequest;
import dev.spring.petclinic.dto.OwnerResponse;
import dev.spring.petclinic.model.Owner;

public interface OwnerService extends CrudService<OwnerResponse, OwnerRequest, Long> {
	Owner findByLastName(String lastName);

	List<Owner> findAllByLastNameLike(String lastName);

}
//public interface OwnerService extends CrudService<Owner, Long>{
//	//lastName과 일치하는 한명의 Owner 정보 반환
//	Owner findByLastName(String lastName);
//
//	//SQL에서 LIKE연산자를 활용한 lastName 검색 기능
//	List<Owner>findAllByLastNameLike(String lastName);
//	
//	List<Owner> findAll();
//
//}

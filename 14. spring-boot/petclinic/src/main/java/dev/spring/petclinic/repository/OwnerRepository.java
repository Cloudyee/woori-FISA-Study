package dev.spring.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.spring.petclinic.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

	// lastName과 일치하는 한 명의 Owner 정보 반환
	Owner findByLastName(String lastName);

	// SQL에서 LIKE 연산자를 활용한 lastName 검색 기능
	List<Owner> findAllByLastNameLike(String lastName);

	List<Owner> findAll();
	// SQL에서 owner 입력 정보를 추가하는 기능
//	Owner save(Owner owner);

}

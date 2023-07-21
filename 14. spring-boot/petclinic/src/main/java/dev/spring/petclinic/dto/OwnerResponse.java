package dev.spring.petclinic.dto;

import java.util.List;
import java.util.stream.Collectors;

import dev.spring.petclinic.dto.OwnerRequest.OwnerRequestBuilder;
import dev.spring.petclinic.model.Owner;
import lombok.Builder;
import lombok.Getter;

/*
 * DTO 클래스 네이밍 방식
 * 1. XxxDTO, XxxDto
 * 2. Request or Response 용도로 사용하기 때문에 그 이름을 부각해서 XxxRequest, XxxResponse
 * 3. 엔티티 클래스의 이너 클래스로 작성
 */

@Getter
public class OwnerResponse {
	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String telephone;
	private List<PetResponse> pets;
	
	@Builder
	private OwnerResponse(Long id, String firstName, String lastName, String address, String city, String telephone,
			List<PetResponse> pets) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
		this.pets = pets;
	}

	public static OwnerResponse from(Owner owner) {
		final Long id = owner.getId();
		final String firstName = owner.getFirstName();
		final String lastName = owner.getLastName();
		final String address = owner.getAddress();
		final String city = owner.getCity();
		final String telephone = owner.getTelephone();
		final List<PetResponse> pets = owner.getPets().stream()
								.map(PetResponse::from)
								.collect(Collectors.toList());
		return new OwnerResponse(id, firstName, lastName, address, city, telephone, pets);
	}


	
}

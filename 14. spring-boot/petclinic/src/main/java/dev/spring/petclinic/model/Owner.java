package dev.spring.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dev.spring.petclinic.dto.OwnerRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "owners")
public class Owner extends Person {

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "telephone")
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private List<Pet> pets = new ArrayList<>();

	@Builder
	public Owner(Long id, String firstName, String lastName, String address, String city, String telephone,
			List<Pet> pets) {
		super(id, firstName, lastName);
		this.address = address;
		this.city = city;
		this.telephone = telephone;
		this.pets = pets;
	}

	public static Owner from(OwnerRequest ownerRequest) {
		final String firstName = ownerRequest.getFirstName();
		final String lastName = ownerRequest.getLastName();
		final String address = ownerRequest.getAddress();
		final String city = ownerRequest.getCity();
		final String telephone = ownerRequest.getTelephone();

		return Owner.builder().firstName(firstName).lastName(lastName).address(address).city(city).telephone(telephone)
				.pets(new ArrayList<>()).build();
	}
}

//package dev.spring.petclinic.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
///*
// * Pet 주인
// * 한 명의 주인은 여러 마리의 펫을 가지고 있을 수 있다.
// * 
// * Field Summary
// * String address; // 주소
// * String city; // 도시
// * String telephone; // 번호
// * List<Pet> pets; // 펫(두 마리 이상)
// */
//
//@Getter // Lombok getter()
//@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자, 접근레벨 Protected
//@Entity // JPA를 통해 관리하기 위해 Entity로 지정
//@Table(name = "owners") // 실제 생성될 DB 테이블명 owners
//public class Owner extends Person {
//	@Column(name = "address")
//	private String address;
//
//	@Column(name = "city")
//	private String city;
//
//	@Column(name = "telephone")
//	private String telephone;
//
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
//	private List<Pet> pets = new ArrayList<>();
//
//	@Builder
//	public Owner(Long id, String firstName, String lastName, String address, String city, String telephone,
//			List<Pet> pets) {
//		super(id, firstName, lastName);
//		this.address = address;
//		this.city = city;
//		this.telephone = telephone;
//		this.pets = pets;
//	}
//
//}

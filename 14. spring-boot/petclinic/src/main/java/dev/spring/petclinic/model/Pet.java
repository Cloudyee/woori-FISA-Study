package dev.spring.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Field Summary
 * String name;
 * LocalDate birthDate; // 컬럼명 birth_date
 * 
 * pet은 한 명의 Owner를 가지고 있다.
 * Owner owner; 
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType petType;

	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@Builder
	public Pet(Long id, String name, PetType petType, LocalDate birthDate, Owner owner) {
		super(id);
		this.name = name;
		this.petType = petType;
		this.birthDate = birthDate;
		this.owner = owner;
	}

}
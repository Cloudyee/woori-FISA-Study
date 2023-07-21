package dev.spring.petclinic.model;

import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass

public class Person extends BaseEntity{
	private String firstName;
	private String lastName;
	
	public Person(Long id, String firstName, String lastName) {
		super(id);
		this.firstName=firstName;
		this.lastName=lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
}

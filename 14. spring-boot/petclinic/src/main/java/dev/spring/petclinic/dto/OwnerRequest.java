package dev.spring.petclinic.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class OwnerRequest {
	
	@NotNull @Pattern(regexp = "^[a-zA-Z]*$") @Size(min = 1, max = 30)
	private String firstName;
	@NotNull @Pattern(regexp = "^[a-zA-Z]*$") @Size(min = 1, max = 30) 
	private String lastName;
	@NotNull @Size(min = 1, max = 255)
	private String address;
	@NotNull @Size(min = 1, max = 80) 
	private String city;
	@NotNull @Pattern(regexp = "^[0-9]*$") @Size(min = 1, max = 20)
	private String telephone;

	@Builder
	public OwnerRequest(String firstName, String lastName, String address, String city, String telephone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}

	
}
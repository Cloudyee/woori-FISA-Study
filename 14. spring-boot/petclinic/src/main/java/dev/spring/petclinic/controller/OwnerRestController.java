package dev.spring.petclinic.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import dev.spring.petclinic.dto.OwnerRequest;
import dev.spring.petclinic.dto.OwnerResponse;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@RestController // @Controller + @ResponseBody
@RequestMapping("/api/owners")
public class OwnerRestController {
	private final OwnerService ownerService;

	public OwnerRestController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@GetMapping
	public ResponseEntity<List<OwnerResponse>> listOwners() {

		List<OwnerResponse> owners = ownerService.findAll();

		if (owners.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(owners, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<OwnerResponse> addOwner(@Valid @RequestBody OwnerRequest ownerRequest) {
		
		
		OwnerResponse ownerResponse = ownerService.save(ownerRequest);
		
		return new ResponseEntity<>(ownerResponse, HttpStatus.CREATED);
	}	
//	private OwnerService ownerservice;
//	private Owner owner;
//
//	public OwnerRestController(OwnerService ownerservice) {
//		this.ownerservice = ownerservice;
//	}
//
//	@GetMapping
////	public ResponseEntity<List<Owner>> listOwners(){
//	public List<OwnerResponse> listOwners() {
//		List<Owner> owners = ownerservice.findAll();
//		List<OwnerResponse> ownerResponse = owners.stream() //List<Owner>의 owner객체들을 풀어헤쳐
//				.map(OwnerResponse::from) // OwnerResponse.from(Owner owner)의 인수로 객체를 하나씩 전달해서 처리
//				.collect(Collectors.toList()); // 처리가 끝난 개별 OwnerResponse 객체들을 List<OwnerResponse>로 만들어라.
//		
//		return ownerResponse;
//	}
//	
//	@PostMapping
//	public ResponseEntity<OwnerResponse> addOwner(@Valid @RequestBody OwnerRequest ownerRequest){
//		System.out.println(ownerRequest);
//		
//		return null;
//	}

}

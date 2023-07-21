//package dev.spring.petclinic.controller;
//
//import java.util.List;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import dev.spring.petclinic.model.Owner;
//import dev.spring.petclinic.model.Pet;
//import dev.spring.petclinic.model.PetType;
//import dev.spring.petclinic.service.OwnerService;
//import dev.spring.petclinic.service.PetService;
//import dev.spring.petclinic.service.PetTypeService;
//
//@Controller
//@RequestMapping("/owners/{ownerId}")
//
//public class PetController {
//	private PetService petservice;
//
//	private OwnerService ownerservice;
//
//	private PetTypeService petTypeService;
//
//	public PetController(PetService petservice, OwnerService ownerService, PetTypeService petTypeService) {
//		this.petservice = petservice;
//		this.ownerservice = ownerService;
//		this.petTypeService = petTypeService;
//
//	}
//
//	@GetMapping
//	public ResponseEntity<List<OwnerResponse>> listOwners() {
//
//		List<OwnerResponse> owners = ownerService.findAll();
//
//		if (owners.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(owners, HttpStatus.OK);
//	}
//
//	@PostMapping
//	public ResponseEntity<OwnerResponse> addOwner(@Valid @RequestBody OwnerRequest ownerRequest) {
//		
//		
//		OwnerResponse ownerResponse = ownerService.save(ownerRequest);
//		
//		return new ResponseEntity<>(ownerResponse, HttpStatus.CREATED);
//	}	
//  
//	
//	// 펫 정보를 추가 페이지로 이동
////	@GetMapping("/pets/new")
////	public String processCreationForm(Pet pet,PetType petType, Model model,@PathVariable("ownerId") Long ownerId ) {
////		Owner owner = ownerservice.findById(ownerId);
////		model.addAttribute("owner", owner);
////		model.addAttribute("pet", Pet.builder().build());
////		List<PetType>types = petTypeService.findAll();
////		model.addAttribute("types", types);
////		return "pets/createOrUpdatePetForm";
////	}
////
////	@PostMapping("/pets/new")
////	public String initUpdatePetForm(Pet pet, Model model) {
////		Pet savedPet = petservice.save(pet);
////		
////		model.addAttribute("pet", savedPet);
////		return "redirect:/owners/"+ savedPet.getId();
////	}
//}

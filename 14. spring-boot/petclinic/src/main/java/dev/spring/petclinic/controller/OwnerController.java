//package dev.spring.petclinic.controller;
//
//import java.util.List;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import dev.spring.petclinic.model.Owner;
//import dev.spring.petclinic.service.OwnerService;
//
///*
// * Servlet에서는 InsertController, Llistcontroller등 개별적으로 컨트롤러 작성
// * Spring에서는 Owner와 관련된 데이터 처리는 하나의 Controller에서 하는 편
// */
//@RequestMapping("/owners") // localhost:8080/owners로 요청이 올 경우 동작
//@Controller // or @Component, bean으로 등록
//public class OwnerController {
//	private OwnerService ownerservice;
//
//	public OwnerController(OwnerService ownerservice) {
//		this.ownerservice = ownerservice;
//	}
//
//	// find 경로로 get 요청이 왔을 경우 findOwner가 호출되도록 작성, 테스트
//	@GetMapping(path = "/find")
//	public String findOwner(Model model) {
//		model.addAttribute("owner", Owner.builder().build());
//
//		return "owners/findOwners";
//	}
//
//	@GetMapping
//	public String processFindform(Owner owner, Model model) {
//		// 별도의 파라미터 값 없이 /owners로 요청할 경우 모든 Owners 데이터 반환
//		if (owner.getLastName() == null) {
//			owner.setLastName(""); // 빈 값으로 검색할 수 있도록 공백 문자열 값 설정
//		}
//
//		// SQL에서 LIKE 연산자를 활용한 lastName 검색 기능
//		// 쿼리 메서드 - 메서드의 이름만 규칙에 맞게 작성하면 그에 해당하는 SQL쿼리가 작성됨
//		List<Owner> owners = ownerservice.findAllByLastNameLike("%" + owner.getLastName() + "%");
//
//		// key 값으로 listOwners지정, value값으로 owners
//		model.addAttribute("listOwners", owners);
//
//		/*
//		 * List<Owner>인 owners를 가지고 조건식으로 분기 조건1. 해당하는 owner가 없을 경우 - owners/findOwners로
//		 * 페이지 이동 조건2. owner가 1명일 경우, 경로에 owners/{ownerId값} 조건3. 2명 이상의 owner일 경우
//		 */
//
//		if (owners.isEmpty()) {
//			// 유효성처리
//			return "owners/findOwners";
//		} else if (owners.size() == 1) {
//			return "redirect:/owners/" + owners.get(0).getId();// showOwner()로 요청
//		}
//		return "owners/ownersList";
//	}
//
//	@GetMapping("/{ownerId}") // localhost:8080/owners/1 , owners/2...
//	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
//		// view이름은 owners/ownerDetails로 이동
//		ModelAndView mnv = new ModelAndView();
//		mnv.setViewName("owners/ownerDetails");
//
//		// id값에 일치하는 1명의 owner데이터 조회 기능
//		// ownerService.findById(ownerId); 하는 코드를 작성
//		Owner owner = ownerservice.findById(ownerId);
//		mnv.addObject(owner);
//
//		return mnv;
//
//	}
//
//	// Add Owner 버튼 클릭시 동작할 메서드
//	@GetMapping(path = "/new")
//	public String initCreationForm(Model model) {
//		model.addAttribute("owner", Owner.builder().build());
//		return "owners/createOrUpdateOwnerForm";
//	}
//
//	// owner 객체를 DB에 추가하는 기능으로 동작할 메서드
//	// 위와 경로가 달라서(get, post)구분이 가능하다.
//	@PostMapping(path = "/new")
//	public String processCreationForm(Owner owner) {
//		// 파라미터로 전달받은Owner owner가 입력한 새로은 owner의 정보의 값이 담겨있다.
//		Owner savedOwner = ownerservice.save(owner);
//		// 폼으로 부터 전달받은 값을 가지고 service에 넘겨서 DB 등록 처리 수행
//
//		return "redirect:/owners/" + savedOwner.getId();
//	}
//
//	@GetMapping("/{ownerId}/edit")
//	public String initUpdateOwnerForm(Owner owner, Model model, @PathVariable("ownerId") Long ownerId) {
//		Owner editOwner = ownerservice.findById(ownerId);
//		
//		model.addAttribute("owner", editOwner);
//		return "owners/createOrUpdateOwnerForm";
//	}
//
//	@PostMapping("/{ownerId}/edit")
//	public String processUpdateOwnerForm(Owner editOwner, @PathVariable("ownerId") Long ownerId) {
//		editOwner.setId(ownerId);
//	    Owner updatedOwner = ownerservice.save(editOwner);
//	    return "redirect:/owners/" + updatedOwner.getId();
//	}
//}
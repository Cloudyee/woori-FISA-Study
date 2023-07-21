package dev.spring.petclinic.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.spring.petclinic.dto.OwnerRequest;
import dev.spring.petclinic.dto.OwnerResponse;
import dev.spring.petclinic.service.OwnerService;

//@SpringBootTest // 테스트 케이스 실행 시 테스트에 필요한 설정과 빈들을 자동으로 초기화해주는 역할
@WebMvcTest(OwnerRestController.class)
class OwnerRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	OwnerService ownerService;
	
	private List<OwnerResponse> owners;

	@BeforeEach
	void setUp() throws Exception {
		owners = new ArrayList<>();
		owners.add(OwnerResponse.builder().id(1L).firstName("George").lastName("Franklin").address("110 W. Liberty St.").city("Madison").telephone("6085551023").build());	
	}

	@Test
	@DisplayName("전체 Owners 목록을 조회하였을 경우 첫 번째 owner는 George이다.")
	void testListOwners() throws Exception {
		given(ownerService.findAll()).willReturn(owners);
		
		mockMvc.perform(get("/api/owners"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.[0].id").exists())
			.andExpect(jsonPath("$.[0].firstName").exists())
			.andExpect(jsonPath("$.[0].lastName").exists())
			.andExpect(jsonPath("$.[0].address").exists())
			.andExpect(jsonPath("$.[0].city").exists())
			.andExpect(jsonPath("$.[0].telephone").exists())
			.andDo(print());
		
		verify(ownerService).findAll();
	}

	@Test
	void testAddOwner() throws Exception {
		OwnerRequest ownerRequest = OwnerRequest.builder()
											.firstName("kakao")
											.lastName("park")
											.address("makao")
											.city("hongkong")
											.telephone("01055555555")
											.build();
		
		OwnerResponse ownerResponse = OwnerResponse.builder()
											.firstName("kakao")
											.lastName("park")
											.address("makao")
											.city("hongkong")
											.telephone("01055555555")
											.build();

		given(ownerService.save(any())).willReturn(ownerResponse);
		
		
		// ObjectMapper: java 객체를 JSON 형태로 직렬화 해주는 클래스
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(ownerRequest);
		System.out.println(json);
		
		// content(): HTTP Body에 넣을 내용
		mockMvc.perform(post("/api/owners").content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated()) // status code: 201
		.andExpect(jsonPath("$.firstName").exists())
		.andExpect(jsonPath("$.lastName").value("park"))
		.andExpect(jsonPath("$.address").value("makao"))
		.andExpect(jsonPath("$.city").value("hongkong"))
		.andExpect(jsonPath("$.telephone").value("01055555555"))
		.andDo(print());
		
		verify(ownerService).save(refEq(ownerRequest));
	}

}

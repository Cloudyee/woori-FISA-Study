package dev.spring.petclinic.controller;

//===== import code ===== //

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;

//===== import code ===== //
@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;

	@InjectMocks // @Mock, @MockBean
	OwnerController controller;

	private MockMvc mockMvc;

	@BeforeEach // 매 테스트 케이스 수행 전에 실행되는 코드
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	@DisplayName("/find로 요청하였을 경우 findOwners.html을 렌더링한다.")
	void testFindOwner() throws Exception {
		mockMvc.perform(get("/owners/find")) // get() -> HTTP Get 요청할 때 사용하는 메서드
				.andExpect(status().isOk()).andExpect(view().name("owners/findOwners"))
				.andExpect(model().attributeExists("owner"));
	}

	@Test // ownersList.html 테스트
	void testProcessFindFormReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList( // 2개의 더미 Owner 객체를 보관하고 있는 리스트를
																						// 반환하다고 가정
				Owner.builder().id(1L).build(), Owner.builder().id(2L).build()));

		mockMvc.perform(get("/owners")).andExpect(status().isOk()).andExpect(view().name("owners/ownersList"))
				.andExpect(model().attribute("listOwners", hasSize(2)));
	}

	@Test // ownerDetails.html 테스트
	void testProcessFindFormReturnOne() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1l).build()));

		mockMvc.perform(get("/owners")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
	}

	@Test // 공백으로 검색하였을 경우 전체 owner 목록 반환 테스트(ownersList.html)
	void testProcessFindFormEmptyReturnMany() throws Exception {
		when(ownerService.findAllByLastNameLike(anyString()))
				.thenReturn(Arrays.asList(Owner.builder().id(1l).build(), Owner.builder().id(2l).build()));

		mockMvc.perform(get("/owners").param("lastName", "")).andExpect(status().isOk())
				.andExpect(view().name("owners/ownersList")).andExpect(model().attribute("listOwners", hasSize(2)));
	}

	@Test
	void testShowOwner() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

		mockMvc.perform(get("/owners/123"))
					.andExpect(status().isOk())
					.andExpect(view().name("owners/ownerDetails"))
					.andExpect(model().attribute("owner", hasProperty("id", is(1L))));
	}

	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/createOrUpdateOwnerForm"))
				.andExpect(model().attributeExists("owner"));

		verifyNoInteractions(ownerService);
	}

	@Test
	void testProcessCreationForm() throws Exception {
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());

		mockMvc.perform(post("/owners/new"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"))
				.andExpect(model().attributeExists("owner"));

		verify(ownerService).save(ArgumentMatchers.any());
	}

}

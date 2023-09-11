package com.capstone.progettofinale;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capstone.progettofinale.payload.LoginSuccessfullPayload;
import com.capstone.progettofinale.payload.UserLoginPayload;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
//@WebAppConfiguration
class ProgettofinaleApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() throws Exception {

		UserLoginPayload p = new UserLoginPayload("super.admin@test.com", "1234");
		mvc.perform(MockMvcRequestBuilders.post("/auth/login").accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(p))).andDo(new ResultHandler() {

					@Override
					public void handle(MvcResult result) throws Exception {
						String payload = result.getResponse().getContentAsString();
						LoginSuccessfullPayload lsp = new ObjectMapper().readValue(payload,
								LoginSuccessfullPayload.class);
						log.info("auth token: " + lsp.getAccessToken());

						mvc.perform(
								MockMvcRequestBuilders.get("/mete/città").header("Authorization", lsp.getAccessToken()))
								.andExpect(status().isOk());

					}
				});
	}

}

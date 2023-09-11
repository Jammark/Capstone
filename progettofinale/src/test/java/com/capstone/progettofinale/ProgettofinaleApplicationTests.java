package com.capstone.progettofinale;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.capstone.progettofinale.auth.SecurityConfig;
import com.capstone.progettofinale.payload.LoginSuccessfullPayload;
import com.capstone.progettofinale.payload.UserLoginPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@WebAppConfiguration
@Import(SecurityConfig.class)
@EnableWebMvc

class ProgettofinaleApplicationTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;
	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter
				.setObjectMapper(new ObjectMapper().registerModule(new ParameterNamesModule()));
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity())

				.build();
	}
	@Test
	void contextLoads() throws Exception {


		UserLoginPayload p = new UserLoginPayload("super.admin@test.com", "1234");
		mvc.perform(MockMvcRequestBuilders.post("/auth/login").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(p))).andDo(new ResultHandler() {

					@Override
					public void handle(MvcResult result) throws Exception {
						String payload = result.getResponse().getContentAsString();
						LoginSuccessfullPayload lsp = mapper.readValue(payload,
								LoginSuccessfullPayload.class);
						log.info("auth token: " + lsp.getAccessToken());

						mvc.perform(
								MockMvcRequestBuilders.get("/mete/città").header("Authorization",
										"Bearer " + lsp.getAccessToken()))
								.andExpect(status().isOk());

						mvc.perform(MockMvcRequestBuilders.get("/mete/città/9").header("Authorization",
								"Bearer " + lsp.getAccessToken())).andExpect(status().isOk())
								.andExpect(jsonPath("$.nome").value("Roma"));

					}
				});
	}

}

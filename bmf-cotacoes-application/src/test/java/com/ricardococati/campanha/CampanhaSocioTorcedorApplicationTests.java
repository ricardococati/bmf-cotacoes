package com.ricardococati.campanha;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.ricardococati.config.ObjectMapperConfig;
import com.ricardococati.model.Campanha;
import com.ricardococati.usecase.CampanhaUsecase;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CampanhaSocioTorcedorApplicationTests {

	@Autowired
	protected WebApplicationContext context;

	@MockBean
	private CampanhaUsecase campanhaService;

	private MockMvc mvc;

	private ObjectMapper objectMapper;

	@Before
	public void before() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		this.objectMapper = new ObjectMapperConfig().objectMapper();
	}

	@Test
	public void verificaRetorno200FindAll() throws Exception {
		String retorno = Resources.toString(this.getClass().getResource("/mock/campanhas.json"), Charsets.UTF_8);
		List<Campanha> campanhas = Arrays.asList(this.objectMapper.readValue(retorno, Campanha[].class));
		Mockito.doReturn(campanhas).when(this.campanhaService).findAll(Matchers.any());
		String content = this.mvc
				.perform(get("/campanhas"))
				.andExpect(status()
						.isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		Assert.assertEquals(retorno, content);
		Assert.assertEquals(campanhas.size(), 3);
	}

	@Test
	public void verificaRetorno404FindAll() throws Exception {
		String retorno = Resources.toString(this.getClass().getResource("/mock/campanhasVazias.json"), Charsets.UTF_8);
		List<Campanha> campanhas = Arrays.asList(this.objectMapper.readValue(retorno, Campanha[].class));
		Mockito.doReturn(campanhas).when(this.campanhaService).findAll(Matchers.any());

		this.mvc.perform(get("/campanhas")).andExpect(status().isNotFound());

	}

	@Test
	public void verificaRetorno200FindById() throws Exception {
		String retorno = Resources.toString(this.getClass().getResource("/mock/campanha.json"), Charsets.UTF_8);
		Campanha campanhaMock = this.objectMapper.readValue(retorno, Campanha.class);
		Mockito.doReturn(campanhaMock).when(this.campanhaService).findById(Matchers.any());

		String content = this
				.mvc
				.perform(get("/campanhas/1"))
				.andExpect(status()
						.isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		Assert.assertEquals(retorno, content);

	}

	@Test
	public void verificaRetorno404FindById() throws Exception {
		Campanha campanhaMock = null;
		Mockito.doReturn(campanhaMock).when(this.campanhaService).findById(Matchers.any());

		this.mvc.perform(get("/campanhas/1")).andExpect(status().isNotFound());

	}

	@Test
	public void verificaRetorno200Save() throws Exception {
		String body = Resources.toString(this.getClass().getResource("/mock/campanhaPost.json"), Charsets.UTF_8);
		Campanha campanhaMock = this.objectMapper.readValue(body, Campanha.class);
		Mockito.doReturn(campanhaMock).when(this.campanhaService).save(Matchers.any());

		this.mvc.perform(post("/campanhas")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(body))
				.andExpect(status()
						.isCreated());

	}

	@Test
	public void verificaRetorno200Update() throws Exception {
		String body = Resources.toString(this.getClass().getResource("/mock/campanha.json"), Charsets.UTF_8);
		Campanha campanhaMock = this.objectMapper.readValue(body, Campanha.class);
		Mockito.doReturn(campanhaMock).when(this.campanhaService).edit(Matchers.any());

		this.mvc.perform(put("/campanhas")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(body))
				.andExpect(status().isAccepted());
	}
	
	@Test
	public void verificaRetorno200Delete() throws Exception {
		Mockito.doNothing().when(this.campanhaService).delete(Matchers.any());
		this.mvc.perform(delete("/campanhas/1111111111")).andExpect(status().isNoContent());
	}
	
	@Test
	public void verificaRetorno200FindAllCampanhasByTimeDoCoracao() throws Exception {
		String retorno = Resources.toString(this.getClass().getResource("/mock/campanhas_por_time.json"), Charsets.UTF_8);
		List<Campanha> campanhas = Arrays.asList(this.objectMapper.readValue(retorno, Campanha[].class));
		Mockito.doReturn(campanhas).when(this.campanhaService).findAllCampanhasByTimeDoCoracao(Matchers.any());

		String content = this.mvc
				.perform(get("/campanhas/time/gremio"))
				.andExpect(status()
						.isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		Assert.assertEquals(retorno, content);
	}
	
	@Test
	public void verificaRetorno404FindAllCampanhasByTimeDoCoracao() throws Exception {
		String retorno = Resources.toString(this.getClass().getResource("/mock/campanhas_por_time_not_found.json"), Charsets.UTF_8);
		List<Campanha> campanhas = Arrays.asList(this.objectMapper.readValue(retorno, Campanha[].class));
		Mockito.doReturn(campanhas).when(this.campanhaService).findAllCampanhasByTimeDoCoracao(Matchers.any());

		this.mvc.perform(get("/campanhas/time/gremio")).andExpect(status().isNotFound());
	}

}

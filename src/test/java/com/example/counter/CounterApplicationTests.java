package com.example.counter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.LinkedHashMap;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@Slf4j
@SpringBootTest
class CounterApplicationTests {
	@Autowired
	protected MockMvc mockMvc;
	protected final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void contextLoads() {
	}

	@Test
	void count() throws Exception {
		LinkedHashMap<Character, Integer> expected = new LinkedHashMap<>()
				{
					{
						put('b', 8);
						put('a', 5);
						put('c', 5);
					}
				};


		this.mockMvc.perform(get("/count/aaaaabbbbbbbbccccc"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(toJson(expected)));
	}
	@Test
	void countEmpty() throws Exception {
		this.mockMvc.perform(get("/count/   "))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	@Test
	void countWithSpaces() throws Exception {
		LinkedHashMap<Character, Integer> expected = new LinkedHashMap<>()
		{
			{
				put('b', 8);
				put('a', 5);
				put('c', 5);
				put(' ', 2);
			}
		};


		this.mockMvc.perform(get("/count/aaaaa bbbbbbb bccccc"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(toJson(expected)));
	}


	protected String toJson(Object object) {
		String json = "{}";
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.info("Error convert object to json");
		}
		return json;
	}

}


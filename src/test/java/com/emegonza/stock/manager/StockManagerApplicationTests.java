package com.emegonza.stock.manager;

import com.emegonza.stock.manager.repository.IProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class StockManagerApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IProductRepository repository;


	private void clearDB() {
        repository.deleteAll();
	}

    @BeforeEach
    public void setUp() throws Exception {
        clearDB();

        List<String> rows = Files.lines(Paths.get(ClassLoader.getSystemResource("testData.txt").toURI())).toList();

        for (String row : rows) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Double> map = mapper.readValue(row, Map.class);

            mockMvc.perform(post("/api/stock/product")
                    .contentType("application/json")
                    .content(row))
                    .andDo(print())
                    .andExpect(jsonPath("$.*", hasSize(8)))
                    .andExpect(jsonPath("$.status", is("AVAILABLE")))
                    .andExpect(jsonPath("$.sellingPrice", is(map.get("sellingPrice"))))
                    .andExpect(jsonPath("$.lastModifiedDate", is(notNullValue())))
                    .andExpect(jsonPath("$.id", greaterThan(0)))
                    .andExpect(status().isCreated());
        }
    }

    @Test
    public void saveProduct_OK_Test() throws Exception {
        String product = "{\n" +
                "\t\"name\": \"item_x\",\n" +
                "\t\"enteredByUser\": \"user_x\",\n" +
                "\t\"buyingPrice\": 50.0,\n" +
                "\t\"sellingPrice\": 55.0\n" +
                "}";

        mockMvc.perform(post("/api/stock/product")
                        .contentType("application/json")
                        .content(product))
                .andDo(print())
                .andExpect(jsonPath("$.*", hasSize(8)))
                .andExpect(jsonPath("$.status", is("AVAILABLE")))
                .andExpect(jsonPath("$.sellingPrice", is(55.0)))
                .andExpect(jsonPath("$.lastModifiedDate", is(notNullValue())))
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(status().isCreated());
    }

    @Test
    public void saveProductWithTheSameId_Error_Test() throws Exception {
        String product = "{\n" +
                "\t\"id\":" + 1 + ",\n" +
                "\t\"name\": \"item_x\",\n" +
                "\t\"enteredByUser\": \"user_x\",\n" +
                "\t\"buyingPrice\": 50.0,\n" +
                "\t\"sellingPrice\": 55.0\n" +
                "}";

        mockMvc.perform(post("/api/stock/product")
                        .contentType("application/json")
                        .content(product))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}

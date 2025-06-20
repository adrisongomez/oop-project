package test.oopecommerce.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = com.oopecommerce.OopEcommerceApplication.class)
@AutoConfigureMockMvc
@Import(TestConfig.class)
public class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void fullCrudFlow() throws Exception {
        String newProduct = "{\"name\":\"Phone\",\"description\":\"desc\"}";
        MvcResult createRes = mockMvc.perform(MockMvcRequestBuilders.post("/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newProduct))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        JsonNode body = mapper.readTree(createRes.getResponse().getContentAsString());
        String id = body.get("id").asText();

        mockMvc.perform(MockMvcRequestBuilders.get("/products/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Phone"));

        String updateJson = "{\"name\":\"Phone2\",\"description\":\"d2\",\"status\":\"ACTIVE\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/products/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ACTIVE"));

        String patchJson = "{\"description\":\"d3\"}";
        mockMvc.perform(MockMvcRequestBuilders.patch("/products/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patchJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("d3"));

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .param("pageSize", "10")
                .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").exists());

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void createProductInvalid() throws Exception {
        String bad = "{\"name\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bad))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

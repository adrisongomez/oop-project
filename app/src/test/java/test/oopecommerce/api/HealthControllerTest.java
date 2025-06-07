package test.oopecommerce.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkHealthReturnsOk() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/checkhealth"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
        assertEquals("{\"status\":\"ok\"}", response);
    }
}

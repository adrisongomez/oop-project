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
import com.oopecommerce.repositories.IUserRepository;
import test.oopecommerce.__fixtures__.InMemoryUserRepository;

@SpringBootTest(classes = com.oopecommerce.OopEcommerceApplication.class)
@AutoConfigureMockMvc
@Import(TestConfig.class)
public class UsersControllerTest {
    
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void fullCrudFlow() throws Exception {
        String newUserJson = "{\"email\":\"a@ex.com\",\"password\":\"secret\",\"name\":\"Alice\"}";
        MvcResult createRes = mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        JsonNode createBody = mapper.readTree(createRes.getResponse().getContentAsString());
        assertFalse(createBody.has("hashedPassword"));
        String id = createBody.get("id").asText();

        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("a@ex.com"));

        String updateJson = "{\"email\":\"b@ex.com\",\"hashedPassword\":\"h2\",\"name\":\"Bob\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("b@ex.com"));

        String patchJson = "{\"name\":\"Bobby\"}";
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patchJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bobby"));

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .param("pageSize", "10")
                .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").exists());

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void createUserInvalidEmail() throws Exception {
        String bad = "{\"email\":\"bad\",\"password\":\"secret\",\"name\":\"Bad\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bad))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateUserInvalidEmail() throws Exception {
        String newUserJson = "{\"email\":\"c@ex.com\",\"password\":\"p\",\"name\":\"C\"}";
        MvcResult createRes = mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        JsonNode body = mapper.readTree(createRes.getResponse().getContentAsString());
        String id = body.get("id").asText();

        String updateJson = "{\"email\":\"bad\",\"hashedPassword\":\"h\",\"name\":\"C2\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void patchUserInvalidEmail() throws Exception {
        String newUserJson = "{\"email\":\"d@ex.com\",\"password\":\"p\",\"name\":\"D\"}";
        MvcResult createRes = mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newUserJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        JsonNode body = mapper.readTree(createRes.getResponse().getContentAsString());
        String id = body.get("id").asText();

        String patchJson = "{\"email\":\"bad\"}";
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(patchJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUsingInMemoryRepository() {
        // Verificar que el repositorio inyectado es una instancia de InMemoryUserRepository
        assertTrue(userRepository instanceof InMemoryUserRepository);
        
        // Verificar que los usuarios de prueba precargados están disponibles
        assertTrue(userRepository.findByEmail("john@example.com").isPresent());
        assertTrue(userRepository.findByEmail("jane@example.com").isPresent());
    }
}

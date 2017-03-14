package integrationTests;


import br.edu.ufcg.computacao.si1.ADExtremeApplication;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ADExtremeApplication.class)
@WebAppConfiguration
public class IntegrationTest {
    private final String USER_API = "/api/users/";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mapping;

    private User user;
    private List<User> bookmarkList = new ArrayList<>();

    @Autowired
    private UserRepository repository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mapping = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null", this.mapping);
    }
/*
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(context).build();

        this.repository.deleteAllInBatch();

        this.user = new User("Odravison", "Ojunior4@fake.com", "hao123", "user");
    }

    @Test
    public void invalidUser() throws Exception {
        mockMvc.perform(post(USER_API)
                .content(this.json(new User()))
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validUser() throws Exception {
        mockMvc.perform(post(USER_API)
                .content(this.json(user))
                .contentType(contentType))
                .andExpect(status().isCreated());
    }

    @Test
    public void conflictUser() throws Exception {
        mockMvc.perform(post(USER_API)
                .content(this.json(user))
                .contentType(contentType))
                .andExpect(status().isCreated());

        mockMvc.perform(post(USER_API)
                .content(this.json(user))
                .contentType(contentType))
                .andExpect(status().isConflict());
    }

*/
    private String json(Object object) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mapping.write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}
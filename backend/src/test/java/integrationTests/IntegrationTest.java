package integrationTests;


import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

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

import br.edu.ufcg.computacao.si1.ADExtremeApplication;
import br.edu.ufcg.computacao.si1.enums.UserRole;
import br.edu.ufcg.computacao.si1.models.User;
import br.edu.ufcg.computacao.si1.repositories.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ADExtremeApplication.class)
@WebAppConfiguration
public class IntegrationTest {
    private final String USER_API = "/api/users/";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter<User> mapping;

    private User user;

    @Autowired
    private UserRepository repository;

    @Autowired
    private WebApplicationContext context;

    @SuppressWarnings("unchecked")
	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mapping = (HttpMessageConverter<User>) Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null", this.mapping);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(context).build();

        this.repository.deleteAllInBatch();

        this.user = new User("Odravison", "Junior", "Ojunior4@fake.com", "hao123", UserRole.LEGAL_PERSON);
    }

    @Test
    public void invalidUser() throws Exception {
        mockMvc.perform(post(USER_API)
                .content(this.json(new User()))
                .contentType(contentType))
                .andExpect(status().isInternalServerError());
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


    private String json(User object) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mapping.write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}
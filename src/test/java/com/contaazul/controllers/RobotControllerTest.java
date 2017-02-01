package com.contaazul.controllers;

import com.contaazul.MarsrobotApplication;
import com.contaazul.dao.RobotRepository;
import com.contaazul.models.Orientation;
import com.contaazul.models.Robot;
import com.contaazul.utils.Tuple;
import org.junit.Before;
import org.junit.BeforeClass;
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
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = MarsrobotApplication.class)
@WebAppConfiguration
public class RobotControllerTest {

    private MockMvc mockMvc;

    private Robot robotS;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));



    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RobotRepository robotRepository;

    /**
     * Sets converters to spring convert all object to Json;
     * @param converters
     */
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("JSON must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.robotRepository.deleteAllInBatch();

        Tuple<Integer, Integer> tupleS = new Tuple<>(0,0);
        Orientation orientationS = new Orientation(tupleS, "N");
        this.robotS = this.robotRepository.save(new Robot(orientationS));
    }

    @Test
    public void robotInInitialPosition() throws Exception {
        mockMvc.perform(get("/rest/mars/whereami")
                .content(this.json(this.robotS))
                .contentType(contentType))
                .andExpect(status().isOk());

    }

    @Test
    public void movingRobotForward() throws Exception {
        mockMvc.perform(post("/rest/mars/MM")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPosition.y", is(2)));

    }

    @Test
    public void moveAndSpinningRobotToLeft() throws Exception {
        mockMvc.perform(post("/rest/mars/MML")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPosition.y", is(2)))
                .andExpect(jsonPath("$.currentPosition.x", is(0)))
                .andExpect(jsonPath("$.currentOrientation", is("W")));

    }

    @Test
    public void moveAndSpinningRobotToRight() throws Exception {
        mockMvc.perform(post("/rest/mars/MMRMMRMM")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPosition.y", is(0)))
                .andExpect(jsonPath("$.currentPosition.x", is(2)))
                .andExpect(jsonPath("$.currentOrientation", is("S")));

    }

    @Test
    public void badRequestMovement() throws Exception {
        mockMvc.perform(post("/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM")
                .contentType(contentType))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void badRequest() throws Exception {
        mockMvc.perform(post("/rest/mars/AAA")
                .contentType(contentType))
                .andExpect(status().isBadRequest());

    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }









}

package com.pa.revmgmt.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pa.revmgmt.domain.Client;
import com.pa.revmgmt.domain.ClientType;
import com.pa.revmgmt.repo.ClientRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * In this unit test, we would want to post the request body to the url
 * /clients/. In the response, we check for
 * HttpStatus of Created and that the location header
 * contains the url of the created resource.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = ClientController.class, secure = false)
public class ClientControllerTest {

    static Client newClient;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @BeforeClass
    public static void setUp(){
        newClient = new Client( "Franky Edgards LLC", "9728147008", "Lucas John", ClientType.Business);
        newClient.setId(1);
    }


    @Test
    public void createNewClient() throws Exception {
        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(newClient);

        ObjectMapper mapper = new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/clients")
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newClient))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/clients/1", response.getHeader(HttpHeaders.LOCATION));
    }


    @Test
    public void getClient() throws Exception {

        Mockito.when(clientRepository.findById(Mockito.anyInt())).thenReturn(
                Optional.ofNullable(newClient));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/clients/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(newClient);
        assertEquals(expected, result.getResponse().getContentAsString());


        requestBuilder = MockMvcRequestBuilders
                .get("/clients")
                .param("type", ClientType.Business.toString())
                .accept(MediaType.APPLICATION_JSON);
        result = mockMvc.perform(requestBuilder).andReturn();
    }



}

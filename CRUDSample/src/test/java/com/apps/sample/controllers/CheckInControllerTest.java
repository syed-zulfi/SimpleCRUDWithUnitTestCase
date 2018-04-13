package com.apps.sample.controllers;

import com.apps.sample.model.Guest;
import com.apps.sample.services.inface.GuestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(CheckInController.class)
public class CheckInControllerTest {
    @MockBean
    GuestService guestService;

    @Autowired
    MockMvc mMvc;

    @Test
    public void checkInTest(){
        String expected ="Welcome Syed";
        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/checkin")
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .contentType(MediaType.TEXT_PLAIN)
                .param("name","Syed");
                try {
           MvcResult result =  mMvc.perform(reqBuilder).andReturn();
            Assert.assertEquals(expected,result.getResponse().getContentAsString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void onBoardGuestTest(){
        Guest mckGuest = new Guest();
        mckGuest.setName("AAA");
        mckGuest.setPhone("33333");
        try {

            String mckReqBody = new ObjectMapper().writeValueAsString(mckGuest);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/guests")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mckReqBody);
            mckGuest.setId(222L);

            Mockito.when(guestService.createGuest(mckGuest)).thenReturn(true);
            MvcResult mvcResult = mMvc.perform(requestBuilder).andReturn();
            MockHttpServletResponse response = mvcResult.getResponse();
            Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatus());

            Mockito.verify(guestService,Mockito.times(1)).createGuest(Mockito.any(Guest.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getAllGuestsTest(){
        List<Guest> allGuest = new ArrayList<Guest>();
        Guest guest = new Guest();
        guest.setPhone("9494");
        guest.setName("Helloooo");

        allGuest.add(guest);
        Mockito.when(guestService.getAllGuests()).thenReturn(allGuest);
        RequestBuilder request = MockMvcRequestBuilders.get("/api/guests").accept(MediaType.APPLICATION_JSON);
        try {
            MvcResult result = mMvc.perform(request).andReturn();
            String expected = "[{\"id\":null,\"name\":\"Helloooo\",\"phone\":\"9494\"}]";
            JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
            Mockito.verify(guestService, Mockito.times(1)).getAllGuests();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void deleteGuestTest(){
        Mockito.when(guestService.deelteGuest(Mockito.anyLong())).thenReturn(true);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.delete("/api/guests/2772");
        try {
           MvcResult result= mMvc.perform(requestBuilder).andReturn();
           MockHttpServletResponse response = result.getResponse();
           Assert.assertEquals(HttpStatus.NO_CONTENT.value(),response.getStatus());
           Mockito.verify(guestService, Mockito.times(1)).deelteGuest(Mockito.anyLong());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateGuestTest(){
        Guest mckGuest = new Guest();
        mckGuest.setId(19191L);
        mckGuest.setPhone("332323");
        mckGuest.setName("CCCC");
        Mockito.when(guestService.update(mckGuest)).thenReturn(true);

        try {
            String input = new ObjectMapper().writeValueAsString(mckGuest);
            RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/guests")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input);
            MvcResult result = mMvc.perform(requestBuilder).andReturn();
            MockHttpServletResponse response = result.getResponse();
            Assert.assertEquals(HttpStatus.OK.value(),response.getStatus());
            JSONAssert.assertEquals(input,response.getContentAsString(),false);
            Mockito.verify(guestService,Mockito.times(1)).update(mckGuest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

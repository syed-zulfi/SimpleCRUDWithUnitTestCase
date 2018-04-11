package com.apps.sample;

import com.apps.sample.model.Guest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = CrudSampleApplication.class)
public class CrudSampleApplicationIntegTest {
    @LocalServerPort
    private int port;
    TestRestTemplate tRestTmplt = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @Test
    public void testCheckin(){
        HttpEntity<String> htEnt = new HttpEntity<String>(null,headers);
        String URL=createURI("/api/checkin?name=AAA");
        ResponseEntity<String> resEnt = tRestTmplt.exchange(URL,HttpMethod.GET,htEnt,String.class);
        String expectedRes = "Welcome AAA";
        Assert.assertTrue(expectedRes.equals(resEnt.getBody()));
    }
    @Test
    public void testCreateGuest(){
        Guest guest = new Guest();
        guest.setName("AAAA");
        guest.setPhone("44433434");
        HttpEntity<Guest> entity = new HttpEntity<Guest>(guest,headers);
        String URL = createURI("/api/createGuest");
        ResponseEntity rsEntity = tRestTmplt.exchange(URL,HttpMethod.POST,entity,String.class);
        Assert.assertEquals(HttpStatus.CREATED.value(),rsEntity.getStatusCodeValue());
    }

    @Test
    public void testGetGuestList(){
        HttpEntity entity = new HttpEntity(null,headers);
        String URL = createURI("/api/guestList");
        ResponseEntity<List> rsEntity = tRestTmplt.exchange(URL,HttpMethod.GET, entity,List.class);
        List<Guest> guests = rsEntity.getBody();
        Assert.assertEquals(guests.size(),2);
    }
    @Test
    public void testDeleteGuest(){
        HttpEntity entity = new HttpEntity(null,headers);
        String URL = createURI("/api/removeGuest/10001");
        ResponseEntity resEntity = tRestTmplt.exchange(URL,HttpMethod.DELETE, entity,String.class);
        Assert.assertEquals(HttpStatus.NO_CONTENT.value(),resEntity.getStatusCodeValue());
    }
    @Test
    public void testUpdateGuest(){
        String URL = createURI("/api/update");
        Guest guest = new Guest();
        guest.setId(10002L);
        guest.setName("GGGGG");
        guest.setPhone("8896349472");
        HttpEntity<Guest> entity = new HttpEntity<Guest>(guest,headers);
        ResponseEntity<Guest> rsEntity = tRestTmplt.exchange(URL,HttpMethod.PUT,entity,Guest.class);
        Guest resGuest = rsEntity.getBody();
        Assert.assertEquals(guest.getId(),resGuest.getId());
        Assert.assertTrue(guest.getName().equals(resGuest.getName()));
        Assert.assertTrue(guest.getPhone().equals(resGuest.getPhone()));
    }


    private String createURI(String path){
        return "http://localhost:"+port+path;
    }
}

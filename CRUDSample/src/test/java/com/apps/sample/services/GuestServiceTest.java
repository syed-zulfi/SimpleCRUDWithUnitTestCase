package com.apps.sample.services;

import com.apps.sample.dao.inface.GuestRepo;
import com.apps.sample.model.Guest;
import com.apps.sample.services.inface.GuestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class GuestServiceTest {
    @TestConfiguration
    static class GuestServiceTestConfig{
        @Bean
        public GuestService getGuestService(){
            return new GuestServiceImpl();
        }
    }

    @Autowired
    GuestService guestService;

    @MockBean
    GuestRepo guestRepo;

    @Test
    public void testGetAllGuests(){
        Guest guest = new Guest();
        guest.setName("AAA");
        guest.setPhone("3333");
        guest.setId(8484L);

        List<Guest> guests = new ArrayList<Guest>();
        guests.add(guest);
        Mockito.when(guestRepo.getGuests()).thenReturn(guests);
        List<Guest> retGstList = guestService.getAllGuests();
        Assert.assertArrayEquals(guests.toArray(),retGstList.toArray());
    }
    @Test
    public void testCreateGuest(){
        Guest guest = new Guest();
        guest.setName("AAA");
        guest.setPhone("3333");
        guest.setId(8484L);
        Mockito.doNothing().when(guestRepo).createGuest(guest);
        Assert.assertTrue(guestService.createGuest(guest));
    }
    @Test
    public  void testDeleteGuest(){
        Mockito.doNothing().when(guestRepo).deleteGuest(Mockito.anyLong());
        Assert.assertTrue(guestService.deelteGuest(4949L));
    }

    @Test
    public void testUpdateGuest(){
        Guest guest = new Guest();
        guest.setName("AAA");
        guest.setPhone("3333");
        guest.setId(8484L);
        Mockito.when(guestRepo.updateGuest(guest)).thenReturn(guest);
        Assert.assertTrue(guestService.update(guest));


    }
}

package com.apps.sample.dao;

import com.apps.sample.dao.inface.GuestRepo;
import com.apps.sample.dao.inface.GuestRepository;
import com.apps.sample.model.Guest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GuestRepTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
GuestRepository guestRepo;
    @Test
    public void createAndListGuesTest(){
        Guest guest = new Guest();
        guest.setName("AADDDDEEE");
        guest.setPhone("8383838");
        guestRepo.save(guest);
        List<Guest> guests = new ArrayList<Guest>();
        guestRepo.findAll().forEach(guests::add);
        Assert.assertTrue(guests.size()>2);

    }
    @Test
    public void deleteGuest(){
        long id = 10002;
        guestRepo.deleteById(id);
        List<Guest> guests = new ArrayList<Guest>();

        guestRepo.findAll().forEach(guests::add);
        Assert.assertTrue(guests.size()==1);

    }
    @Test
    public void updateGuest(){
        Guest guest = new Guest();
        guest.setName("AADDDDEEE");
        guest.setPhone("8383838");
        guest.setId(10001L);

        guestRepo.save(guest);
        Guest updtGuest = guestRepo.findById(10001L).get();
        Assert.assertTrue(guest.getName().equals(updtGuest.getName()));
        Assert.assertTrue(guest.getPhone().equals(updtGuest.getPhone()));
        Assert.assertEquals(guest.getId(),updtGuest.getId());

    }
}

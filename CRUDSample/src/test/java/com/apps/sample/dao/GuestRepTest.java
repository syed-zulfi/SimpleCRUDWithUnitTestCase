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
    public void createGuestTest(){
        Guest guest = new Guest();
        guest.setName("AADDDDEEE");
        guest.setPhone("8383838");
        guest = guestRepo.save(guest);
       Assert.assertNotNull(entityManager.find(Guest.class,guest.getId()));

    }
    @Test
    public void deleteGuest(){
        Guest guest =new Guest();
        guest.setName("HHHHHHH");
        guest.setPhone("939393");
       guest = entityManager.persist(guest);
        Assert.assertNotNull(guestRepo.findById(guest.getId()).get());
        guestRepo.deleteById(guest.getId());
        Assert.assertFalse(guestRepo.findById(guest.getId()).isPresent());
     }
     @Test
     public void listAllGuests(){
        Guest guest = new Guest();
        guest.setName("BBBBBBB");
        guest.setPhone("8484848");
        entityManager.persist(guest);
        List<Guest> guestList = new ArrayList<Guest>();
        guestRepo.findAll().forEach(guestList::add);
        Assert.assertTrue(guestList.size()>2);

     }

    @Test
    public void updateGuest(){
        Guest guest = new Guest();
        guest.setName("AADDDDEEE");
        guest.setPhone("8383838");

       Long id = (Long) entityManager.persistAndGetId(guest);

       Guest updtGuest = entityManager.find(Guest.class,id);
       updtGuest.setName("BBBBBB");
       updtGuest.setPhone("34433344");
       guestRepo.save(updtGuest);

       Assert.assertTrue(guestRepo.findById(id).get().getName().equals(updtGuest.getName()));
    }
}

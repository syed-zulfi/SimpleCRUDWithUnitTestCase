package com.apps.sample.dao;

import com.apps.sample.dao.inface.GuestRepo;
import com.apps.sample.model.Guest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GuestRepoImpl implements GuestRepo {
    @PersistenceContext
    EntityManager emanager;

    @Override
    public List<Guest> getGuests() {
        List<Guest> gList = emanager.createQuery("Select g from Guest g").getResultList();
        return gList;
    }

    @Override
    public void createGuest(Guest guest) {
        emanager.persist(guest);

    }

    @Override
    public void deleteGuest(Long id) {
        Guest guest = emanager.find(Guest.class, id);
        emanager.remove(guest);
    }

    @Override
    public Guest updateGuest(Guest guest) {
        Guest guestUpdate = emanager.find(Guest.class, guest.getId());
        guestUpdate.setName(guest.getName());
        guestUpdate.setPhone(guest.getPhone());
        emanager.flush();
        return guestUpdate;
    }

    @Override
    public Guest findById(Long id) {
        Guest guest = emanager.find(Guest.class,id);
        return guest;
    }
}

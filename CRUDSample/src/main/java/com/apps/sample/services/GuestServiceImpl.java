package com.apps.sample.services;

import com.apps.sample.dao.inface.GuestRepo;
import com.apps.sample.dao.inface.GuestRepository;
import com.apps.sample.model.Guest;
import com.apps.sample.services.inface.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {


    @Autowired
    private GuestRepository guestRepository;


    @Override
    public List<Guest> getAllGuests() {
        List<Guest> allGuests=new ArrayList<Guest>();
        for (Guest g : guestRepository.findAll()) {
            allGuests.add(g);
        }
        return allGuests;
    }

    @Override
    public synchronized boolean createGuest(Guest guest) {
        //guestRepo.createGuest(guest);
        guestRepository.save(guest);
        System.out.println(guest.toString());
        return true;
    }

    @Override
    public boolean deelteGuest(Long id) {
       // guestRepo.deleteGuest(id);
        guestRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean update(Guest guest) {
        //guestRepo.updateGuest(guest);
        guestRepository.save(guest);
        return true;
    }
}

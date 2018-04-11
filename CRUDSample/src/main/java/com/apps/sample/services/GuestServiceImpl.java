package com.apps.sample.services;

import com.apps.sample.dao.inface.GuestRepo;
import com.apps.sample.model.Guest;
import com.apps.sample.services.inface.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {
    @Autowired
    private GuestRepo guestRepo;

    @Override
    public List<Guest> getAllGuests() {
        List<Guest> allGuests = guestRepo.getGuests();
        for (Guest g : allGuests) {
            System.out.print(g.getName());
        }
        return allGuests;
    }

    @Override
    public synchronized boolean createGuest(Guest guest) {
        guestRepo.createGuest(guest);
        return true;
    }

    @Override
    public boolean deelteGuest(Long id) {
        guestRepo.deleteGuest(id);
        return true;
    }

    @Override
    public boolean update(Guest guest) {
        guestRepo.updateGuest(guest);
        return true;
    }
}

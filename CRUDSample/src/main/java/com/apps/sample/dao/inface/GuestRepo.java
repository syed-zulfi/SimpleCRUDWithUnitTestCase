package com.apps.sample.dao.inface;

import com.apps.sample.model.Guest;

import java.util.List;

public interface GuestRepo {
    public List<Guest> getGuests();
    public void createGuest(Guest guest);
    public void deleteGuest(Long id);
    public Guest updateGuest(Guest guest);
    public Guest findById(Long id);
}

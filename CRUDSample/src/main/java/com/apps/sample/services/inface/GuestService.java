package com.apps.sample.services.inface;

import com.apps.sample.model.Guest;

import java.util.List;

public interface GuestService {

    public List<Guest> getAllGuests();

    public boolean createGuest(Guest guest);

    public boolean deelteGuest(Long id);

    public boolean update(Guest guest);
}

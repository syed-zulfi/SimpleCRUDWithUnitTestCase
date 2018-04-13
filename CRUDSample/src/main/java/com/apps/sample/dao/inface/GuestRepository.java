package com.apps.sample.dao.inface;

import com.apps.sample.model.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest,Long> {

}

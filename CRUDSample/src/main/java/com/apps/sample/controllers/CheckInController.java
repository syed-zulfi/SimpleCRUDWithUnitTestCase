package com.apps.sample.controllers;

import com.apps.sample.model.Guest;
import com.apps.sample.services.inface.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CheckInController {

    @Autowired
    private GuestService guestServiceImpl;


    @GetMapping("/checkin")
    public ResponseEntity<String> checkIn(@RequestParam String name) {
        ResponseEntity rEntity = new ResponseEntity("Welcome " + name, HttpStatus.OK);
        return rEntity;
    }

    @GetMapping("/guests")
    public ResponseEntity<List<Guest>> getGuests() {
        List<Guest> guestList = guestServiceImpl.getAllGuests();
        ResponseEntity rEntity = new ResponseEntity(guestList, HttpStatus.OK);
        return rEntity;
    }

    @PostMapping("/guests")
    public ResponseEntity<Void> onBoardGuest(@RequestBody Guest guest, UriComponentsBuilder ucompBuilder) {
        boolean flag = guestServiceImpl.createGuest(guest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucompBuilder.path("/createGuest/").buildAndExpand().toUri());
        ResponseEntity rEntity = new ResponseEntity(headers, HttpStatus.CREATED);
        return rEntity;
    }

    @DeleteMapping("/guests/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable("id") String id, UriComponentsBuilder uComBuilder) {
        HttpHeaders headers = new HttpHeaders();
        URI self = uComBuilder.path("/guests/{id}").buildAndExpand(id).toUri();
        headers.setLocation(self);
        System.out.print(id);
        guestServiceImpl.deelteGuest(Long.parseLong(id));
        ResponseEntity rEntity = new ResponseEntity(headers, HttpStatus.NO_CONTENT);
        return rEntity;
    }

    @PutMapping("/guests")
    public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest) {
        guestServiceImpl.update(guest);
        return new ResponseEntity<Guest>(guest, HttpStatus.OK);
    }

}

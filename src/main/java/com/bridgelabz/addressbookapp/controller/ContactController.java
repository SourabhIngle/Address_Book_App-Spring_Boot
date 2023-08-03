package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.AddressBookAppApplication;
import com.bridgelabz.addressbookapp.DTO.ContactDTO;
import com.bridgelabz.addressbookapp.DTO.ResponseDTO;
import com.bridgelabz.addressbookapp.model.AddressBookApp;
import com.bridgelabz.addressbookapp.repository.ContactRepository;
import com.bridgelabz.addressbookapp.service.ContactServiceInterface;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactServiceInterface contactServiceInterface;


    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> add(@RequestBody ContactDTO contactDTO) {
        String email = contactDTO.email;
        if (contactServiceInterface.uniqueEmailId(email)) {
            ResponseDTO responseDTO = new ResponseDTO("Data add successfully", contactServiceInterface.add(contactDTO));
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }
        ResponseDTO responseDTO = new ResponseDTO("Email Id already exists in Database.Use Unique Email Id", contactDTO);
        return  new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
    }

    // Get All Person data from database.
    @GetMapping("/getall")
    public List<AddressBookApp> getAll() {
        return contactServiceInterface.getAll();
    }


    // Get person data using person unique Id.
    @GetMapping("/getByToken")
    public ResponseDTO getById(@RequestHeader String token) {
        AddressBookApp addressBookApp = contactServiceInterface.getById(token);
        return new ResponseDTO("Contact data found",addressBookApp);
    }


    // Update person data by using id (Edit existing person data ).
    @PutMapping("/updatebyid")
    public ResponseEntity<ResponseDTO> updateById(@RequestHeader String token, @Valid @RequestBody ContactDTO contactDTO) {
        AddressBookApp addressBookApp = contactServiceInterface.updateById(token, contactDTO);
        ResponseDTO responseDTO = new ResponseDTO("Data Update Successfully", addressBookApp);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    // Delete person data from Address Book by using id .
    @DeleteMapping("/deletebyid")
    public ResponseEntity<String> deleteById(@RequestHeader String token) {
        contactServiceInterface.deleteById(token);
        return ResponseEntity.ok("Id Successfully Deleted");
    }
}

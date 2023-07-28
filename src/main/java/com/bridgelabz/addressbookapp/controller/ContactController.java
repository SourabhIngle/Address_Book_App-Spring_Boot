package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.AddressBookAppApplication;
import com.bridgelabz.addressbookapp.DTO.ContactDTO;
import com.bridgelabz.addressbookapp.DTO.ResponseDTO;
import com.bridgelabz.addressbookapp.model.AddressBookApp;
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
    public ResponseEntity<Object> add(@RequestBody ContactDTO contactDTO){
        String email = contactDTO.email;
        if (!contactServiceInterface.uniqueEmailId(email)){
            return ResponseEntity.badRequest().body("Email already exists");
        }
        ResponseDTO responseDTO = new ResponseDTO("dsfa",contactServiceInterface.add(contactDTO));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public List<AddressBookApp> getAll() {
        return contactServiceInterface.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable Integer id) {
        ResponseDTO responseDTO = new ResponseDTO("Retrieve Data Successfully", contactServiceInterface.getById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable Integer id, @Valid @RequestBody ContactDTO contactDTO) {
        AddressBookApp addressBookApp = contactServiceInterface.updateById(id, contactDTO);
        ResponseDTO responseDTO = new ResponseDTO("Data Update Successfully", addressBookApp);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        contactServiceInterface.deleteById(id);
        return ResponseEntity.ok("Id " + id + " has been Successfully Deleted");
    }
}

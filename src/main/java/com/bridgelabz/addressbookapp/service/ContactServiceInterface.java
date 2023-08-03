package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.DTO.ContactDTO;
import com.bridgelabz.addressbookapp.DTO.ResponseDTO;
import com.bridgelabz.addressbookapp.model.AddressBookApp;

import java.util.List;

public interface ContactServiceInterface {
       ResponseDTO add(ContactDTO contactDTO);
       List<AddressBookApp> getAll();
       AddressBookApp getById(String token);
       AddressBookApp updateById(String token, ContactDTO contactDTO);
       void deleteById(String token);

       boolean uniqueEmailId(String email);
}

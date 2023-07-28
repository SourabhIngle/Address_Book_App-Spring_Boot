package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.DTO.ContactDTO;
import com.bridgelabz.addressbookapp.model.AddressBookApp;

import java.util.List;

public interface ContactServiceInterface {
       AddressBookApp add(ContactDTO contactDTO);
       List<AddressBookApp> getAll();
       AddressBookApp getById(Integer id);
       AddressBookApp updateById(Integer id, ContactDTO contactDTO);
       void deleteById(Integer id);

       boolean uniqueEmailId(String email);
}

package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.DTO.ContactDTO;
import com.bridgelabz.addressbookapp.DTO.ResponseDTO;
import com.bridgelabz.addressbookapp.exception.CustomException;
import com.bridgelabz.addressbookapp.model.AddressBookApp;
import com.bridgelabz.addressbookapp.repository.ContactRepository;
import com.bridgelabz.addressbookapp.security_token.JWTToken;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements ContactServiceInterface {


    @Autowired  //ContactRepository is use for storing data into Database;
    private ContactRepository contactRepository;
    @Autowired
    private JWTToken jwtToken;


    @Override
//add => This is using for add data into database table
    public ResponseDTO add(ContactDTO contactDTO) {
        AddressBookApp addressBookApp = new AddressBookApp(contactDTO);
         contactRepository.save(addressBookApp);
         String token = jwtToken.createToken(addressBookApp.getId());
         return new ResponseDTO(token,addressBookApp);
    }

    @Override
//getAdd => This is using for get to fetch data from database.
    public List<AddressBookApp> getAll() {
        return contactRepository.findAll();
    }

    @Override
//getById => This is using for getting data from database using by id .
    public AddressBookApp getById(String token) {
        int id = jwtToken.decodeToken(token);
        return contactRepository.findById(id)
                .orElseThrow(() -> new CustomException("Person id: " + id + " Not present"));
    }

    @Override
//updateById => This is using for update existed data in the database using by id.
    public AddressBookApp updateById(String token, ContactDTO contactDTO) {
        AddressBookApp getId = getById(token);
        if (getId != null)
            getId.updateContact(contactDTO);
        return contactRepository.save(getId);
    }

    @Override
//deleteById => Just deleting a particular data from database by using id.
    public void deleteById(String token) {
        int id = jwtToken.decodeToken(token);
        contactRepository.deleteById(id);
    }

    @Override
    public boolean uniqueEmailId(String email) {
        AddressBookApp duplicateEmail = contactRepository.findByEmail(email);
        return duplicateEmail == null;
    }
}

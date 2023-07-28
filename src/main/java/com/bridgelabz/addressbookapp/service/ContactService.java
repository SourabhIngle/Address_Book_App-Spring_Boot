package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.DTO.ContactDTO;
import com.bridgelabz.addressbookapp.exception.CustomException;
import com.bridgelabz.addressbookapp.model.AddressBookApp;
import com.bridgelabz.addressbookapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements ContactServiceInterface {


    @Autowired  //ContactRepository is use for storing data into Database;
    private ContactRepository contactRepository;


    @Override
//add => This is using for add data into database table
    public AddressBookApp add(ContactDTO contactDTO) {
        AddressBookApp addressBookApp = new AddressBookApp(contactDTO);
        return contactRepository.save(addressBookApp);
    }

    @Override
//getAdd => This is using for get to fetch data from database.
    public List<AddressBookApp> getAll() {
        return contactRepository.findAll();
    }

    @Override
//getById => This is using for getting data from database using by id .
    public AddressBookApp getById(Integer id) {
        return contactRepository.findById(id).orElseThrow(() -> new CustomException("Person id: " + id + " Not present"));
    }

    @Override
//updateById => This is using for update existed data in the database using by id.
    public AddressBookApp updateById(Integer id, ContactDTO contactDTO) {
        AddressBookApp getId = getById(id);
        if (getId != null)
            getId.updateContact(contactDTO);
        return contactRepository.save(getId);
    }

    @Override
//deleteById => Just deleting a particular data from database by using id.
    public void deleteById(Integer id) {
        getById(id);
        contactRepository.deleteById(id);
    }

    public boolean uniqueEmailId(String email) {
//        return contactList.stream().noneMatch(user -> user.getEmail().equals(email));
        AddressBookApp addressBookApp = contactRepository.findByEmail(email);
        return addressBookApp == null;
    }



//    @Override
//    public void uniqueEmailId(String email) {
//        if (!contactList.getEmail().equals(email)) {
//
//        } else {
//            new CustomException("Email already exist");
//
//        }
//    }
}

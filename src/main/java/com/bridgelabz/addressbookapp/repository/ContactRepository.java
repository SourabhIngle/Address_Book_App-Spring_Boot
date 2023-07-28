package com.bridgelabz.addressbookapp.repository;

import com.bridgelabz.addressbookapp.model.AddressBookApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<AddressBookApp,Integer> {
    //Spring Data JPA will automatically generate the query for you based on the method name.
    AddressBookApp findByEmail(String email);
}

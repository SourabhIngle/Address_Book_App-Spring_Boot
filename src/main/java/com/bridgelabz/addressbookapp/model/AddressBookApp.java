package com.bridgelabz.addressbookapp.model;

import com.bridgelabz.addressbookapp.DTO.ContactDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Address_Book_Table")
@Data
@NoArgsConstructor
public class AddressBookApp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Person_Id")
    private String name;
    @Column(name = "Person_PhoneNumber")
    private String phoneNumber;
    @Column(name = "Person_Email")
    private String email;
    @Column(name = "Person_Address")
    private String address;
    @Column(name = "Person_State")
    private String state;
    @Column(name = "Person_City")
    private String city;
    @Column(name = "Person_Pin_Code")
    private String pin;

    public AddressBookApp(ContactDTO contactDTO) {
        this.updateContact(contactDTO);
    }

    public void updateContact(ContactDTO contactDTO) {
        this.name = contactDTO.name;
        this.phoneNumber = contactDTO.phoneNumber;
        this.email = contactDTO.email;
        this.address = contactDTO.address;
        this.state = contactDTO.state;
        this.city = contactDTO.city;
        this.pin = contactDTO.pin;
    }

}

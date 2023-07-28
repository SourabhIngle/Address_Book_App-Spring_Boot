package com.bridgelabz.addressbookapp.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class ContactDTO {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Integer id;
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Invalid name or First letter capital")
    @NotEmpty
    public String name;
    @Pattern(regexp = "^\\+91\\s[6-9][0-9]{9}$", message = "+91 mandatory then 10 digit number starting from 6 or above")
    @NotEmpty
    public String phoneNumber;
    @Pattern(regexp = "^[A-Za-z]{3,}(([+]|[-]|[.])?[a-zA-Z0-9]+)?@[a-zA-Z0-9]{1,}+[.][a-zA-Z0-9]{3}([.]|[,])?([a-zA-Z]?)*$",message = "Follow the email id rule.")
    @NotEmpty
    public String email;
    @NotEmpty(message = "Full fill the address")
    public String address;
    @NotBlank(message = "Fill up state")
    public String state;
    @NotEmpty(message = "Write your city name")
    public String city;
    @Pattern(regexp = "^[4][0-9]{2}([\\s])?[0-9]{3}$", message = "Start from 4 and only 6 number including 4")
    @NotEmpty
    public String pin;

}

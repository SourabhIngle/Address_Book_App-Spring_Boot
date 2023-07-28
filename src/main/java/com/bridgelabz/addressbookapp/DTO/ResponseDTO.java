package com.bridgelabz.addressbookapp.DTO;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class ResponseDTO {
    public String message;
    public Object data;
}

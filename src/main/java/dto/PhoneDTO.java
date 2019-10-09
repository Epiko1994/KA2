/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Phone;

/**
 *
 * @author Ulrik
 */
public class PhoneDTO {
    
    private String number;
    private String description;

    public PhoneDTO() {
    }

    public PhoneDTO(String number, String description) {
        this.number = number;
        this.description = description;
    }

    PhoneDTO(Phone phone) {
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }
    
}

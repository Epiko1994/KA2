/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ulrik
 */
public class PersonDTO {
    
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private List<PhoneDTO> phones = new ArrayList();
    private List<HobbyDTO> hobbies = new ArrayList();

    public PersonDTO() {
    }

    public PersonDTO(Person p) {
        this.id = p.getId();
        this.email = p.getEmail();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
//        this.address = p.getAddress().getStreet();
        this.city = p.getAddress().getCityInfo().getZip() + " " + p.getAddress().getCityInfo().getCityName();
        
        for (Hobby hobby : p.getHobbies()){ 
            this.hobbies.add(new HobbyDTO(hobby));
        }
        
        for (Phone phone : p.getPhones()){ 
            this.phones.add(new PhoneDTO(phone));
        }
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public List<HobbyDTO> getHobbies() {
        return hobbies;
    }
        
}

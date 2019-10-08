/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Objects;

/**
 *
 * @author Ulrik
 */
public class CityInfoDTO {
    
    private Long id;
    private int zipcode;
    private String city;

    public CityInfoDTO() {
    }

    public CityInfoDTO(int zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }

    public CityInfoDTO(Long id, int zipcode, String city) {
        this.id = id;
        this.zipcode = zipcode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + this.zipcode;
        hash = 67 * hash + Objects.hashCode(this.city);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CityInfoDTO other = (CityInfoDTO) obj;
        if (this.zipcode != other.zipcode) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}

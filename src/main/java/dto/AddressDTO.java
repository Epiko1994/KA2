/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;

import java.util.Objects;

/**
 *
 * @author Ulrik
 */
public class AddressDTO {
    
    private Long id;
    private String street;
    private String additionalInfo;
    private int cityinfo;

    public AddressDTO() {
    }

    public AddressDTO(String street, int cityinfo) {
        this.street = street;
        this.cityinfo = cityinfo;
    }
    /*
    public AddressDTO(Address a) {
        this.street = a.getStreet();
        this.additionalInfo = a.getAdditionalInfo();
        this.cityinfo = a.getCityinfo().getZipcode() + " " + a.getCityinfo().getCity();
    }
    */
    public AddressDTO(Address a) {
        this.street = a.getStreet();
        this.additionalInfo = a.getAdditionalInfo();
        this.cityinfo = a.getCityinfo().getZipcode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(int cityinfo) {
        this.cityinfo = cityinfo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.street);
        hash = 53 * hash + Objects.hashCode(this.additionalInfo);
        hash = 53 * hash + Objects.hashCode(this.cityinfo);
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
        final AddressDTO other = (AddressDTO) obj;
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.additionalInfo, other.additionalInfo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cityinfo, other.cityinfo)) {
            return false;
        }
        return true;
    }
    
    
    
}

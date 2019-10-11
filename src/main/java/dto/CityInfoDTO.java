/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ulrik
 */
public class CityInfoDTO {
    
    private int zip;
    private String cityName;

    public CityInfoDTO() {
    }

    public CityInfoDTO(int zip, String cityName) {
        this.zip = zip;
        this.cityName = cityName;
    }

    public int getZip() {
        return zip;
    }

    public String getCityName() {
        return cityName;
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Ulrik
 */
@Entity
@NamedQuery(name = "CityInfo.deleteAllRows", query = "DELETE from CityInfo")
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int zip;
    private String cityName;
    @OneToMany(mappedBy = "cityInfo", fetch=FetchType.LAZY,
        cascade = CascadeType.ALL,  
        orphanRemoval = true
    )
    private List<Address> address = new ArrayList();;

    public CityInfo() {
    }

    public CityInfo(int zip) {
        this.zip = zip;
    }

    public CityInfo(String cityName) {
        this.cityName = cityName;
    }
    
    public CityInfo(int zip,String cityName) {
        this.zip = zip;
        this.cityName = cityName;
    }


    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    
    
}

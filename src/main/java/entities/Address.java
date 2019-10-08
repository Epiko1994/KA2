package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Person")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String additionalInfo;
    @OneToMany(mappedBy = "address")
    private List<Person> persons;
    @ManyToOne
    private CityInfo cityinfo;

    public Address() {
    }

    public Address(String street, String additionalInfo, CityInfo cityinfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityinfo = cityinfo;
    }

    public Address(String street, String additionalInfo, List<Person> persons, CityInfo cityinfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.persons = persons;
        this.cityinfo = cityinfo;
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public CityInfo getCityinfo() {
        return cityinfo;
    }

    public void setCityinfo(CityInfo cityinfo) {
        this.cityinfo = cityinfo;
    }
    
    

}

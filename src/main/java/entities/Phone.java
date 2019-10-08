package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Phone.deleteAllRows", query = "DELETE from Phone")
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String number;
    private String descritpion;
    @ManyToOne
    private Person person;

    public Phone() {

    }

    public Phone(String number, String descritpion, Person person) {
        this.number = number;
        this.descritpion = descritpion;
        this.person = person;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    

}

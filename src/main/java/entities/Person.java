package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String email;
    private String firstName;
    private String lastName;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Address address;
    @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.DETACH)
    @JoinTable(
    name="PERSON_HOBBY",
    joinColumns=@JoinColumn(name="persons_ID", referencedColumnName="ID"),
    inverseJoinColumns=@JoinColumn(name="hobbies_ID", referencedColumnName="ID"))
    private List<Hobby> hobbies = new ArrayList();
    @OneToMany(mappedBy = "person",fetch=FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Phone> phones = new ArrayList();;
    
    public Person() {
    }

    public Person(String email, String firstName, String lastName, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
    
    public void addHobby(Hobby hobby) {
        this.hobbies.add(hobby);
        hobby.addPerson(this);
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        this.phones.add(phone);
        phone.setPerson(this);
    }    
    
       
}

package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@NamedQuery(name = "Hobby.deleteAllRows", query = "DELETE from Person")
public class Hobby implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    private String name;
    private String description;
    
    @ManyToMany(mappedBy = "hobbies",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(
    name="PERSON_HOBBY",
    joinColumns=@JoinColumn(name="persons_ID", referencedColumnName="ID"),
    inverseJoinColumns=@JoinColumn(name="hobbies_ID", referencedColumnName="ID"))
   
    private List<Person> persons;

    public Hobby() {
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    


}

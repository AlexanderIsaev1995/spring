package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;


@Entity
public class Address extends EntityBase {
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private Long house;
    @ManyToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private Set<Person> person;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getHouse() {
        return house;
    }

    public void setHouse(Long house) {
        this.house = house;
    }

    public Set<Person> getPerson() {
        return person;
    }

    public void setPerson(Set<Person> person) {
        this.person = person;
    }
}

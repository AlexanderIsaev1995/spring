package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Address extends EntityBase {
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private Long house;

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
}

package app.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue(value = "admin")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends Person {
}

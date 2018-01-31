package se.munhunger.oven.model.persistance;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User
{
    @Id
    public String email;
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<GroceryList> groceryLists;

    public User(String email) {
        this.email = email;
    }
    public User(){}
}

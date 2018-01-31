package se.munhunger.oven.model.persistance;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marcus Münger
 */
@Entity
@Table(name = "grocery_list")
public class GroceryList implements Serializable {
    @Column(length = 126)
    public String title;
    @Id
    @Column(length = 64)
    public String id;
    @Transient
    public int size;
}
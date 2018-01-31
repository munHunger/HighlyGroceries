package se.munhunger.oven.model.persistance;

import javax.persistence.*;
import java.util.List;

/**
 * @author Marcus Münger
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(length = 128)
    public String category;
    @OneToMany
    public List<Item> items;
}
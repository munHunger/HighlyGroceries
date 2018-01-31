package se.munhunger.oven.model.persistance;

import javax.persistence.*;

/**
 * @author Marcus Münger
 */
@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(length = 128)
    public String title;
    public boolean purchased;
    @ManyToOne
    public Category category;
}
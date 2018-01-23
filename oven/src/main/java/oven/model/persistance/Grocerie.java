package oven.model.persistance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(description = "Groceries")
@Entity
@Table(name = "food")
public class Grocerie implements Serializable {

    @ApiModelProperty(value = "Random string")
    @Id
    public String name;

    @ApiModelProperty(value = "The name of the grocerie")
    @Column
    public String food_group;


    public Grocerie(){}

    public Grocerie(String name, String food_group)
    {
        this.name = name;
        this.food_group = food_group;
    }

}

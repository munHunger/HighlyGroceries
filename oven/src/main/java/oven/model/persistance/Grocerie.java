package oven.model.persistance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@ApiModel(description = "Groceries")
@Entity
@Table(name = "grocerie")
public class Grocerie implements Serializable {

    @ApiModelProperty(value = "Random string")
    @Id
    public String id;

    @ApiModelProperty(value = "The name of the grocerie")
    @Column
    public String title;

    public Grocerie(){}

    public Grocerie(String title)
    {
        this.id = UUID.randomUUID().toString();
        this.title = title;
    }

}

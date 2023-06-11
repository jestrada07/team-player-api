package com.hw.hwapi.models;
//Objects JSON - TABLE
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//Pojo - plain old java object
//entity - Entity annotation is widely used in various natural language processing tasks, such as information extraction, named entity recognition, and text mining, to enable more advanced analysis and understanding of textual data.
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //how it's going to select it in sql
   // JoinColumn is a Java annotation used in object-relational mapping (ORM) frameworks, such as Hibernate, to define a relationship between two database tables.
    @JoinColumn(name = "team_id", nullable = false) //each player belongs to a specific team , indicates the relationship between Player and Team
    @OnDelete(action = OnDeleteAction.CASCADE) // impact other entities - anything that's dependent on primary key
    @JsonIgnore //instructing the program or framework to ignore that field during the serialization or deserialization process. This means that the field's value won't be included in the JSON output or won't be assigned a value when converting JSON to an object.
    private Team team;


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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

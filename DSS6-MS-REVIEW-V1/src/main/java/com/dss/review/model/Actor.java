package com.dss.review.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity(name="ACTOR")
@Getter
@Setter
public class Actor {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String age;

    @JsonIgnore
    @ManyToMany(mappedBy = "actorList")
    private Set<Movie> movieList;

    public Actor(String id, String firstName, String lastName, String gender,
                 String age, Set<Movie> movieList){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.movieList = movieList;
    }
}

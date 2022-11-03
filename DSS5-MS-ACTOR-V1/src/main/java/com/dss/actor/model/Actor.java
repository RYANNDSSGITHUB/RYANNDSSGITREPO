package com.dss.actor.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
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

    @ManyToMany
    @JoinTable(name="MOVIE_ACTOR",
            joinColumns = @JoinColumn(name="actor_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="movie_id",referencedColumnName = "id"))
    private Set<Movie> movieList;
}

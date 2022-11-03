package com.dss.movie.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name="ACTOR")
@Setter
@Getter
public class Actor {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String age;

    @ManyToMany(mappedBy = "actorList")
    private List<Movie> moviesList;
}

package com.dss.movie.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name="MOVIE")
@Getter
@Setter
public class Movie {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String description;
    private String productionCost;
    private Date yearReleased;
    private String imageDirectory;

    @ManyToMany
    @JoinTable(name="MOVIE_ACTOR",
            joinColumns = @JoinColumn(name="movie_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="actor_id",referencedColumnName = "id"))
    private List<Actor> actorList;
}

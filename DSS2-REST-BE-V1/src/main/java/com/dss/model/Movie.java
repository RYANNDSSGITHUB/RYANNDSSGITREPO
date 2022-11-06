package com.dss.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name="MOVIE")
@Getter
@Setter
public class Movie extends AbstractBaseModel{

    private String description;
    private String productionCost;
    private String yearReleased;
    private String imageDirectory;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    List<Review> reviewList;

    @ManyToMany
    @JoinTable(name="MOVIE_ACTOR",
            joinColumns = @JoinColumn(name="movie_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="actor_id",referencedColumnName = "id"))
    private Set<Actor> actorList;
}

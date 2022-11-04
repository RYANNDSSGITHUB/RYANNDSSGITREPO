package com.dss.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity(name="MOVIE")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String description;
    private String productionCost;
    private String yearReleased;
    private String imageDirectory;

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Review> reviewList;

    @ManyToMany
    @JoinTable(name = "MOVIE_ACTOR",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
    private Set<Actor> actorList;

    public Movie(String id, String description, String productionCost, String yearReleased,
                 String imageDirectory, List<Review> reviewList, Set<Actor> actorList){
        this.id = id;
        this.description = description;
        this.productionCost = productionCost;
        this.yearReleased = yearReleased;
        this.imageDirectory = imageDirectory;
        this.reviewList = reviewList;
        this.actorList = actorList;
    }
}

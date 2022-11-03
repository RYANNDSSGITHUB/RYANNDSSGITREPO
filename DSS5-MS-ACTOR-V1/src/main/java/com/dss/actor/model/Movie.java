package com.dss.actor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private String yearReleased;
    private String imageDirectory;

    @JsonIgnore
    @ManyToMany(mappedBy = "movieList")
    private List<Actor> actorList;
}

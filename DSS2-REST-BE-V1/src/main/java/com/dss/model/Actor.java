package com.dss.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name="ACTOR")
@Getter
@Setter
public class Actor extends AbstractBaseModel {

    private String firstName;
    private String lastName;
    private String gender;
    private String age;

    @JsonIgnore
    @ManyToMany(mappedBy = "actorList")
    private List<Movie> movieList;
}

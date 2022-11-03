package com.dss.movie.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name="ACTOR")
@Setter
@Getter
public class Actor {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String age;
}

package com.dss.movie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="REVIEW")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String message;
    private LocalDate postedDt;
    private String rating;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="movie_id", referencedColumnName="id")
    private Movie movie;
}

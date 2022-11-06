package com.dss.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity(name="REVIEW")
@Getter
@Setter
public class Review extends AbstractBaseModel {

    private String message;
    private LocalDate postedDt;
    private String rating;

    @ManyToOne
    @JoinColumn(name="movie_id", referencedColumnName="id")
    private Movie movie;
}

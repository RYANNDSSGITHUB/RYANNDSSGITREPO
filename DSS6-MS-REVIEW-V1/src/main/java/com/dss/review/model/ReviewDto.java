package com.dss.review.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class ReviewDto {
    private String id;
    private String message;
    private LocalDate postedDt;
    private String rating;
    private Movie movie;
}

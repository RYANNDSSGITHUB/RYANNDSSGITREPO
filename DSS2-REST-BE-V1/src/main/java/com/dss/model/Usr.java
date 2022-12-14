package com.dss.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="USR")
@Getter
@Setter
public class Usr extends AbstractBaseModel {

    private String alias;
    private String contactNo;
    private String email;
    private String password;

    public Usr(String email, String password) {
        this.alias = "";
        this.contactNo = "";
        this.email = email;
        this.password = password;
    }
}

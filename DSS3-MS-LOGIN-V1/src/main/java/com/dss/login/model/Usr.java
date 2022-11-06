package com.dss.login.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Entity(name="USR")
@Getter
@Setter
public class Usr {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
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

package com.org.THC.model;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Users {

    @Id
    private String id;
    private String username;
    private String password;
}

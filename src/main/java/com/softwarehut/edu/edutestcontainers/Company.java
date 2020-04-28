package com.softwarehut.edu.edutestcontainers;

import java.io.Serializable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Company implements Serializable {

    private Long id;
    private String name;
    private String description;
}
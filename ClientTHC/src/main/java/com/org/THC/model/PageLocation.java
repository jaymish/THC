package com.org.THC.model;

import lombok.Data;

import java.util.List;

@Data
public class PageLocation {
    private List<Location> locationList;
    private Integer pages;
}

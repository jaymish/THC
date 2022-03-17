package com.org.THC.model;

import lombok.Data;

import java.util.List;

@Data
public class PageMenu {
    private List<Menu> menuList;
    private Integer pages;
}

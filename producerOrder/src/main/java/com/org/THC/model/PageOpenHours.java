package com.org.THC.model;

import lombok.Data;

import java.util.List;

@Data
public class PageOpenHours {
    private List<OpenHours> openHoursList;
    private Integer pages;
}

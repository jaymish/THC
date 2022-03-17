package com.org.THC.model;

import lombok.Data;

import java.util.List;
@Data
public class PageAPIExecuation {
    private List<APIExecutionTime> apiExecutionTimeList;
    private Integer pages;
}

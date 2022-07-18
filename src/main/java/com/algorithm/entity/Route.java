package com.algorithm.entity;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Route {

    private Integer distance;
    private City from;
    private City to;

}

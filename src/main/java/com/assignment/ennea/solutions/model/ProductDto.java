package com.assignment.ennea.solutions.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * ProductDto Class
 */
@Data
public class ProductDto {

    private UUID id;
    private String code;
    private String name;
    private String batch;
    private int stock;
    private int deal;
    private int free;
    private float mrp;
    private float rate;
    private Date expirationDate;
    private String company;
    private String supplier;

}

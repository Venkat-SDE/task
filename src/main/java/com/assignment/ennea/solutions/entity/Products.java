package com.assignment.ennea.solutions.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Entity Class
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products {

    /**
     * Simple JavaBean domain object representing as products
     */

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "product_code")
    private String code;

    @Column(name = "product_name")
    private String name;

    @Column(name = "batch")
    private String batch;

    @Column(name = "stock")
    private int stock;

    @Column(name = "deal")
    private int deal;

    @Column(name = "free")
    private int free;

    @Column(name = "mrp")
    private float mrp;

    @Column(name = "rate")
    private float rate;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "company_name")
    private String company;

    @Column(name = "supplier_name")
    private String supplier;

}

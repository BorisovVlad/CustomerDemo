package net.proselyte.customerdemo.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
public class Customer extends BaseEntity{

    @Column(name = "FIRST_NAME")
    private String FIRST_NAME;

    @Column(name = "LAST_NAME")
    private String LAST_NAME;

    @Column(name = "ADDRESS")
    private String ADDRESS;

    @Column(name = "BUDGET")
    private BigDecimal BUDGET;
}

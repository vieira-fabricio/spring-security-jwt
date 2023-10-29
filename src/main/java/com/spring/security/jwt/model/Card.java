package com.spring.security.jwt.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "tb_cartao")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_card", unique = true)
    private String number;
    @Column(name = "limit_card", precision = 13, scale = 2)
    private BigDecimal limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}



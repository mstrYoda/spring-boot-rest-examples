package com.example.domain.user;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {
    private String addressText;
    private String addressCode;
    private String addressCity;
}

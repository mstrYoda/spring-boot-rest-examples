package com.example.application.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
    private int age;
}

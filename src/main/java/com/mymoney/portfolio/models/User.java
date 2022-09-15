package com.mymoney.portfolio.models;

import java.util.UUID;

public class User {
    String id;
    String name;

    public User(String name){
        id = UUID.randomUUID().toString();
        name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

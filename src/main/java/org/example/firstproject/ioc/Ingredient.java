package org.example.firstproject.ioc;

import lombok.Getter;

@Getter
public abstract class Ingredient {
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }

}

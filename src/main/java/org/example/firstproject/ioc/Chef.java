package org.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {

    private IngredientFactory ingredientFactory;
    // 셰프가 식재료 공장과 형업하기 위한 DI
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        //재료 준비
         Ingredient ingredient = ingredientFactory.get(menu);
        //요리 반환
        return ingredient.getName() + "으로 만든 "+menu;
    }
}

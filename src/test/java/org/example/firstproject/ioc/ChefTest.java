package org.example.firstproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest class ChefTest {
    @Autowired
    IngredientFactory ingredientFactory;
    @Autowired
    Chef chef;
    @Test
    void 돈가스요리하기(){
        //준비
    //    IngredientFactory ingredientFactory =new IngredientFactory();
    //    Chef chef = new Chef(ingredientFactory);
        String menu = "돈가스";
        //수정
        String food = chef.cook(menu);

        //예상
        String expected = "한돈 등심으로 만든 돈가스";
        //검증
        assertEquals(expected,food);
        System.out.println(food);
    }
    @Test
    void 스테이크요리하기(){
        //준비
   //     IngredientFactory ingredientFactory =new IngredientFactory();
   //     Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";
        //수정
        String food = chef.cook(menu);

        //예상
        String expected = "한우 꽃등심으로 만든 스테이크";
        //검증
        assertEquals(expected,food);
        System.out.println(food);
    }
    @Test
    void 크리스피치킨요리하기(){
        //준비
 //       IngredientFactory ingredientFactory =new IngredientFactory();
 //       Chef chef = new Chef(ingredientFactory);
        String menu = "크리스피 치킨";
        //수정
        String food = chef.cook(menu);

        //예상
        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";
        //검증
        assertEquals(expected,food);
        System.out.println(food);
    }
}
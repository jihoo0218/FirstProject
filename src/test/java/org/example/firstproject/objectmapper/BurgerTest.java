package org.example.firstproject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BurgerTest {

    @Test
    public void 자바객체를JSON으로변환() throws JsonProcessingException {
        //준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패티","순쇠고기 패티","토마토","스파이시 어니언 소스");
        Burger burger = new Burger("맥도날드 슈비버거",5000, ingredients);
        //수행
        String json = objectMapper.writeValueAsString(burger);
        //예상
        String expected ="{\"name\":\"맥도날드 슈비버거\",\"price\":5000,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";
        //검증
        assertEquals(expected,json);
       JsonNode jsonNode =objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void JSON을자바객체로변환() throws JsonProcessingException {
        //준비
        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "{\"name\":\"맥도날드 슈비버거\",\"price\":5000,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";
        /*
        {
        "name" : "맥도널드 슈비버거",
        "price" : "5500",
        "ingredients" : ["통새우 패티","순쇠고기 패티","토마토","스파이시 어니언 소스"]
        }*/
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name","맥도날드 슈비버거");
        objectNode.put("price","5500");

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("통새우 패티");
        arrayNode.add("순쇠고기 패티");
        arrayNode.add("토마토");
        arrayNode.add("스파이시 어니언 소스");
        objectNode.set("ingredients",arrayNode);
        String json = objectNode.toString();
        //수행
        Burger burger = objectMapper.readValue(json, Burger.class);
        //예상
        List<String> ingredients = Arrays. asList("통새우 패티","순쇠고기 패티","토마토","스파이시 어니언 소스");
        Burger expected = new Burger("맥도날드 슈비버거",5500, ingredients);
        //검증
        assertEquals(expected.toString(),burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println("json = " + jsonNode.toPrettyString());
        System.out.println("burger = " + burger.toString());
    }


}
package com.beech.blunettestavernmenu;

/**
 * Created by Wayne Beech on 12/9/2015.
 */
public class FoodItem {

    private int id;
    private String name;
    private int prepTime; //(minutes) for demonstration purposes will be using seconds
    private float price;


    //constructors
    public FoodItem() {
        id = 0;
        name = "PlaceHolder";
        prepTime = 0;
        price = 0f;
    }

    public FoodItem(int id, String name, int prepTime, float price) {
        this.id = id;
        this.name = name;
        this.prepTime = prepTime;
        this.price = price;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

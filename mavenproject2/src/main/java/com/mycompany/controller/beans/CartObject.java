/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller.beans;

/**
 *
 * @author Linda
 */
public class CartObject {

    String day;
    String foodName;
    int price;
    int quan;
    int priority;

    public CartObject(String day, String foodName, int price, int quan, int priority) {
        this.day = day;
        this.foodName = foodName;
        this.price = price;
        this.quan = quan;
        this.priority = priority;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuan() {
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}

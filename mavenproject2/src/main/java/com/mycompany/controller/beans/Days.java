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
public enum Days {  //enumeráció
    Hétfő("Hétfő", 1), Kedd("Kedd", 2), Szerda("Szerda", 3), Csütörtök("Csütörtök", 4), Péntek("Péntek", 5);

    String name;
    int priority;

    private Days(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

}

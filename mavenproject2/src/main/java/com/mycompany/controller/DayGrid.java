/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.beans.Days;
import com.mycompany.controller.beans.Food;
import com.mycompany.mavenproject2.modell.dbutil;
import com.mycompany.mavenproject2.view.MyUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.DefaultItemSorter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Grid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linda
 */
public class DayGrid {

    String nameOfDay;
    Grid grid;
    
    public DayGrid(String nameOfDay) {
        this.nameOfDay = nameOfDay;

    }

    public Grid getGrid() {
        
        dbutil dbu = new dbutil();
        ArrayList rs = null;
        try {
            rs = dbu.selectAllByDay(nameOfDay);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        BeanItemContainer<Food> container = new BeanItemContainer<>(Food.class);  //grid feltöltö
        container.addAll(rs);
        container.setItemSorter(new DefaultItemSorter(new Comparator() {   //anonim innner class
            @Override
            public int compare(Object o1, Object o2) {
                Days a = (Days) o1;
                Days b = (Days) o2;
                return a.getPriority() - b.getPriority();
            }
        }));
        
        grid = new Grid(nameOfDay, container);
        
        grid.addItemClickListener(new ItemClickEvent.ItemClickListener() {  //anonim innner class
            @Override
            public void itemClick(ItemClickEvent event) {
                System.err.println(event);
            }
        });
        
        
        grid.setStyleName("monday");    //stílus neve
        grid.setEditorEnabled(true);    //szerkesztés engedélyezés
        grid.getColumn("name").setEditable(false);  //szerk. tiltása
        grid.getColumn("price").setEditable(false);
        grid.getColumn("menu").setEditable(false);
        Grid.FooterRow mondayFooter = grid.appendFooterRow();       //lábléc hozzáadás
        mondayFooter.setStyleName("mondayFooter");
        grid.setFooterVisible(true);        //látható
        mondayFooter.join("name", "price", "menu", "quantity");     //cellaegyesítés
        grid.setSelectionMode(Grid.SelectionMode.MULTI);        //több cella kijelölése
        grid.setWidth(100, Sizeable.Unit.PERCENTAGE);   //méretezés
        grid.setHeight("600px");
        
        return grid;
    }
}

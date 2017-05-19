/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.FooterRow;

/**
 *
 * @author Linda
 */
public class cartGrid {

    Grid grid;

    public cartGrid() {

    }

    public Grid getCartGrid() {

        grid = new Grid("Kosár");  //név
        grid.setSelectionMode(Grid.SelectionMode.MULTI);   //több cella kijelölése
        grid.setStyleName("cart");      //stílus
        grid.addColumn("Nap", String.class);    //oszloptípusok
        grid.addColumn("Étel", String.class);
        grid.addColumn("Ár", Integer.class);
        grid.addColumn("Mennyiség", Integer.class);
        grid.getColumn("Nap").setSortable(false);   //rendezés tiltás

        grid.setWidth(100, Unit.PERCENTAGE);  //méretezés
        grid.setHeight("645px");
        return grid;

    }

    public FooterRow getFooter() {
        
        FooterRow footer = grid.appendFooterRow();  //lábléc hozzáadás
        footer.setStyleName("footer");
        grid.setFooterVisible(true);
        footer.getCell("Mennyiség").setText("");
        footer.getCell("Ár").setText("Összeg:");
        
        return footer;
    }

}

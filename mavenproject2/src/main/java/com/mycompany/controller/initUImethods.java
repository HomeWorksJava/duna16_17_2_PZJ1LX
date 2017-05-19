/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.controller.beans.CartObject;
import com.mycompany.controller.beans.Days;
import static com.mycompany.mavenproject2.view.MyUI.cart;
import static com.mycompany.mavenproject2.view.MyUI.cartItems;
import static com.mycompany.mavenproject2.view.MyUI.sum;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author Linda
 */
public class initUImethods {

    public void createButt(String day, Grid.MultiSelectionModel selectionOfGivenDay, Grid gridOfGivenDay, Grid.FooterRow footer) {
        Button buttonThisDay = new Button("Add to cart", new Button.ClickListener() {       //anonim inner class

            @Override
            public void buttonClick(Button.ClickEvent e) {
                for (Object itemId : selectionOfGivenDay.getSelectedRows()) {
                    Property nameOfGiven = gridOfGivenDay.getContainerDataSource().getContainerProperty(itemId, "name");
                    Property priceOfGiven = gridOfGivenDay.getContainerDataSource().getContainerProperty(itemId, "price");
                    Property quantityOfGiven = gridOfGivenDay.getContainerDataSource().getContainerProperty(itemId, "quantity");
                    int price = Integer.parseInt(priceOfGiven.getValue().toString());
                    int quan = Integer.parseInt(quantityOfGiven.getValue().toString());
                    cartItems.add(new CartObject(day, nameOfGiven.getValue().toString(), price, quan, Days.valueOf(day).getPriority()));
                }
                
                cart.getContainerDataSource().removeAllItems();     //sorbarendezés miatt, nem a legjobb de elmegy
                sum = 0;
                gridOfGivenDay.getSelectionModel().reset();
                cartItems.sort(Comparator.comparing(CartObject::getPriority));

                for (CartObject cartItem : cartItems) {
                    cart.addRow(cartItem.getDay(), cartItem.getFoodName(), cartItem.getPrice(), cartItem.getQuan());
                    sum += cartItem.getPrice() * cartItem.getQuan();
                }
                footer.getCell("Mennyiség").setText(Integer.toString(sum));
            }
        });
        
        gridOfGivenDay.getFooterRow(0).getCell("name").setComponent(buttonThisDay);
        buttonThisDay.setStyleName(ValoTheme.BUTTON_FRIENDLY);
    }

    public void createDeleteSelectedButton(Grid.MultiSelectionModel selectionOfCart, Grid.FooterRow footer) {
        Button deleteSelected = new Button("Delete Row(s)", (Button.ClickEvent e) -> {
            for (Object itemId : selectionOfCart.getSelectedRows()) {
                Property quantityOfGiven = cart.getContainerDataSource().getContainerProperty(itemId, "Mennyiség");
                int price = Integer.parseInt(cart.getContainerDataSource().getContainerProperty(itemId, "Ár").getValue().toString()) * Integer.parseInt(quantityOfGiven.getValue().toString());
                sum -= price;
                String foodName = cart.getContainerDataSource().getContainerProperty(itemId, "Étel").getValue().toString();
                String foodDay = cart.getContainerDataSource().getContainerProperty(itemId, "Nap").getValue().toString();

                Iterator<CartObject> it = cartItems.iterator();
                while (it.hasNext()) {
                    CartObject co = it.next();
                    if ((co.getFoodName().equals(foodName)) && (co.getDay().equals(foodDay))) {
                        it.remove();
                    }
                }
                cart.getContainerDataSource().removeItem(itemId);

            }
            cart.getSelectionModel().reset();
            footer.getCell("Mennyiség").setText(Integer.toString(sum));

        });
        deleteSelected.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        footer.getCell("Nap").setComponent(deleteSelected);
    }

    public void createDeleteAllButton(Grid.FooterRow footer) {
        Button bt = new Button("Clear cart", e -> {
            cart.getContainerDataSource().removeAllItems();
            sum = 0;
            footer.getCell("Mennyiség").setText(Integer.toString(sum));
            cartItems.clear();
        });
        bt.setStyleName(ValoTheme.BUTTON_DANGER);
        footer.getCell("Étel").setComponent(bt);
    }
}

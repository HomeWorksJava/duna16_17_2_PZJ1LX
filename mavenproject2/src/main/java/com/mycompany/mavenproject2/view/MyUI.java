package com.mycompany.mavenproject2.view;

import com.mycompany.controller.DayGrid;
import com.mycompany.controller.beans.CartObject;
import com.mycompany.controller.beans.Days;
import com.mycompany.controller.cartGrid;
import com.mycompany.controller.initUImethods;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.FooterRow;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("com.mycompany.mavenproject2.MyAppWidgetset")
@StyleSheet({"http://fonts.googleapis.com/css?family=Cabin+Sketch"})
public class MyUI extends UI {

    public static int sum = 0;
    public static Grid cart;
    public static List<CartObject> cartItems = new ArrayList<>();

    initUImethods controll = new initUImethods();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();     //fekvő tájolás

        TabSheet tabs = new TabSheet();     //fülek
        tabs.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabs.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();      //két panel
        horizontalSplit.setSizeFull();
        horizontalSplit.setSplitPosition(500, Unit.PIXELS);

        //napokhoz tartozó gridek
        DayGrid gridMonday = new DayGrid(Days.Hétfő.name());
        Grid gHetfo = gridMonday.getGrid();

        DayGrid gridTuesday = new DayGrid(Days.Kedd.name());
        Grid gKedd = gridTuesday.getGrid();

        DayGrid gridWednesday = new DayGrid(Days.Szerda.name());
        Grid gSzerda = gridWednesday.getGrid();

        DayGrid gridThursday = new DayGrid(Days.Csütörtök.name());
        Grid gCsutortok = gridThursday.getGrid();

        DayGrid gridFriday = new DayGrid(Days.Péntek.name());
        Grid gPentek = gridFriday.getGrid();

        cartGrid cG = new cartGrid();
        cart = cG.getCartGrid();
        FooterRow footer = cG.getFooter();
        
        //kiválasztott kosár elemek
        MultiSelectionModel selectionOfCart
                = (MultiSelectionModel) cart.getSelectionModel();

        //kiválasztások és gomb hozzáadások
        Grid.MultiSelectionModel selectionOfMonday
                = (Grid.MultiSelectionModel) gHetfo.getSelectionModel();
        controll.createButt(Days.Hétfő.name(), selectionOfMonday, gHetfo, footer);

        Grid.MultiSelectionModel selectionOfTuesday
                = (Grid.MultiSelectionModel) gKedd.getSelectionModel();
        controll.createButt(Days.Kedd.name(), selectionOfTuesday, gKedd, footer);

        Grid.MultiSelectionModel selectionOfWednesday
                = (Grid.MultiSelectionModel) gSzerda.getSelectionModel();
        controll.createButt(Days.Szerda.name(), selectionOfWednesday, gSzerda, footer);

        Grid.MultiSelectionModel selectionOfThursday
                = (Grid.MultiSelectionModel) gCsutortok.getSelectionModel();
        controll.createButt(Days.Csütörtök.name(), selectionOfThursday, gCsutortok, footer);

        Grid.MultiSelectionModel selectionOfFriday
                = (Grid.MultiSelectionModel) gPentek.getSelectionModel();
        controll.createButt(Days.Péntek.name(), selectionOfFriday, gPentek, footer);

        controll.createDeleteAllButton(footer);
        controll.createDeleteSelectedButton(selectionOfCart, footer);

        tabs.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabs.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        //Fülecskék hozzáadása
        tabs.addTab(gHetfo, Days.Hétfő.name());
        tabs.addTab(gKedd, Days.Kedd.name());
        tabs.addTab(gSzerda, Days.Szerda.name());
        tabs.addTab(gCsutortok, Days.Csütörtök.name());
        tabs.addTab(gPentek, Days.Péntek.name());

        //bal és jobb oldali tábla hozzáadása
        horizontalSplit.addComponents(tabs, cart);

        //tájolás beállítása
        layout.addComponents(horizontalSplit);
        layout.setMargin(true);
        layout.setSpacing(true);

        //tartalom végleges beállítása
        setContent(layout);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

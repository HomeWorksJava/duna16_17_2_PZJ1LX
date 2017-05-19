/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2.modell;

import com.mycompany.controller.beans.Food;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Linda
 */
public class dbutil {

    private static final String dbURL = "jdbc:derby://localhost:1527/etlap;create=true;user=Linda;password=12345";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;   //sql utasitas helye

    public dbutil() {
    }

    public ArrayList selectAllByDay(String day) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        conn = DriverManager.getConnection(dbURL);
        stmt = conn.createStatement();
        String sql = ("SELECT * FROM " + day);
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Food> Rows = new ArrayList<>();

        while (rs.next()) {
            Food food = new Food();
            food.setName(rs.getString("étel"));
            food.setPrice(rs.getInt("ár"));
            food.setQuantity(rs.getInt("mennyiség"));
            food.setMenu(rs.getString("menü"));

            Rows.add(food);

        }
        rs.close();
        return Rows;

    }

}

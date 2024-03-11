/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.data.dao.impl;

import com.aldrin.pos.model.UserAccount;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ALRIN B.C.
 */
@Setter
@Getter
@ToString
public class UserSales extends DBConnection {

    private UserAccount userAccount;
    private String currentDate;
    private Float sales;
    private Integer transaction;

    public ArrayList<UserSales> selectUserSales() {
        ArrayList<UserSales> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    USER_ACCOUNT.FIRSTNAME, \n"
                    + "    USER_ACCOUNT.SURNAME, \n"
                    + "    CURRENT_DATE , \n"
                    + "    SUM(STOCK_IN_ENTRY.PRICE_SELLING) AS SALES,"
                    + "    USER_ACCOUNT.ID,\n"
                    + "    COUNT(DISTINCT INVOICE_ENTRY.INVOICE_ID) AS TRANSACTIONS \n"
                    + "\n"
                    + "FROM \n"
                    + "    INVOICE \n"
                    + "INNER JOIN \n"
                    + "    USER_ACCOUNT \n"
                    + "ON \n"
                    + "    (INVOICE.USER_ID = USER_ACCOUNT.ID) \n"
                    + "INNER JOIN \n"
                    + "    INVOICE_ENTRY \n"
                    + "ON \n"
                    + "    (INVOICE.ID = INVOICE_ENTRY.INVOICE_ID) \n"
                    + "INNER JOIN \n"
                    + "    STOCK_IN_ENTRY \n"
                    + "ON \n"
                    + "    (INVOICE_ENTRY.STOCKIN_ENTRY_ID = STOCK_IN_ENTRY.ID) \n"
                    + "INNER JOIN \n"
                    + "    PRODUCT \n"
                    + "ON \n"
                    + "    (STOCK_IN_ENTRY.PRODUCT_ID = PRODUCT.ID) \n"
                    + "INNER JOIN \n"
                    + "    UNIT \n"
                    + "ON \n"
                    + "    (STOCK_IN_ENTRY.UNIT_ID = UNIT.ID) GROUP BY USER_ACCOUNT.FIRSTNAME,  USER_ACCOUNT.SURNAME,USER_ACCOUNT.ID   ORDER BY   USER_ACCOUNT.SURNAME ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                UserSales us = new UserSales();
                UserAccount userAccount = new UserAccount();
                userAccount.setId(rs.getLong("ID"));
                userAccount.setFirstname(rs.getString("FIRSTNAME"));
                userAccount.setSurname(rs.getString("SURNAME"));
                Float sales = rs.getFloat("SALES");
                Integer trans = rs.getInt("TRANSACTIONS");

                us.setUserAccount(userAccount);
                us.setSales(sales);
                us.setTransaction(trans);
                list.add(us);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<UserSales> selectUserSalesWithParam(Long start, Long end) {
        ArrayList<UserSales> list = new ArrayList<>();
        try {
            String query = "SELECT     USER_ACCOUNT.FIRSTNAME, \n"
                    + "                        USER_ACCOUNT.SURNAME, \n"
                    + "                        CURRENT_DATE , \n"
                    + "                        SUM(STOCK_IN_ENTRY.PRICE_SELLING) AS SALES,\n"
                    + "                        USER_ACCOUNT.ID,\n"
                    + "                        COUNT(DISTINCT INVOICE_ENTRY.INVOICE_ID) AS TRANSACTIONS \n"
                    + "                    \n"
                    + "                    FROM \n"
                    + "                        INVOICE \n"
                    + "                    INNER JOIN \n"
                    + "                        USER_ACCOUNT \n"
                    + "                    ON \n"
                    + "                        (INVOICE.USER_ID = USER_ACCOUNT.ID) \n"
                    + "                    INNER JOIN \n"
                    + "                        INVOICE_ENTRY \n"
                    + "                    ON \n"
                    + "                        (INVOICE.ID = INVOICE_ENTRY.INVOICE_ID) \n"
                    + "                    INNER JOIN \n"
                    + "                        STOCK_IN_ENTRY \n"
                    + "                    ON \n"
                    + "                        (INVOICE_ENTRY.STOCKIN_ENTRY_ID = STOCK_IN_ENTRY.ID) \n"
                    + "                    INNER JOIN \n"
                    + "                        PRODUCT \n"
                    + "                    ON \n"
                    + "                       (STOCK_IN_ENTRY.PRODUCT_ID = PRODUCT.ID) \n"
                    + "                    INNER JOIN \n"
                    + "                        UNIT \n"
                    + "                    ON   (STOCK_IN_ENTRY.UNIT_ID = UNIT.ID) WHERE INVOICE.ID >=" + start + " AND INVOICE.ID <=" + end + " \n"
                    + "                    GROUP BY USER_ACCOUNT.FIRSTNAME,  USER_ACCOUNT.SURNAME,USER_ACCOUNT.ID   ORDER BY   USER_ACCOUNT.SURNAME ASC";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                UserSales us = new UserSales();
                UserAccount userAccount = new UserAccount();
                userAccount.setId(rs.getLong("ID"));
                userAccount.setFirstname(rs.getString("FIRSTNAME"));
                userAccount.setSurname(rs.getString("SURNAME"));
                Float sales = rs.getFloat("SALES");
                Integer trans = rs.getInt("TRANSACTIONS");

                us.setUserAccount(userAccount);
                us.setSales(sales);
                us.setTransaction(trans);
                list.add(us);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}

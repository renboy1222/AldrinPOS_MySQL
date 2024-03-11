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
public class Profit extends DBConnection {

    private UserAccount userAccount;
    private String currentDate;
    private Float profit;
    private Integer transaction;

    public ArrayList<Profit> selectProfit() {
        ArrayList<Profit> list = new ArrayList<>();
        try {
            String query = " SELECT \n"
                    + "    USER_ACCOUNT.FIRSTNAME, \n"
                    + "    USER_ACCOUNT.SURNAME, \n"
                    + "    SUM(STOCK_IN_ENTRY.PRICE_SELLING-STOCK_IN_ENTRY.PRICE_BUYING) AS PROFIT,\n"
                    + "    USER_ACCOUNT.ID,\n"
                    + "    COUNT(DISTINCT INVOICE_ENTRY.INVOICE_ID) AS TRANSACTIONS\n"
                    + "    \n"
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
                    + "    (STOCK_IN_ENTRY.UNIT_ID = UNIT.ID)   GROUP BY USER_ACCOUNT.FIRSTNAME,  USER_ACCOUNT.SURNAME,USER_ACCOUNT.ID  ORDER BY   USER_ACCOUNT.SURNAME ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Profit us = new Profit();
                UserAccount userAccount = new UserAccount();
                userAccount.setId(rs.getLong("ID"));
                userAccount.setFirstname(rs.getString("FIRSTNAME"));
                userAccount.setSurname(rs.getString("SURNAME"));
                Float profit = rs.getFloat("PROFIT");
                Integer trans = rs.getInt("TRANSACTIONS");

                us.setUserAccount(userAccount);
                us.setProfit(profit);
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

    public ArrayList<Profit> selectProfitWithParam(Long start,Long end) {
        ArrayList<Profit> list = new ArrayList<>();
        try {
            String query = " SELECT \n"
                    + "    USER_ACCOUNT.FIRSTNAME, \n"
                    + "    USER_ACCOUNT.SURNAME, \n"
                    + "    SUM(STOCK_IN_ENTRY.PRICE_SELLING-STOCK_IN_ENTRY.PRICE_BUYING) AS PROFIT,\n"
                    + "    USER_ACCOUNT.ID,\n"
                    + "    COUNT(DISTINCT INVOICE_ENTRY.INVOICE_ID)  AS TRANSACTIONS\n"
                    + "    \n"
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
                    + "    (STOCK_IN_ENTRY.UNIT_ID = UNIT.ID) WHERE INVOICE_ENTRY.INVOICE_ID >=" + start + " AND INVOICE_ENTRY.INVOICE_ID <=" + end + "  GROUP BY USER_ACCOUNT.FIRSTNAME,  USER_ACCOUNT.SURNAME,USER_ACCOUNT.ID  ORDER BY   USER_ACCOUNT.SURNAME ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Profit us = new Profit();
                UserAccount userAccount = new UserAccount();
                userAccount.setId(rs.getLong("ID"));
                userAccount.setFirstname(rs.getString("FIRSTNAME"));
                userAccount.setSurname(rs.getString("SURNAME"));
                Float profit = rs.getFloat("PROFIT");
                Integer trans = rs.getInt("TRANSACTIONS");

                us.setUserAccount(userAccount);
                us.setProfit(profit);
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

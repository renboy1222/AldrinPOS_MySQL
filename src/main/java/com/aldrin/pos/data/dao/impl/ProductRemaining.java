/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.data.dao.impl;

import com.aldrin.pos.model.Category;
import com.aldrin.pos.model.Product;
import com.aldrin.pos.model.StockInEntry;
import com.aldrin.pos.model.Unit;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ALRIN B.C.
 */
@Setter
@Getter
public class ProductRemaining extends DBConnection {
    
    private Category category;
    private Product product;
    private Unit unit;
    private StockInEntry stockInEntry;
    private String qtyRemaining;
    private Float lineTotal;
    
    public ArrayList<ProductRemaining> selectRemainingProduct() {
        ArrayList<ProductRemaining> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "   CATEGORY.CATEGORY, \n"
                    + "   PRODUCT.PRODUCT, \n"
                    + "   UNIT.UNIT,\n"
                    + "   COUNT(*) AS QTY,\n"
                    + "   STOCK_IN_ENTRY.PRICE_BUYING ,\n"
                    + "   STOCK_IN_ENTRY.PRICE_SELLING ,\n"
                    + "   (STOCK_IN_ENTRY.PRICE_SELLING *COUNT(*) ) AS LINETOTAL \n"
                    + "   \n"
                    + "FROM \n"
                    + "    STOCK_IN_ENTRY \n"
                    + "INNER JOIN \n"
                    + "    PRODUCT \n"
                    + "ON \n"
                    + "    (STOCK_IN_ENTRY.PRODUCT_ID = PRODUCT.ID) \n"
                    + "INNER JOIN \n"
                    + "    CATEGORY \n"
                    + "ON \n"
                    + "    (PRODUCT.CATEGORY_ID = CATEGORY.ID) \n"
                    + "INNER JOIN \n"
                    + "    UNIT \n"
                    + "ON \n"
                    + "    (STOCK_IN_ENTRY.UNIT_ID =UNIT.ID)where STOCK_IN_ENTRY.PROCESS_ID =1 GROUP BY  \n"
                    + " CATEGORY.CATEGORY, PRODUCT.PRODUCT, UNIT.UNIT,STOCK_IN_ENTRY.PRICE_SELLING, STOCK_IN_ENTRY.PRICE_BUYING ORDER BY CATEGORY.CATEGORY, PRODUCT.PRODUCT ASC    ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ProductRemaining pr = new ProductRemaining();
                Category category = new Category();
                Product product = new Product();
                Unit unit = new Unit();
                StockInEntry stockInEntry = new StockInEntry();
                category.setCategory(rs.getString("CATEGORY"));
                product.setProduct(rs.getString("PRODUCT"));
                unit.setUnit(rs.getString("UNIT"));
                
                stockInEntry.setPriceBuying(rs.getFloat("PRICE_BUYING"));
                stockInEntry.setPriceSelling(rs.getFloat("PRICE_SELLING"));
                
                
                pr.setCategory(category);
                pr.setProduct(product);
                pr.setUnit(unit);
                pr.setQtyRemaining(rs.getString("QTY"));
                pr.setStockInEntry(stockInEntry);
                pr.setLineTotal(rs.getFloat("LINETOTAL"));
                list.add(pr);
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

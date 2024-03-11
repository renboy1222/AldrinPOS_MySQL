/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.data.dao.impl;

import com.aldrin.pos.data.dao.StockInEntryDAO;
import static com.aldrin.pos.data.dao.impl.DBConnection.closeConnection;
import static com.aldrin.pos.data.dao.impl.DBConnection.getCon;
import com.aldrin.pos.model.Product;
import com.aldrin.pos.model.StockIn;
import com.aldrin.pos.model.StockInEntry;
import com.aldrin.pos.model.Unit;
import com.aldrin.pos.util.ComboBoxList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ALDRIN B. C.
 */
@Setter
@Getter
public class StockInEntryDAOImpl extends DBConnection implements StockInEntryDAO {

    @Override
    public void addStockInEntry(StockInEntry stockInEntry) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO STOCK_IN_ENTRY ("
                    + "    STOCK_IN_ENTRY.ID,"
                    + "    STOCK_IN_ENTRY.STOCK_IN_ID, \n"
                    + "    STOCK_IN_ENTRY.PRODUCT_ID, \n"
                    + "    STOCK_IN_ENTRY.UNIT_ID, \n"
                    + "    STOCK_IN_ENTRY.PRICE_SELLING, \n"
                    + "    STOCK_IN_ENTRY.PRICE_BUYING,"
                    + "    STOCK_IN_ENTRY.PROCESS_ID ) VALUES  (?,?,?,?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setLong(2, stockInEntry.getStockIn().getId());
            ps.setLong(3, stockInEntry.getProduct().getId());
            ps.setLong(4, stockInEntry.getUnit().getId());
            ps.setFloat(5, stockInEntry.getPriceSelling());
            ps.setFloat(6, stockInEntry.getPriceBuying());
            ps.setLong(7, stockInEntry.getProcess().getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<StockInEntry> selectStockInEntry(StockInEntry se) {
        ArrayList<StockInEntry> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    STOCK_IN_ENTRY.STOCK_IN_ID, \n"
                    + "    STOCK_IN_ENTRY.PRODUCT_ID, \n"
                    + "    STOCK_IN_ENTRY.UNIT_ID, \n"
                    + "    STOCK_IN_ENTRY.PRICE_SELLING, \n"
                    + "    STOCK_IN_ENTRY.PRICE_BUYING,\n"
                    + "    UNIT.UNIT, PRODUCT.PRODUCT,\n"
                    + "    COUNT(*) AS COUNT_ROW \n"
                    + "FROM \n"
                    + "    STOCK_IN_ENTRY \n"
                    + "INNER JOIN \n"
                    + "    UNIT \n"
                    + "ON (STOCK_IN_ENTRY.UNIT_ID = UNIT.ID) \n"
                    + "INNER JOIN \n"
                    + "    PRODUCT \n"
                    + "ON (STOCK_IN_ENTRY.PRODUCT_ID = PRODUCT.ID) WHERE STOCK_IN_ENTRY.STOCK_IN_ID= " + se.getStockIn().getId() + "\n "
                    + "    GROUP BY \n"
                    + "        STOCK_IN_ENTRY.UNIT_ID, \n"
                    + "        STOCK_IN_ENTRY.PRODUCT_ID, \n"
                    + "        STOCK_IN_ENTRY.STOCK_IN_ID, \n"
                    + "        STOCK_IN_ENTRY.PRICE_SELLING, \n"
                    + "        STOCK_IN_ENTRY.PRICE_BUYING,UNIT.UNIT,PRODUCT.PRODUCT  ORDER BY STOCK_IN_ENTRY.STOCK_IN_ID DESC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                StockInEntry s = new StockInEntry();
                StockIn sn = new StockIn();
                Product p = new Product();
                Unit u = new Unit();
                sn.setId(rs.getLong("STOCK_IN_ID"));
                p.setId(rs.getLong("PRODUCT_ID"));
                p.setProduct(rs.getString("PRODUCT"));
                u.setId(rs.getLong("UNIT_ID"));
                u.setUnit(rs.getString("UNIT"));

                s.setStockIn(sn);
                s.setProduct(p);
                s.setUnit(u);
                s.setPriceBuying(rs.getFloat("PRICE_BUYING"));
                s.setPriceSelling(rs.getFloat("PRICE_SELLING"));
                s.setQty(rs.getInt("COUNT_ROW"));

                list.add(s);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public StockInEntry findProductByBarCode(StockInEntry stockInEntry) {
        StockInEntry se = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    PRODUCT.PRODUCT, \n"
                    + "    PRODUCT.BARCODE, \n"
                    + "    STOCK_IN_ENTRY.PRICE_BUYING, \n"
                    + "    STOCK_IN_ENTRY.PRICE_SELLING, \n"
                    + "    STOCK_IN_ENTRY.PRODUCT_ID, \n"
                    + "    STOCK_IN_ENTRY.STOCK_IN_ID, \n"
                    + "    STOCK_IN_ENTRY.UNIT_ID, \n"
                    + "    UNIT.UNIT , STOCK_IN_ENTRY.ID  \n"
                    + "FROM \n"
                    + "    STOCK_IN_ENTRY \n"
                    + "INNER JOIN \n"
                    + "    PRODUCT \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        STOCK_IN_ENTRY.PRODUCT_ID = PRODUCT.ID) \n"
                    + "INNER JOIN \n"
                    + "    UNIT \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        STOCK_IN_ENTRY.UNIT_ID = UNIT.ID) WHERE PRODUCT.BARCODE ='" + stockInEntry.getProduct().getBarcode() + "' ORDER BY STOCK_IN_ENTRY.ID DESC ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                se = new StockInEntry();
                Product p = new Product();
                Unit u = new Unit();
                u.setUnit(rs.getString("UNIT"));
                p.setProduct(rs.getString("PRODUCT"));
                p.setId(rs.getLong("PRODUCT_ID"));
                se.setPriceBuying(rs.getFloat("PRICE_SELLING"));
                se.setUnit(u);
                se.setProduct(p);

            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return se;
    }

    @Override
    public StockInEntry findProductById(StockInEntry stockInEntry) {
        StockInEntry se = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    PRODUCT.PRODUCT, \n"
                    + "    PRODUCT.BARCODE, \n"
                    + "    STOCK_IN_ENTRY.PRICE_BUYING, \n"
                    + "    STOCK_IN_ENTRY.PRICE_SELLING, \n"
                    + "    STOCK_IN_ENTRY.PRODUCT_ID, \n"
                    + "    STOCK_IN_ENTRY.STOCK_IN_ID, \n"
                    + "    STOCK_IN_ENTRY.UNIT_ID, \n"
                    + "    UNIT.UNIT , STOCK_IN_ENTRY.ID  \n"
                    + "FROM \n"
                    + "    STOCK_IN_ENTRY \n"
                    + "INNER JOIN \n"
                    + "    PRODUCT \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        STOCK_IN_ENTRY.PRODUCT_ID = PRODUCT.ID) \n"
                    + "INNER JOIN \n"
                    + "    UNIT \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        STOCK_IN_ENTRY.UNIT_ID = UNIT.ID) WHERE STOCK_IN_ENTRY.PRODUCT_ID =" + stockInEntry.getProduct().getId() + " ORDER BY STOCK_IN_ENTRY.ID DESC ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                se = new StockInEntry();
                Product p = new Product();
                Unit u = new Unit();
                u.setUnit(rs.getString("UNIT"));
                p.setProduct(rs.getString("PRODUCT"));
                p.setId(rs.getLong("PRODUCT_ID"));
                se.setPriceBuying(rs.getFloat("PRICE_SELLING"));
                se.setUnit(u);
                se.setProduct(p);

            }
            rs.close();
            statement.close();
//            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return se;
    }

    @Override
    public Long getStockInEntryIDByProcessId(Long id) {
        Long stockInId = 0L;
        try {
            String maxId = "SELECT \n"
                    + "    STOCK_IN_ENTRY.ID\n"
                    + "FROM \n"
                    + "    STOCK_IN_ENTRY WHERE STOCK_IN_ENTRY.PROCESS_ID =1 AND STOCK_IN_ENTRY.PRODUCT_ID =" + id + " ORDER BY STOCK_IN_ENTRY.ID ASC  LIMIT 1 ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(maxId);
            while (rs.next()) {
                stockInId = rs.getLong("ID");
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stockInId;
    }

    public void updateStockInEntryProcess(StockInEntry se) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE  STOCK_IN_ENTRY  SET  PROCESS_ID=2  WHERE ID = ?");
            ps.setLong(1, se.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean validateQtyRunOut(Long productId, Integer quantity) {
        Boolean runOut = false;
        Integer qty = 0;
        try {
            String maxId = "SELECT     COUNT(*) AS ROW_COUNT  FROM STOCK_IN_ENTRY \n"
                    + " WHERE STOCK_IN_ENTRY.PROCESS_ID =1 AND STOCK_IN_ENTRY.PRODUCT_ID =" + productId + " GROUP BY STOCK_IN_ENTRY.PRODUCT_ID  ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(maxId);
            while (rs.next()) {
                qty = rs.getInt("ROW_COUNT");
            }
            rs.close();
            st.close();
            closeConnection();
            if (quantity > qty) {
                runOut = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return runOut;
    }

    @Override
    public Long getMaxId() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(STOCK_IN_ENTRY.ID) AS ID  \n"
                    + "FROM \n"
                    + "    STOCK_IN_ENTRY ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                if (idl == 0) {
                    maxId = 1L;
                } else {
                    maxId = idl + 1;
                }
            }
            rs.close();
            statement.close();
//            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Opss...", JOptionPane.ERROR_MESSAGE);
        }
        return maxId;
    }

    @Override
    public Long getMaxIdForRef() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(STOCK_IN_ENTRY.ID) AS ID  \n"
                    + "FROM \n"
                    + "    STOCK_IN_ENTRY ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                if (idl == 0) {
                    maxId = 1L;
                } else {
                    maxId = idl;
                }
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Opss...", JOptionPane.ERROR_MESSAGE);
        }
        return maxId;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.data.dao.impl;

import com.aldrin.pos.data.dao.InvoiceEntryDAO;
import static com.aldrin.pos.data.dao.impl.DBConnection.closeConnection;
import static com.aldrin.pos.data.dao.impl.DBConnection.getCon;
import com.aldrin.pos.model.InvoiceEntry;
import com.aldrin.pos.model.Product;
import com.aldrin.pos.model.StockIn;
import com.aldrin.pos.model.StockInEntry;
import com.aldrin.pos.model.Unit;
import com.aldrin.pos.util.ComboBoxList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
public class InvoiceEntryDAOImpl extends DBConnection implements InvoiceEntryDAO {

    @Override
    public void addInvoiceEntry(InvoiceEntry invoiceEntry) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO INVOICE_ENTRY ("
                    + "   INVOICE_ENTRY.ID,INVOICE_ENTRY.STOCKIN_ENTRY_ID, \n"
                    + "   INVOICE_ENTRY.INVOICE_ID ) VALUES  (?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setLong(2, invoiceEntry.getStockInEntryId().getId());
            ps.setLong(3, invoiceEntry.getInvoice().getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<InvoiceEntry> selectInvoiceEntry(InvoiceEntry ie) {
        ArrayList<InvoiceEntry> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    INVOICE_ENTRY.INVOICE_ID, \n"
                    + "    PRODUCT.PRODUCT, \n"
                    + "    UNIT.UNIT, \n"
                    + "    COUNT(*) AS QUANTITY, \n"
                    + "    STOCK_IN_ENTRY.PRICE_SELLING, \n"
                    + "    SUM(STOCK_IN_ENTRY.PRICE_SELLING) AS TOTAL \n"
                    + "FROM \n"
                    + "    INVOICE_ENTRY \n"
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
                    + "    (STOCK_IN_ENTRY.UNIT_ID = UNIT.ID)\n"
                    + "     WHERE INVOICE_ENTRY.INVOICE_ID =" + ie.getInvoice().getId() + " GROUP BY PRODUCT.PRODUCT,INVOICE_ENTRY.INVOICE_ID,UNIT.UNIT,STOCK_IN_ENTRY.PRICE_SELLING";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                InvoiceEntry invoice = new InvoiceEntry();
                StockInEntry se = new StockInEntry();
                StockIn sn = new StockIn();
                Product p = new Product();
                Unit u = new Unit();
                p.setProduct(rs.getString("PRODUCT"));
                u.setUnit(rs.getString("UNIT"));
                invoice.setQuantity(rs.getString("QUANTITY"));
                se.setPriceSelling(rs.getFloat("PRICE_SELLING"));
                invoice.setTotal(rs.getFloat("TOTAL"));
                se.setUnit(u);
                se.setProduct(p);

                invoice.setStockInEntryId(se);
                invoice.setStockInEntryId(se);
                list.add(invoice);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public InvoiceEntry findProductByBarCode(InvoiceEntry invoiceEntry) {
        InvoiceEntry se = null;
        try {
//            getDBConn();
//            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
//                    + "    PRODUCT.PRODUCT, \n"
//                    + "    PRODUCT.BARCODE, \n"
//                    + "    STOCK_IN_ENTRY.PRICE_BUYING, \n"
//                    + "    STOCK_IN_ENTRY.PRICE_SELLING, \n"
//                    + "    STOCK_IN_ENTRY.PRODUCT_ID, \n"
//                    + "    STOCK_IN_ENTRY.STOCK_IN_ID, \n"
//                    + "    STOCK_IN_ENTRY.UNIT_ID, \n"
//                    + "    UNIT.UNIT , STOCK_IN_ENTRY.ID  \n"
//                    + "FROM \n"
//                    + "    STOCK_IN_ENTRY \n"
//                    + "INNER JOIN \n"
//                    + "    PRODUCT \n"
//                    + "ON \n"
//                    + "    ( \n"
//                    + "        STOCK_IN_ENTRY.PRODUCT_ID = PRODUCT.ID) \n"
//                    + "INNER JOIN \n"
//                    + "    UNIT \n"
//                    + "ON \n"
//                    + "    ( \n"
//                    + "        STOCK_IN_ENTRY.UNIT_ID = UNIT.ID) WHERE PRODUCT.BARCODE ='" + stockInEntry.getProduct().getBarcode() + "' ORDER BY STOCK_IN_ENTRY.ID DESC ");
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                se = new InvoiceEntry();
//                Product p = new Product();
//                Unit u = new Unit();
//                u.setUnit(rs.getString("UNIT"));
//                p.setProduct(rs.getString("PRODUCT"));
//                p.setId(rs.getLong("PRODUCT_ID"));
////                se.setPriceBuying(rs.getFloat("PRICE_SELLING"));
////                se.setUnit(u);
////                se.setProduct(p);
//
//            }
//            rs.close();
//            statement.close();
//            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return se;
    }

    @Override
    public Long getMaxId() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(INVOICE_ENTRY.ID) AS ID  \n"
                    + "FROM \n"
                    + "    INVOICE_ENTRY ");
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
    public void comboBoxInvoiceStart() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT \n"
                    + "    INVOICE_ENTRY.INVOICE_ID, \n"
                    + "    INVOICE.CREATEDAT \n"
                    + "FROM \n"
                    + "    INVOICE_ENTRY \n"
                    + "INNER JOIN \n"
                    + "    INVOICE \n"
                    + "ON \n"
                    + "    (INVOICE_ENTRY.INVOICE_ID = INVOICE.ID) GROUP BY INVOICE_ENTRY.INVOICE_ID, \n"
                    + "    INVOICE.CREATEDAT ORDER BY INVOICE_ENTRY.INVOICE_ID DESC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("INVOICE_ID");
//                Date namel = rs.getDate("CREATEDAT");
                java.sql.Timestamp timestamp = rs.getTimestamp("CREATEDAT");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss a");
                String formattedTimestamp = dateFormat.format(timestamp);

                this.getList().add(new ComboBoxList(idl, formattedTimestamp));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void comboBoxInvoiceEnd() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT \n"
                    + "    INVOICE_ENTRY.INVOICE_ID, \n"
                    + "    INVOICE.CREATEDAT \n"
                    + "FROM \n"
                    + "    INVOICE_ENTRY \n"
                    + "INNER JOIN \n"
                    + "    INVOICE \n"
                    + "ON \n"
                    + "    (INVOICE_ENTRY.INVOICE_ID = INVOICE.ID) GROUP BY INVOICE_ENTRY.INVOICE_ID, \n"
                    + "    INVOICE.CREATEDAT ORDER BY INVOICE_ENTRY.INVOICE_ID DESC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("INVOICE_ID");
//                String namel = rs.getString("CREATEDAT");
                java.sql.Timestamp timestamp = rs.getTimestamp("CREATEDAT");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss a");
                String formattedTimestamp = dateFormat.format(timestamp);

                this.getList().add(new ComboBoxList(idl, formattedTimestamp));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

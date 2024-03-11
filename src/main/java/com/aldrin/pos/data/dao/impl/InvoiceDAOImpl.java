/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.data.dao.impl;

import com.aldrin.pos.data.dao.InvoiceDAO;
import com.aldrin.pos.data.dao.StockInDAO;
import static com.aldrin.pos.data.dao.impl.DBConnection.closeConnection;
import static com.aldrin.pos.data.dao.impl.DBConnection.getCon;
import com.aldrin.pos.model.Invoice;
import com.aldrin.pos.model.StockIn;
import com.aldrin.pos.model.Transporter;
import com.aldrin.pos.model.UserAccount;
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
public class InvoiceDAOImpl extends DBConnection implements InvoiceDAO {

    @Override
    public void addInvoice(Invoice invoice) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO INVOICE (ID,USER_ID) VALUES  (?,?) ");
            ps.setLong(1, getMaxId());
            ps.setLong(2, invoice.getUserAccount().getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE STOCK_IN SET USER_ID =?, TRANSPORTER_ID =? WHERE STOCK_IN.ID = ?");
            ps.setLong(1, invoice.getUserAccount().getId());
//            ps.setLong(2, invoice.getTransporter().getId());
//            ps.setDate(3, invoice.getCreatedAt());
            ps.setLong(4, invoice.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<Invoice> selectInvoice() {
        ArrayList<Invoice> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    USER_ACCOUNT.FIRSTNAME, \n"
                    + "    USER_ACCOUNT.SURNAME, \n"
                    + "    INVOICE.ID, \n"
                    + "    INVOICE.CREATEDAT, \n"
                    + "    SUM(STOCK_IN_ENTRY.PRICE_SELLING) AS TOTAL  \n"
                    + "FROM \n"
                    + "    INVOICE \n"
                    + "INNER JOIN \n"
                    + "    USER_ACCOUNT \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        INVOICE.USER_ID = USER_ACCOUNT.ID) \n"
                    + "INNER JOIN \n"
                    + "    INVOICE_ENTRY \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        INVOICE.ID = INVOICE_ENTRY.INVOICE_ID) \n"
                    + "INNER JOIN \n"
                    + "    STOCK_IN_ENTRY \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        INVOICE_ENTRY.STOCKIN_ENTRY_ID = STOCK_IN_ENTRY.ID) GROUP BY USER_ACCOUNT.FIRSTNAME,USER_ACCOUNT.SURNAME,INVOICE.ID, INVOICE.CREATEDAT ORDER BY INVOICE.ID DESC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Invoice invoice = new Invoice();
                UserAccount ua = new UserAccount();
                ua.setFirstname(rs.getString("FIRSTNAME"));
                ua.setSurname(rs.getString("SURNAME"));
//                ua.setId(rs.getLong("ID"));
                invoice.setCreatedAt(rs.getDate("CREATEDAT"));
                invoice.setTimeStamp(rs.getTimestamp("CREATEDAT"));
                
               
//                invoice.setTransporter(t);
                invoice.setUserAccount(ua);
                invoice.setId(rs.getLong("ID"));
                invoice.setAmount(rs.getFloat("TOTAL"));
                
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

    @Override
    public Long getMaxId() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(INVOICE.ID) AS ID  \n"
                    + "FROM \n"
                    + "    INVOICE ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                if (idl == 0) {
                    maxId = 1L;
                } else {
                    maxId = idl+1;
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
    public void deleteInvoice(Invoice invoice) {
    }

    @Override
    public void comboBoxInvoice() {
    }

    @Override
    public Long getMaxIdForRef() {
         Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(INVOICE.ID) AS ID  \n"
                    + "FROM \n"
                    + "    INVOICE ");
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.data.dao.impl;

import com.aldrin.pos.data.dao.StockInDAO;
import static com.aldrin.pos.data.dao.impl.DBConnection.closeConnection;
import static com.aldrin.pos.data.dao.impl.DBConnection.getCon;
import com.aldrin.pos.model.StockIn;
import com.aldrin.pos.model.Transporter;
import com.aldrin.pos.model.UserAccount;
import com.aldrin.pos.util.ComboBoxList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class StockInDAOImpl extends DBConnection implements StockInDAO {


    @Override
    public void addStockIn(StockIn stockIn) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO STOCK_IN (ID,USER_ID,TRANSPORTER_ID) VALUES  (?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setLong(2, stockIn.getUserAccount().getId());
            ps.setLong(3, stockIn.getTransporter().getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStockIn(StockIn stockIn) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE STOCK_IN SET USER_ID =?, TRANSPORTER_ID =? WHERE STOCK_IN.ID = ?");
            ps.setLong(1, stockIn.getUserAccount().getId());
            ps.setLong(2, stockIn.getTransporter().getId());
            ps.setDate(3, stockIn.getCreatedAt());
            ps.setLong(4, stockIn.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStockIn(StockIn stockIn) {

    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<StockIn> selectStockIn() {
        ArrayList<StockIn> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    STOCK_IN.ID,\n"
                    + "    STOCK_IN.CREATEDAT, \n"
                    + "    STOCK_IN.TRANSPORTER_ID, \n"
                    + "    STOCK_IN.USER_ID, \n"
                    + "    USER_ACCOUNT.FIRSTNAME AS UFIRSTNAME, \n"
                    + "    USER_ACCOUNT.SURNAME AS USURNAME, \n"
                    + "    TRANSPORTER.FIRSTNAME AS TFIRSTNAME, \n"
                    + "    TRANSPORTER.SURNAME AS TSURNAME\n"
                    + "FROM \n"
                    + "    STOCK_IN \n"
                    + "INNER JOIN \n"
                    + "    USER_ACCOUNT \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        STOCK_IN.USER_ID = USER_ACCOUNT.ID) \n"
                    + "INNER JOIN \n"
                    + "    TRANSPORTER \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        STOCK_IN.TRANSPORTER_ID = TRANSPORTER.ID) ORDER BY STOCK_IN.CREATEDAT DESC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                StockIn s = new StockIn();
                UserAccount ua = new UserAccount();
                Transporter t = new Transporter();
                ua.setFirstname(rs.getString("UFIRSTNAME"));
                ua.setSurname(rs.getString("USURNAME"));
                ua.setId(rs.getLong("USER_ID"));
                t.setFirstname(rs.getString("TFIRSTNAME"));
                t.setSurname(rs.getString("TSURNAME"));
                t.setId(rs.getLong("TRANSPORTER_ID"));
                s.setCreatedAt(rs.getDate("CREATEDAT"));
                s.setTimestamp(rs.getTimestamp("CREATEDAT"));
                s.setTransporter(t);
                s.setUserAccount(ua);
                s.setId(rs.getLong("ID"));
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
    public void comboBoxStockIn() {

    }

    @Override
    public Long getMaxIdForRef() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(STOCK_IN.ID) AS ID  \n"
                    + "FROM \n"
                    + "    STOCK_IN");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                if(idl==0){
                    maxId =1L;
                }else{
                    maxId = idl; 
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
    public Long getMaxId() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(STOCK_IN.ID) AS ID  \n"
                    + "FROM \n"
                    + "    STOCK_IN ");
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

   

}

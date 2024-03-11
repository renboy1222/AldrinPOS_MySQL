/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.data.dao.impl;

import com.aldrin.pos.data.dao.CategoryDAO;
import static com.aldrin.pos.data.dao.impl.DBConnection.closeConnection;
import static com.aldrin.pos.data.dao.impl.DBConnection.getCon;
import com.aldrin.pos.model.Category;
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
public class CategoryDAOImpl extends DBConnection implements CategoryDAO {

    @Override
    public void addCategory(Category category) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO CATEGORY (ID,CATEGORY,DESCRIPTION) VALUES  (?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, category.getCategory());
            ps.setString(3, category.getDescription());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(Category category) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CATEGORY SET CATEGORY =?, DESCRIPTION =? WHERE CATEGORY.ID = ?");
            ps.setString(1, category.getCategory());
            ps.setString(2, category.getDescription());
            ps.setLong(3, category.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Category category) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CATEGORY SET DELETED =? WHERE CATEGORY.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, category.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<Category> selectCategory() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM CATEGORY WHERE CATEGORY.DELETED =FALSE  ORDER BY CATEGORY ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getLong("ID"));
                c.setCategory(rs.getString("CATEGORY"));
                c.setDescription(rs.getString("DESCRIPTION"));
                list.add(c);
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
    public void comboBoxCategory() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM CATEGORY WHERE CATEGORY.DELETED =FALSE  ORDER BY CATEGORY ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String namel = rs.getString("CATEGORY");
                this.getList().add(new ComboBoxList(idl, namel));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

       @Override
    public Long getMaxId() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(CATEGORY.ID) AS ID  \n"
                    + "FROM \n"
                    + "    CATEGORY ");
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.data.dao.impl;

import com.aldrin.pos.data.dao.TransporterDAO;
import static com.aldrin.pos.data.dao.impl.DBConnection.closeConnection;
import static com.aldrin.pos.data.dao.impl.DBConnection.getCon;
import com.aldrin.pos.model.Transporter;
import com.aldrin.pos.util.ComboBoxList;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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
public class TransporterDAOImpl extends DBConnection implements TransporterDAO {

    @Override
    public void addTransporter(Transporter transporter) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO TRANSPORTER (TRANSPORTER.ID,TRANSPORTER.FIRSTNAME, \n"
                    + "    TRANSPORTER.SURNAME, \n"
                    + "    TRANSPORTER.MOBILE, \n"
                    + "    TRANSPORTER.EMAIL, \n"
                    + "    TRANSPORTER.ADDRESS, \n"
                    + "    TRANSPORTER.COMPANY, \n"
                    + "    TRANSPORTER.COMPANY_ADDRESS,PHOTO) VALUES  (?,?,?,?,?,?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, transporter.getFirstname());
            ps.setString(3, transporter.getSurname());
            ps.setString(4, transporter.getMobile());
            ps.setString(5, transporter.getEmail());
            ps.setString(6, transporter.getAddress());
            ps.setString(7, transporter.getCompany());
            ps.setString(8, transporter.getCompany_address());
            ps.setBytes(9, transporter.getPhoto());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTransporter(Transporter transporter) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE TRANSPORTER SET FIRSTNAME =?, SURNAME =?,MOBILE=?, "
                    + "EMAIL =?, ADDRESS=?, COMPANY=?, COMPANY_ADDRESS =?, PHOTO=? WHERE TRANSPORTER.ID = ?");
            ps.setString(1, transporter.getFirstname());
            ps.setString(2, transporter.getSurname());
            ps.setString(3, transporter.getMobile());
            ps.setString(4, transporter.getEmail());
            ps.setString(5, transporter.getAddress());
            ps.setString(6, transporter.getCompany());
            ps.setString(7, transporter.getCompany_address());
            ps.setBytes(8, transporter.getPhoto());
            ps.setLong(9, transporter.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransporter(Transporter transporter) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE TRANSPORTER SET DELETED =? WHERE TRANSPORTER.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, transporter.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<Transporter> selectTransporter() {
        ArrayList<Transporter> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    TRANSPORTER.ID, \n"
                    + "    TRANSPORTER.FIRSTNAME, \n"
                    + "    TRANSPORTER.SURNAME, \n"
                    + "    TRANSPORTER.MOBILE, \n"
                    + "    TRANSPORTER.EMAIL, \n"
                    + "    TRANSPORTER.ADDRESS, \n"
                    + "    TRANSPORTER.COMPANY, \n"
                    + "    TRANSPORTER.COMPANY_ADDRESS \n"
                    + "FROM \n"
                    + "    TRANSPORTER WHERE TRANSPORTER.DELETED =FALSE ORDER BY TRANSPORTER.SURNAME ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Transporter t = new Transporter();
                t.setId(rs.getLong("ID"));
                t.setFirstname(rs.getString("FIRSTNAME"));
                t.setSurname(rs.getString("SURNAME"));
                t.setMobile(rs.getString("MOBILE"));
                t.setEmail(rs.getString("EMAIL"));
                t.setAddress(rs.getString("ADDRESS"));
                t.setCompany(rs.getString("COMPANY"));
                t.setCompany_address(rs.getString("COMPANY_ADDRESS"));
                list.add(t);
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
    public Transporter findPhotoByTransporterId(Transporter transporter) {
        Transporter transporterPhoto = new Transporter();
        Blob photo = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT  PHOTO FROM TRANSPORTER  WHERE ID  =" + transporter.getId() + "");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Blob picturel = rs.getBlob("PHOTO");
                photo = picturel;
                byte[] bytes = convertBlobToBytes(picturel);
                transporterPhoto.setPhoto(bytes);
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transporterPhoto;
    }

    private static byte[] convertBlobToBytes(java.sql.Blob blob) throws IOException, SQLException {
        try (InputStream inputStream = blob.getBinaryStream()) {
            return convertInputStreamToBytes(inputStream);
        }
    }

    private static byte[] convertInputStreamToBytes(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }

    private static void writeBytesToFile(byte[] bytes, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(bytes);
        }
    }

    @Override
    public void comboBoxTransporter() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM TRANSPORTER WHERE  TRANSPORTER.DELETED =FALSE  ORDER BY SURNAME ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String surnamel = rs.getString("SURNAME");
                String firstnamel = rs.getString("FIRSTNAME");
                this.getList().add(new ComboBoxList(idl, surnamel + ", " + firstnamel));
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
                    + "    MAX(TRANSPORTER.ID) AS ID  \n"
                    + "FROM \n"
                    + "    TRANSPORTER ");
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

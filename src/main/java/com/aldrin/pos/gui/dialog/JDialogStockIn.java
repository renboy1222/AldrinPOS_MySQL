/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.pos.gui.dialog;

import com.aldrin.pos.data.dao.impl.StockInDAOImpl;
import com.aldrin.pos.gui.JFrameAldrinPOS;
import com.aldrin.pos.model.StockIn;
import com.aldrin.pos.model.StockInEntry;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ALRIN B.C.
 */
public class JDialogStockIn extends javax.swing.JDialog implements MouseListener {

    /**
     * Creates new form JDialogStockIn
     */
    private JFrameAldrinPOS jFrameSariPOS;
    private StockInEntry stockInEntry = new StockInEntry();

    public JDialogStockIn(JFrameAldrinPOS parent, boolean modal) {
        super(parent, modal);
        this.jFrameSariPOS = parent;
        initComponents();
        setTable();
        selectStockIn();
        jTextFieldSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        //icon
        jTextFieldSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("svg/search.svg",24,24));
        jButtonPreview.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStockInEntry = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButtonPreview = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("STOCK-IN");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jTableStockInEntry.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableStockInEntry);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(964, 10));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 964, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel5.setPreferredSize(new java.awt.Dimension(10, 100));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, java.awt.BorderLayout.WEST);

        jPanel6.setPreferredSize(new java.awt.Dimension(10, 406));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(964, 50));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(964, 50));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));

        jButton1.setIcon(new FlatSVGIcon("svg/file.svg",24,24));
        jButton1.setText("New");
        jButton1.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButton1.setPreferredSize(new java.awt.Dimension(80, 32));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButtonPreview.setIcon(new FlatSVGIcon("svg/preview.svg",24,24));
        jButtonPreview.setText("Preview");
        jButtonPreview.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonPreview.setPreferredSize(new java.awt.Dimension(80, 32));
        jButtonPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviewActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonPreview);

        jPanel7.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel8.setPreferredSize(new java.awt.Dimension(400, 50));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 10));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("SEARCH ");
        jLabel2.setPreferredSize(new java.awt.Dimension(70, 16));
        jPanel8.add(jLabel2);

        jTextFieldSearch.setPreferredSize(new java.awt.Dimension(250, 30));
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanel8.add(jTextFieldSearch);

        jPanel9.setPreferredSize(new java.awt.Dimension(10, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel9);

        jPanel7.add(jPanel8, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel7, java.awt.BorderLayout.NORTH);

        setSize(new java.awt.Dimension(980, 535));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
//        String text = jTextFieldSearch.getText().trim();
//        if (text.length() == 0) {
//            sorter.setRowFilter(null);
//        } else {
//            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + ",*"));
//        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JDialogStockInEntry stockInEntry = new JDialogStockInEntry(jFrameSariPOS, true);
        stockInEntry.setVisible(true);
        jButtonPreview.setEnabled(false);
        selectStockIn();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviewActionPerformed
        JDialogPreviewStockIn preview = new JDialogPreviewStockIn(jFrameSariPOS, true, stockInEntry);
        preview.setVisible(true);
        jButtonPreview.setEnabled(false);
    }//GEN-LAST:event_jButtonPreviewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonPreview;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableStockInEntry;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
   private StockInDAOImpl stockInDAOImpl = new StockInDAOImpl();
    private ArrayList<StockIn> stockInList;

    private void selectStockIn() {
        tableModel.setRowCount(0);
        stockInList = stockInDAOImpl.selectStockIn();
        tableModel.setRowCount(0);
        for (StockIn c : stockInList) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss a");
            String formattedTimestamp = dateFormat.format(c.getTimestamp());
            tableModel.addRow(new Object[]{c.getId(), c.getUserAccount().getId(), c.getTransporter().getId(), formattedTimestamp, c.getUserAccount().getSurname() + ", " + c.getUserAccount().getFirstname(), c.getTransporter().getSurname() + ", " + c.getTransporter().getFirstname()});
        }
    }
    public DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"STOCK IN ID", "USER ID", "TRANSPORTER ID", "CREATED", "USER", "PURCHASER"}, 0) {
        public Class getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            }
            switch (columnIndex) {
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                case 3:
                    return String.class;
                default:
                    return String.class;
            }
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 10) {
                return false;

            } else {
                return true;
            }
        }
    };
    private TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);

    private void setTable() {
        jTableStockInEntry.setCellSelectionEnabled(true);
        jTableStockInEntry = new JTable(tableModel);
        jScrollPane1.setViewportView(jTableStockInEntry);
        jTableStockInEntry.addMouseListener(this);
        jTableStockInEntry.setRowSorter(sorter);
//        TableColumn hide0 = jTableStockIn.getColumnModel().getColumn(0);
//        hide0.setMinWidth(0);
//        hide0.setMaxWidth(0);
//        hide0.setPreferredWidth(0);
        TableColumn hide1 = jTableStockInEntry.getColumnModel().getColumn(1);
        hide1.setMinWidth(0);
        hide1.setMaxWidth(0);
        hide1.setPreferredWidth(0);
        TableColumn hide2 = jTableStockInEntry.getColumnModel().getColumn(2);
        hide2.setMinWidth(0);
        hide2.setMaxWidth(0);
        hide2.setPreferredWidth(0);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jTableStockInEntry) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getClickCount() == 1) {
                    int row = jTableStockInEntry.getSelectedRow();
                    if (row != -1) {
//                        "STOCK IN ID", "USER ID", "TRANSPORTER ID", "CREATE AT", "USER", "PURCHASER"
                        Long productId = Long.parseLong(jTableStockInEntry.getValueAt(row, 0).toString());
                        Long categoryId = Long.parseLong(jTableStockInEntry.getValueAt(row, 1).toString());
                        String product = jTableStockInEntry.getValueAt(row, 2).toString();
                        String barcode = jTableStockInEntry.getValueAt(row, 3).toString();
                        String user = jTableStockInEntry.getValueAt(row, 4).toString();
                        String transporter = jTableStockInEntry.getValueAt(row, 5).toString();
                        StockInEntry se = new StockInEntry();
                        StockIn si = new StockIn();
                        se.setUser(user);
                        se.setTransporter(transporter);
                        
                        si.setId(productId);
                        se.setStockIn(si);

                        this.stockInEntry = se;
                        jButtonPreview.setEnabled(true);
                    }
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}

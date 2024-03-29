/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.pos.gui.dialog;

import com.aldrin.pos.data.dao.impl.ProductRemaining;
import com.aldrin.pos.gui.JFrameAldrinPOS;
import com.aldrin.pos.gui.dialog.report.JDialogRemainingProductsReport;
import com.aldrin.pos.model.StockInEntry;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ALRIN B.C.
 */
public class JDialogProductRemaining extends javax.swing.JDialog {

    /**
     * Creates new form JDialogPreviewStockIn
     */
    private JFrameAldrinPOS jFrameSariPOS;
    private StockInEntry stockInEntry;
    private DecimalFormat df = new DecimalFormat("##,##0.00");

    public JDialogProductRemaining(JFrameAldrinPOS jFrameSariPOS, boolean modal) {
        super(jFrameSariPOS, modal);
        initComponents();
        jPanelGrandTotal.putClientProperty(FlatClientProperties.STYLE,
                "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        setTitle("REMAINING PRODUCTS");
        setTable();
        selectRemainingProduct();
        autoCalulateTable();
        jTextFieldSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        //icon
        jTextFieldSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("svg/search.svg", 24, 24));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jPanelFrameContainer = new javax.swing.JPanel();
        jPanelGrandTotal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableStockInEntry = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelTransporter = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabelGrandTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel10.setPreferredSize(new java.awt.Dimension(350, 80));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanelFrameContainer.setPreferredSize(new java.awt.Dimension(260, 200));
        jPanelFrameContainer.setLayout(new java.awt.BorderLayout());

        jPanelGrandTotal.setPreferredSize(new java.awt.Dimension(260, 0));
        jPanelGrandTotal.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

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

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelGrandTotal.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(835, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(435, 50));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTransporter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(jLabelTransporter, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 160, 30));

        jLabelUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(jLabelUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 160, 30));

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(300, 50));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("SEARCH ");
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 16));

        jTextFieldSearch.setPreferredSize(new java.awt.Dimension(250, 30));
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanelGrandTotal.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanelFrameContainer.add(jPanelGrandTotal, java.awt.BorderLayout.CENTER);

        jPanel29.setOpaque(false);
        jPanel29.setPreferredSize(new java.awt.Dimension(270, 5));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel29, java.awt.BorderLayout.NORTH);

        jPanel30.setOpaque(false);
        jPanel30.setPreferredSize(new java.awt.Dimension(270, 60));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelGrandTotal.setFont(new java.awt.Font("Courier New", 0, 22)); // NOI18N
        jLabelGrandTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel30.add(jLabelGrandTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 10, 280, 30));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("TOTAL");
        jPanel30.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 80, 30));

        jButton1.setIcon(new FlatSVGIcon("svg/print.svg",24,24));
        jButton1.setText("Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jPanelFrameContainer.add(jPanel30, java.awt.BorderLayout.SOUTH);

        jPanel31.setOpaque(false);
        jPanel31.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel31, java.awt.BorderLayout.EAST);

        jPanel32.setOpaque(false);
        jPanel32.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel32, java.awt.BorderLayout.WEST);

        jPanel10.add(jPanelFrameContainer, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel10, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(956, 420));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JDialogRemainingProductsReport report = new JDialogRemainingProductsReport(jFrameSariPOS, true);
        report.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        String text = jTextFieldSearch.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + ",*"));
            autoCalulateTable();

        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelGrandTotal;
    private javax.swing.JLabel jLabelTransporter;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelFrameContainer;
    private javax.swing.JPanel jPanelGrandTotal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableStockInEntry;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
    private ProductRemaining productRemaining = new ProductRemaining();
    private ArrayList<ProductRemaining> productRemainingList;

    private void selectRemainingProduct() {
//"#", "CATEGORY", "PRODUCT", "UNIT", "QTY REMAINING", "PRICE BUYINGuf", "PRICE SELLINGuf", "LINETOTALuf","PRICE BUYING", "PRICE SELLING", "LINETOTAL"
        tableModel.setRowCount(0);
        productRemainingList = productRemaining.selectRemainingProduct();
        tableModel.setRowCount(0);
        for (ProductRemaining pr : productRemainingList) {
            tableModel.addRow(new Object[]{jTableStockInEntry.getRowCount() + 1,
                pr.getCategory().getCategory(), 
                pr.getProduct().getProduct(),
                pr.getUnit().getUnit(), 
                pr.getQtyRemaining(), 
                pr.getStockInEntry().getPriceBuying(), 
                pr.getStockInEntry().getPriceSelling(), 
                pr.getLineTotal(), 
                df.format(pr.getStockInEntry().getPriceBuying()), 
                df.format(pr.getStockInEntry().getPriceSelling()), 
                df.format(pr.getLineTotal())});
        }
    }

    public DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"#", "CATEGORY", "PRODUCT", "UNIT", "QTY REMAINING", "PRICE BUYINGUF", "PRICE SELLINGUF", "LINETOTALUF", "PRICE BUYING", "PRICE SELLING", "LINETOTAL"}, 0) {
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
                case 4:
                    return Integer.class;
                case 5:
                    return Integer.class;
                case 6:
                    return Integer.class;
                case 7:
                    return Integer.class;
                case 8:
                    return Integer.class;
                case 9:
                    return Integer.class;
                case 10:
                    return Integer.class;
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
//        jTableStockInEntry.addMouseListener(this);
        jTableStockInEntry.setRowSorter(sorter);

        TableColumn[] column = new TableColumn[100];

        column[0] = jTableStockInEntry.getColumnModel().getColumn(0);
        column[0].setPreferredWidth(30);

        column[1] = jTableStockInEntry.getColumnModel().getColumn(1);
        column[1].setPreferredWidth(60);

        column[2] = jTableStockInEntry.getColumnModel().getColumn(2);
        column[2].setPreferredWidth(280);

        column[3] = jTableStockInEntry.getColumnModel().getColumn(3);
        column[3].setPreferredWidth(60);

        column[4] = jTableStockInEntry.getColumnModel().getColumn(4);
        column[4].setPreferredWidth(80);

        column[5] = jTableStockInEntry.getColumnModel().getColumn(5);
        column[5].setPreferredWidth(80);

        column[6] = jTableStockInEntry.getColumnModel().getColumn(6);
        column[6].setPreferredWidth(80);

        column[7] = jTableStockInEntry.getColumnModel().getColumn(7);
        column[7].setPreferredWidth(80);

//        TableColumn hide0 = jTableStockInEntry.getColumnModel().getColumn(0);
//        hide0.setMinWidth(0);
//        hide0.setMaxWidth(0);
//        hide0.setPreferredWidth(0);
        TableColumn hide5 = jTableStockInEntry.getColumnModel().getColumn(5);
        hide5.setMinWidth(0);
        hide5.setMaxWidth(0);
        hide5.setPreferredWidth(0);
        TableColumn hide6 = jTableStockInEntry.getColumnModel().getColumn(6);
        hide6.setMinWidth(0);
        hide6.setMaxWidth(0);
        hide6.setPreferredWidth(0);
        TableColumn hide7 = jTableStockInEntry.getColumnModel().getColumn(7);
        hide7.setMinWidth(0);
        hide7.setMaxWidth(0);
        hide7.setPreferredWidth(0);

    }

    public void autoCalulateTable() {
        try {
            double grandTotal = 0.0D;
            for (int i = 0; i < jTableStockInEntry.getRowCount(); i++) {
                double lineTotal = Double.parseDouble(jTableStockInEntry.getValueAt(i, 7).toString());
                grandTotal = grandTotal + lineTotal;
                jLabelGrandTotal.setText(String.valueOf(df.format(grandTotal)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jTableStockInEntry.getRowCount() < 1) {
        }
    }

}

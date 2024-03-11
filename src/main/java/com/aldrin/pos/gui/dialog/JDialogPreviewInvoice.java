/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.pos.gui.dialog;

import com.aldrin.pos.data.dao.impl.InvoiceEntryDAOImpl;
import com.aldrin.pos.gui.JFrameAldrinPOS;
import com.aldrin.pos.model.InvoiceEntry;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.text.DecimalFormat;
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
public class JDialogPreviewInvoice extends javax.swing.JDialog {

    /**
     * Creates new form JDialogPreviewStockIn
     */
    private JFrameAldrinPOS jFrameSariPOS;
    private InvoiceEntry invoiceEntry;
    private DecimalFormat df = new DecimalFormat("##,##0.00");

    public JDialogPreviewInvoice(JFrameAldrinPOS jFrameSariPOS, boolean modal, InvoiceEntry se) {
        super(jFrameSariPOS, modal);
        initComponents();
        this.invoiceEntry = se;
        jPanelGrandTotal.putClientProperty(FlatClientProperties.STYLE,
                "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        setTitle("INVOICE ID:" + "[" + se.getInvoice().getId() + "]");
        setTable();
        selectInvoiceEntry();
        jLabelUser.setText(se.getInvoice().getUserAccount().getFirstname());
        jLabelAmount.setText(String.valueOf(df.format(se.getTotal())));
        jLabelGrandTotal.setText(String.valueOf(df.format(se.getTotal())));

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
        jTableInvoiceEntry = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelAmount = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabelGrandTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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

        jTableInvoiceEntry.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableInvoiceEntry);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelGrandTotal.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(835, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(435, 50));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("AMOUNT:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 100, 30));

        jLabelAmount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(jLabelAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 10, 160, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("USER:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 10, 40, 30));

        jLabelUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(jLabelUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 10, 160, 30));

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(300, 50));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
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
            .addGap(0, 845, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel29, java.awt.BorderLayout.NORTH);

        jPanel30.setOpaque(false);
        jPanel30.setPreferredSize(new java.awt.Dimension(270, 35));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelGrandTotal.setFont(new java.awt.Font("Courier New", 0, 22)); // NOI18N
        jLabelGrandTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel30.add(jLabelGrandTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 180, 30));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("TOTAL");
        jPanel30.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 80, 30));

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
            .addGap(0, 0, Short.MAX_VALUE)
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
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel32, java.awt.BorderLayout.WEST);

        jPanel10.add(jPanelFrameContainer, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel10, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(861, 406));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelAmount;
    private javax.swing.JLabel jLabelGrandTotal;
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
    private javax.swing.JTable jTableInvoiceEntry;
    // End of variables declaration//GEN-END:variables
    private InvoiceEntryDAOImpl stockInDAOImpl = new InvoiceEntryDAOImpl();
    private ArrayList<InvoiceEntry> invoiceEntryList;

    private void selectInvoiceEntry() {
//      "PRODUCT", " UNIT", "QTY", "PRICEUF", "TOTALUF", "PRICE", "TOTAL"
        tableModel.setRowCount(0);
        invoiceEntryList = stockInDAOImpl.selectInvoiceEntry(invoiceEntry);
        tableModel.setRowCount(0);
        for (InvoiceEntry se : invoiceEntryList) {
            tableModel.addRow(new Object[]{se.getStockInEntryId().getProduct().getProduct(), se.getStockInEntryId().getUnit().getUnit(),
                se.getQuantity(), se.getStockInEntryId().getPriceSelling(), se.getTotal(), df.format(se.getStockInEntryId().getPriceSelling()), df.format(se.getTotal())});
        }

    }
    public DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"PRODUCT", " UNIT", "QTY", "PRICEUF", "TOTALUF", "PRICE", "TOTAL"}, 0) {
        public Class getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            }
            switch (columnIndex) {
                case 1:
                    return Integer.class;
                case 2:
                    return Integer.class;
                case 3:
                    return Integer.class;
                case 4:
                    return Integer.class;
                case 5:
                    return Integer.class;
                case 6:
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
        jTableInvoiceEntry.setCellSelectionEnabled(true);
        jTableInvoiceEntry = new JTable(tableModel);
        jScrollPane1.setViewportView(jTableInvoiceEntry);
//        jTableStockInEntry.addMouseListener(this);
        jTableInvoiceEntry.setRowSorter(sorter);

        TableColumn[] column = new TableColumn[100];

        column[0] = jTableInvoiceEntry.getColumnModel().getColumn(0);
        column[0].setPreferredWidth(230);

        column[1] = jTableInvoiceEntry.getColumnModel().getColumn(1);
        column[1].setPreferredWidth(80);

        column[2] = jTableInvoiceEntry.getColumnModel().getColumn(2);
        column[2].setPreferredWidth(60);

        column[3] = jTableInvoiceEntry.getColumnModel().getColumn(3);
        column[3].setPreferredWidth(60);

        column[4] = jTableInvoiceEntry.getColumnModel().getColumn(4);
        column[4].setPreferredWidth(60);

        TableColumn hide3 = jTableInvoiceEntry.getColumnModel().getColumn(3);
        hide3.setMinWidth(0);
        hide3.setMaxWidth(0);
        hide3.setPreferredWidth(0);
        TableColumn hide4 = jTableInvoiceEntry.getColumnModel().getColumn(4);
        hide4.setMinWidth(0);
        hide4.setMaxWidth(0);
        hide4.setPreferredWidth(0);

    }

    public void autoCalulateTable() {
        try {
            double grandTotal = 0.0D;
            for (int i = 0; i < jTableInvoiceEntry.getRowCount(); i++) {
                double lineTotal = Double.parseDouble(jTableInvoiceEntry.getValueAt(i, 4).toString());
                grandTotal = grandTotal + lineTotal;
                jLabelGrandTotal.setText(String.valueOf(df.format(grandTotal)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jTableInvoiceEntry.getRowCount() < 1) {
        }
    }

}

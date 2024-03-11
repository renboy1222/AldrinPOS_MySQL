/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aldrin.pos.gui;

import com.aldrin.pos.POSController;
import com.aldrin.pos.gui.dialog.ProductToPrint;
import com.aldrin.pos.gui.dialog.JDialogProfit;
import com.aldrin.pos.gui.dialog.JDialogUserSales;
import com.aldrin.pos.util.HoldOrder;
import com.aldrin.pos.gui.dialog.JDialogInvoice;
import com.aldrin.pos.gui.dialog.JDialogPayment;
import com.aldrin.pos.gui.dialog.JDialogProductRemaining;
import com.aldrin.pos.gui.dialog.JDialogStockIn;
import com.aldrin.pos.data.dao.impl.CategoryDAOImpl;
import com.aldrin.pos.data.dao.impl.InvoiceDAOImpl;
import com.aldrin.pos.data.dao.impl.InvoiceEntryDAOImpl;
import com.aldrin.pos.data.dao.impl.ProductDAOImpl;
import com.aldrin.pos.data.dao.impl.StockInEntryDAOImpl;
import com.aldrin.pos.gui.dialog.JDialogAbout;
import com.aldrin.pos.gui.dialog.JDialogCategory;
import com.aldrin.pos.gui.dialog.JDialogChangePassword;
import com.aldrin.pos.gui.dialog.JDialogProduct;
import com.aldrin.pos.gui.dialog.JDialogTransporter;
import com.aldrin.pos.gui.dialog.JDialogUnit;
import com.aldrin.pos.gui.dialog.JDialogUserAccount;
import com.aldrin.pos.model.Invoice;
import com.aldrin.pos.model.InvoiceEntry;
import com.aldrin.pos.model.Product;
import com.aldrin.pos.model.StockInEntry;
import com.aldrin.pos.model.UserAccount;
import com.aldrin.pos.util.ComboBoxAutoFill;
import com.aldrin.pos.util.ComboBoxList;
import com.aldrin.pos.util.NumberInput;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.HSLColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author ALRIN B.C.
 */
public class JFrameAldrinPOS extends javax.swing.JFrame implements MouseListener, Printable {

    /**
     * Creates new form JFrameAldrinPOS
     */
    File fontStyle = new File("src/main/resources/fonts/Oswald-VariableFont_wght.ttf");
    private ProductDAOImpl productDAOImpl = new ProductDAOImpl();
    private DecimalFormat df = new DecimalFormat("##,##0.00");
    private JTextComponent editor;
    private UserAccount userAccount;
    public static UserAccount userLogin;

    public JFrameAldrinPOS() {
        initComponents();
        svgColorChange(new java.awt.Color(218, 165, 32));
        FlatSVGIcon icon = new FlatSVGIcon("svg/cash-register.svg", 16, 16);
        setIconImage(icon.getImage());
        jPanelGrandTotal.putClientProperty(FlatClientProperties.STYLE,
                "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        jPanelTotalDetails.putClientProperty(FlatClientProperties.STYLE,
                "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        jPanel6.putClientProperty(FlatClientProperties.STYLE,
                "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        setTable();
        jComboBoxProduct.setVisible(false);
        jToggleButton1.setSelected(true);
        jToggleButton1.setIcon(new FlatSVGIcon("svg/barcode.svg", 32, 32));
        jTextFieldBarcode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "BARCODE");
        comboBoxProduct();
        new NumberInput().intValidator(jTextFieldBarcode);

        jButtonQtyAdd.setEnabled(false);
        jButtonQtyMinus.setEnabled(false);
        jButtonQtyEdit.setEnabled(false);
        jButtonQtyRemove.setEnabled(false);
        paymentIsReady();
        fontsInit();
        jComboBoxProduct.setEditable(true);
        editor = (JTextComponent) jComboBoxProduct.getEditor().getEditorComponent();
        editor.addKeyListener(new ComboBoxItemKeyListener());
        editor.setDocument(new ComboBoxAutoFill(jComboBoxProduct));
        jScrollPane1.setFocusable(false);
        jTableDispense.setFocusable(false);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                quitApp();
            }
        });
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(84f);
            jLabelTotal.setFont(font);
        } catch (FontFormatException ex) {
            Logger.getLogger(JFrameAldrinPOS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JFrameAldrinPOS.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanelRight = new javax.swing.JPanel();
        jPanelFrameTotalDetails = new javax.swing.JPanel();
        jPanelTotalDetails = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        jPanelCash3 = new javax.swing.JPanel();
        jPanel57 = new javax.swing.JPanel();
        jLabelItems = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        jLabelCash3 = new javax.swing.JLabel();
        jPanelCash4 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jLabeltxtCash4 = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        jLabelCash4 = new javax.swing.JLabel();
        jPanelCash1 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jLabeltxtCash1 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        jLabelCash1 = new javax.swing.JLabel();
        jPanelCash2 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jLabeltxtCash2 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        jLabelCash2 = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanelFrameContainer = new javax.swing.JPanel();
        jPanelGrandTotal = new javax.swing.JPanel();
        jLabelTotal = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jPanelTotal = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jLabeltxtAmountDue = new javax.swing.JLabel();
        jPanel62 = new javax.swing.JPanel();
        jLabelAmountDue = new javax.swing.JLabel();
        jPanelCash = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jLabeltxtCash = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jLabelCash = new javax.swing.JLabel();
        jPanelChange = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jLabeltxtChange = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jLabelChange = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDispense = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanelButtons = new javax.swing.JPanel();
        jButtonQtyAdd = new javax.swing.JButton(new FlatSVGIcon("svg/add.svg",32,32));
        jLabel1 = new javax.swing.JLabel();
        jButtonQtyMinus = new javax.swing.JButton(new FlatSVGIcon("svg/minus.svg",32,32));
        jLabel2 = new javax.swing.JLabel();
        jButtonQtyEdit = new javax.swing.JButton(new FlatSVGIcon("svg/edit.svg",32,32));
        jLabel6 = new javax.swing.JLabel();
        jButtonQtyRemove = new javax.swing.JButton(new FlatSVGIcon("svg/remove.svg",32,32));
        jPanelButtons1 = new javax.swing.JPanel();
        jButtonNewDispense = new javax.swing.JButton(new FlatSVGIcon("svg/file.svg",32,32));
        jLabel3 = new javax.swing.JLabel();
        jButtonHold = new javax.swing.JButton(new FlatSVGIcon("svg/hold.svg",32,32));
        jLabel4 = new javax.swing.JLabel();
        jButtonPayment = new javax.swing.JButton(new FlatSVGIcon("svg/payment.svg",32,32));
        jPanel20 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jTextFieldBarcode = new javax.swing.JTextField();
        jComboBoxProduct = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton5 = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonStockIn = new javax.swing.JButton();
        jButtonDispense = new javax.swing.JButton();
        jButtonProducts = new javax.swing.JButton();
        jButtonSale = new javax.swing.JButton();
        jButtonSale1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuUser = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuManage = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuSetting = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemLight = new javax.swing.JMenuItem();
        jMenuItemDark = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aldrin POS v1.0");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanelRight.setOpaque(false);
        jPanelRight.setPreferredSize(new java.awt.Dimension(350, 435));
        jPanelRight.setLayout(new java.awt.BorderLayout());

        jPanelFrameTotalDetails.setPreferredSize(new java.awt.Dimension(260, 200));
        jPanelFrameTotalDetails.setLayout(new java.awt.BorderLayout());

        jPanelTotalDetails.setPreferredSize(new java.awt.Dimension(260, 0));
        jPanelTotalDetails.setLayout(new java.awt.BorderLayout());

        jPanel41.setOpaque(false);
        jPanel41.setPreferredSize(new java.awt.Dimension(340, 5));

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanelTotalDetails.add(jPanel41, java.awt.BorderLayout.NORTH);

        jPanel44.setOpaque(false);
        jPanel44.setLayout(new java.awt.BorderLayout());

        jPanel27.setOpaque(false);
        jPanel27.setLayout(new java.awt.BorderLayout());

        jPanel47.setOpaque(false);
        jPanel47.setPreferredSize(new java.awt.Dimension(340, 280));
        jPanel47.setLayout(new java.awt.BorderLayout());

        jPanel42.setLayout(new java.awt.BorderLayout());

        jPanel43.setOpaque(false);
        jPanel43.setPreferredSize(new java.awt.Dimension(0, 280));
        jPanel43.setLayout(new java.awt.GridLayout(0, 1));

        jPanelCash3.setOpaque(false);
        jPanelCash3.setLayout(new java.awt.BorderLayout());

        jPanel57.setOpaque(false);
        jPanel57.setLayout(new java.awt.GridLayout(0, 1));

        jLabelItems.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelItems.setForeground(new java.awt.Color(102, 102, 102));
        jLabelItems.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelItems.setText("0");
        jPanel57.add(jLabelItems);

        jPanelCash3.add(jPanel57, java.awt.BorderLayout.CENTER);

        jPanel58.setOpaque(false);
        jPanel58.setPreferredSize(new java.awt.Dimension(150, 240));
        jPanel58.setLayout(new java.awt.GridLayout(0, 1));

        jLabelCash3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelCash3.setForeground(new java.awt.Color(102, 102, 102));
        jLabelCash3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCash3.setText("ITEMS:");
        jPanel58.add(jLabelCash3);

        jPanelCash3.add(jPanel58, java.awt.BorderLayout.WEST);

        jPanel43.add(jPanelCash3);

        jPanelCash4.setOpaque(false);
        jPanelCash4.setLayout(new java.awt.BorderLayout());

        jPanel59.setOpaque(false);
        jPanel59.setLayout(new java.awt.GridLayout(0, 1));

        jLabeltxtCash4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabeltxtCash4.setForeground(new java.awt.Color(102, 102, 102));
        jLabeltxtCash4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabeltxtCash4.setText("0.00");
        jPanel59.add(jLabeltxtCash4);

        jPanelCash4.add(jPanel59, java.awt.BorderLayout.CENTER);

        jPanel60.setOpaque(false);
        jPanel60.setPreferredSize(new java.awt.Dimension(150, 240));
        jPanel60.setLayout(new java.awt.GridLayout(0, 1));

        jLabelCash4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelCash4.setForeground(new java.awt.Color(102, 102, 102));
        jLabelCash4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCash4.setText("GROSS AMOUNT:");
        jPanel60.add(jLabelCash4);

        jPanelCash4.add(jPanel60, java.awt.BorderLayout.WEST);

        jPanel43.add(jPanelCash4);

        jPanelCash1.setOpaque(false);
        jPanelCash1.setLayout(new java.awt.BorderLayout());

        jPanel51.setOpaque(false);
        jPanel51.setLayout(new java.awt.GridLayout(0, 1));

        jLabeltxtCash1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabeltxtCash1.setForeground(new java.awt.Color(102, 102, 102));
        jLabeltxtCash1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabeltxtCash1.setText("0.00");
        jPanel51.add(jLabeltxtCash1);

        jPanelCash1.add(jPanel51, java.awt.BorderLayout.CENTER);

        jPanel54.setOpaque(false);
        jPanel54.setPreferredSize(new java.awt.Dimension(150, 240));
        jPanel54.setLayout(new java.awt.GridLayout(0, 1));

        jLabelCash1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelCash1.setForeground(new java.awt.Color(102, 102, 102));
        jLabelCash1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCash1.setText("DISCOUNT:");
        jPanel54.add(jLabelCash1);

        jPanelCash1.add(jPanel54, java.awt.BorderLayout.WEST);

        jPanel43.add(jPanelCash1);

        jPanelCash2.setOpaque(false);
        jPanelCash2.setLayout(new java.awt.BorderLayout());

        jPanel55.setOpaque(false);
        jPanel55.setLayout(new java.awt.GridLayout(0, 1));

        jLabeltxtCash2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabeltxtCash2.setForeground(new java.awt.Color(102, 102, 102));
        jLabeltxtCash2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabeltxtCash2.setText("0.00");
        jPanel55.add(jLabeltxtCash2);

        jPanelCash2.add(jPanel55, java.awt.BorderLayout.CENTER);

        jPanel56.setOpaque(false);
        jPanel56.setPreferredSize(new java.awt.Dimension(150, 240));
        jPanel56.setLayout(new java.awt.GridLayout(0, 1));

        jLabelCash2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelCash2.setForeground(new java.awt.Color(102, 102, 102));
        jLabelCash2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCash2.setText("TAX:");
        jPanel56.add(jLabelCash2);

        jPanelCash2.add(jPanel56, java.awt.BorderLayout.WEST);

        jPanel43.add(jPanelCash2);

        jPanel42.add(jPanel43, java.awt.BorderLayout.CENTER);

        jPanel47.add(jPanel42, java.awt.BorderLayout.CENTER);

        jPanel63.setOpaque(false);
        jPanel63.setPreferredSize(new java.awt.Dimension(330, 130));
        jPanel63.setLayout(new java.awt.BorderLayout());

        jPanel10.setFocusable(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(350, 80));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanelFrameContainer.setPreferredSize(new java.awt.Dimension(260, 200));
        jPanelFrameContainer.setLayout(new java.awt.BorderLayout());

        jPanelGrandTotal.setBackground(new java.awt.Color(204, 204, 204));
        jPanelGrandTotal.setPreferredSize(new java.awt.Dimension(260, 0));
        jPanelGrandTotal.setLayout(new java.awt.BorderLayout());

        jLabelTotal.setFont(new java.awt.Font("Courier New", 0, 52)); // NOI18N
        jLabelTotal.setForeground(new java.awt.Color(102, 102, 102));
        jLabelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotal.setText("0.00");
        jLabelTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelGrandTotal.add(jLabelTotal, java.awt.BorderLayout.CENTER);

        jPanelFrameContainer.add(jPanelGrandTotal, java.awt.BorderLayout.CENTER);

        jPanel29.setOpaque(false);
        jPanel29.setPreferredSize(new java.awt.Dimension(270, 2));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel29, java.awt.BorderLayout.NORTH);

        jPanel30.setOpaque(false);
        jPanel30.setPreferredSize(new java.awt.Dimension(270, 2));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel30, java.awt.BorderLayout.SOUTH);

        jPanel31.setOpaque(false);
        jPanel31.setPreferredSize(new java.awt.Dimension(2, 0));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel31, java.awt.BorderLayout.EAST);

        jPanel32.setOpaque(false);
        jPanel32.setPreferredSize(new java.awt.Dimension(2, 0));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelFrameContainer.add(jPanel32, java.awt.BorderLayout.WEST);

        jPanel10.add(jPanelFrameContainer, java.awt.BorderLayout.CENTER);

        jPanel63.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel47.add(jPanel63, java.awt.BorderLayout.NORTH);

        jPanel27.add(jPanel47, java.awt.BorderLayout.NORTH);

        jPanel50.setOpaque(false);
        jPanel50.setPreferredSize(new java.awt.Dimension(330, 350));
        jPanel50.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        jPanelTotal.setOpaque(false);
        jPanelTotal.setLayout(new java.awt.BorderLayout());

        jPanel61.setOpaque(false);
        jPanel61.setLayout(new java.awt.GridLayout(0, 1));

        jLabeltxtAmountDue.setForeground(new java.awt.Color(102, 102, 102));
        jLabeltxtAmountDue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabeltxtAmountDue.setText("0.00");
        jPanel61.add(jLabeltxtAmountDue);

        jPanelTotal.add(jPanel61, java.awt.BorderLayout.CENTER);

        jPanel62.setOpaque(false);
        jPanel62.setPreferredSize(new java.awt.Dimension(150, 240));
        jPanel62.setLayout(new java.awt.GridLayout(0, 1));

        jLabelAmountDue.setForeground(new java.awt.Color(102, 102, 102));
        jLabelAmountDue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelAmountDue.setText("AMOUNT DUE:");
        jPanel62.add(jLabelAmountDue);

        jPanelTotal.add(jPanel62, java.awt.BorderLayout.WEST);

        jPanel50.add(jPanelTotal);

        jPanelCash.setOpaque(false);
        jPanelCash.setLayout(new java.awt.BorderLayout());

        jPanel48.setOpaque(false);
        jPanel48.setLayout(new java.awt.GridLayout(0, 1));

        jLabeltxtCash.setForeground(new java.awt.Color(0, 51, 102));
        jLabeltxtCash.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabeltxtCash.setText("0.00");
        jPanel48.add(jLabeltxtCash);

        jPanelCash.add(jPanel48, java.awt.BorderLayout.CENTER);

        jPanel49.setOpaque(false);
        jPanel49.setPreferredSize(new java.awt.Dimension(150, 240));
        jPanel49.setLayout(new java.awt.GridLayout(0, 1));

        jLabelCash.setForeground(new java.awt.Color(0, 51, 102));
        jLabelCash.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCash.setText("CASH:");
        jPanel49.add(jLabelCash);

        jPanelCash.add(jPanel49, java.awt.BorderLayout.WEST);

        jPanel50.add(jPanelCash);

        jPanelChange.setOpaque(false);
        jPanelChange.setLayout(new java.awt.BorderLayout());

        jPanel52.setOpaque(false);
        jPanel52.setLayout(new java.awt.GridLayout(0, 1));

        jLabeltxtChange.setForeground(new java.awt.Color(0, 102, 51));
        jLabeltxtChange.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabeltxtChange.setText("0.00");
        jPanel52.add(jLabeltxtChange);

        jPanelChange.add(jPanel52, java.awt.BorderLayout.CENTER);

        jPanel53.setOpaque(false);
        jPanel53.setPreferredSize(new java.awt.Dimension(150, 240));
        jPanel53.setLayout(new java.awt.GridLayout(0, 1));

        jLabelChange.setForeground(new java.awt.Color(0, 102, 51));
        jLabelChange.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelChange.setText("CHANGE:");
        jPanel53.add(jLabelChange);

        jPanelChange.add(jPanel53, java.awt.BorderLayout.WEST);

        jPanel50.add(jPanelChange);

        jPanel27.add(jPanel50, java.awt.BorderLayout.CENTER);

        jPanel64.setOpaque(false);
        jPanel64.setPreferredSize(new java.awt.Dimension(330, 5));

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel27.add(jPanel64, java.awt.BorderLayout.SOUTH);

        jPanel44.add(jPanel27, java.awt.BorderLayout.CENTER);

        jPanel45.setOpaque(false);
        jPanel45.setPreferredSize(new java.awt.Dimension(5, 506));

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
        );

        jPanel44.add(jPanel45, java.awt.BorderLayout.WEST);

        jPanel46.setOpaque(false);
        jPanel46.setPreferredSize(new java.awt.Dimension(5, 100));

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
        );

        jPanel44.add(jPanel46, java.awt.BorderLayout.EAST);

        jPanelTotalDetails.add(jPanel44, java.awt.BorderLayout.CENTER);

        jPanelFrameTotalDetails.add(jPanelTotalDetails, java.awt.BorderLayout.CENTER);

        jPanel33.setOpaque(false);
        jPanel33.setPreferredSize(new java.awt.Dimension(270, 2));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanelFrameTotalDetails.add(jPanel33, java.awt.BorderLayout.NORTH);

        jPanel34.setOpaque(false);
        jPanel34.setPreferredSize(new java.awt.Dimension(270, 5));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanelFrameTotalDetails.add(jPanel34, java.awt.BorderLayout.SOUTH);

        jPanel35.setOpaque(false);
        jPanel35.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelFrameTotalDetails.add(jPanel35, java.awt.BorderLayout.EAST);

        jPanel36.setOpaque(false);
        jPanel36.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelFrameTotalDetails.add(jPanel36, java.awt.BorderLayout.WEST);

        jPanelRight.add(jPanelFrameTotalDetails, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanelRight, java.awt.BorderLayout.EAST);

        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(899, 5));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel28.setOpaque(false);
        jPanel28.setPreferredSize(new java.awt.Dimension(5, 425));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel28, java.awt.BorderLayout.LINE_START);

        jPanel25.setLayout(new java.awt.BorderLayout());

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel4.setFocusable(false);
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setFocusable(false);
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel8.setFocusable(false);
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel11.setFocusable(false);
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel13.setFocusable(false);
        jPanel13.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setFocusable(false);

        jTableDispense.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableDispense.setFocusable(false);
        jScrollPane1.setViewportView(jTableDispense);

        jPanel13.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel17.setFocusable(false);
        jPanel17.setPreferredSize(new java.awt.Dimension(798, 65));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jPanel18.setFocusable(false);
        jPanel18.setLayout(new java.awt.BorderLayout());

        jPanel19.setFocusable(false);
        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanelButtons.setFocusable(false);
        jPanelButtons.setPreferredSize(new java.awt.Dimension(450, 80));
        jPanelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 10));

        jButtonQtyAdd.setText("<html><center>Quantity<br><h4 style=\"color:red; padding:0; margin:0;\">[F5]</h4></center></html>");
        jButtonQtyAdd.setFocusable(false);
        jButtonQtyAdd.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonQtyAdd.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonQtyAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQtyAddActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonQtyAdd);

        jLabel1.setText("     ");
        jPanelButtons.add(jLabel1);

        jButtonQtyMinus.setText("<html><center>Quantity<br><h4 style=\"color:red; padding:0; margin:0;\">[F6]</h4></center></html>");
        jButtonQtyMinus.setFocusable(false);
        jButtonQtyMinus.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonQtyMinus.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonQtyMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQtyMinusActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonQtyMinus);

        jLabel2.setText("     ");
        jPanelButtons.add(jLabel2);

        jButtonQtyEdit.setText("<html><center> Quantity<br><h4 style=\"color:red; padding:0; margin:0;\">[F7]</h4></center></html>");
        jButtonQtyEdit.setFocusable(false);
        jButtonQtyEdit.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonQtyEdit.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonQtyEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQtyEditActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonQtyEdit);

        jLabel6.setText("     ");
        jPanelButtons.add(jLabel6);

        jButtonQtyRemove.setText("<html><center>Product<br><h4 style=\"color:red; padding:0; margin:0;\">[F8]</h4></center></html>");
        jButtonQtyRemove.setFocusable(false);
        jButtonQtyRemove.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonQtyRemove.setMaximumSize(new java.awt.Dimension(120, 42));
        jButtonQtyRemove.setMinimumSize(new java.awt.Dimension(120, 42));
        jButtonQtyRemove.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonQtyRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQtyRemoveActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonQtyRemove);

        jPanel19.add(jPanelButtons, java.awt.BorderLayout.WEST);

        jPanelButtons1.setFocusable(false);
        jPanelButtons1.setPreferredSize(new java.awt.Dimension(300, 80));
        jPanelButtons1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 10));

        jButtonNewDispense.setText("<html><center>New<br><h4 style=\"color:red; padding:0; margin:0;\">[F1]</h4></center></html>");
        jButtonNewDispense.setFocusable(false);
        jButtonNewDispense.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonNewDispense.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonNewDispense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewDispenseActionPerformed(evt);
            }
        });
        jPanelButtons1.add(jButtonNewDispense);

        jLabel3.setText("     ");
        jPanelButtons1.add(jLabel3);

        jButtonHold.setForeground(new java.awt.Color(0, 102, 51));
        jButtonHold.setText("<html><center>Hold<br><h4 style=\"color:red; padding:0; margin:0;\">[F4]</h4></center></html>");
        jButtonHold.setFocusable(false);
        jButtonHold.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonHold.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonHold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHoldActionPerformed(evt);
            }
        });
        jPanelButtons1.add(jButtonHold);

        jLabel4.setText("     ");
        jPanelButtons1.add(jLabel4);

        jButtonPayment.setText("<html><center>Payment<br><h4 style=\"color:red; padding:0; margin:0;\">[Space]</h4></center></html>");
        jButtonPayment.setFocusable(false);
        jButtonPayment.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonPayment.setPreferredSize(new java.awt.Dimension(90, 40));
        jButtonPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPaymentActionPerformed(evt);
            }
        });
        jPanelButtons1.add(jButtonPayment);

        jPanel19.add(jPanelButtons1, java.awt.BorderLayout.CENTER);

        jPanel18.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel17.add(jPanel18, java.awt.BorderLayout.CENTER);

        jPanel20.setFocusable(false);
        jPanel20.setPreferredSize(new java.awt.Dimension(798, 5));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 805, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel17.add(jPanel20, java.awt.BorderLayout.NORTH);

        jPanel11.add(jPanel17, java.awt.BorderLayout.SOUTH);

        jPanel8.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel9.setFocusable(false);
        jPanel9.setPreferredSize(new java.awt.Dimension(889, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel15.setFocusable(false);
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        jButton1.setIcon(new FlatSVGIcon("svg/return.svg",32,32));
        jButton1.setToolTipText("Return dispense");
        jButton1.setFocusable(false);
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.setPreferredSize(new java.awt.Dimension(42, 36));
        jPanel15.add(jButton1);

        jButton2.setIcon(new FlatSVGIcon("svg/edit.svg",32,32));
        jButton2.setToolTipText("Edit dispense");
        jButton2.setFocusable(false);
        jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton2.setPreferredSize(new java.awt.Dimension(42, 36));
        jPanel15.add(jButton2);

        jButton3.setIcon(new FlatSVGIcon("svg/coupon.svg",32,32));
        jButton3.setToolTipText("Add coupon");
        jButton3.setFocusable(false);
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.setPreferredSize(new java.awt.Dimension(42, 36));
        jPanel15.add(jButton3);

        jButton4.setIcon(new FlatSVGIcon("svg/discount.svg",32,32));
        jButton4.setToolTipText("Add discount");
        jButton4.setFocusable(false);
        jButton4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton4.setPreferredSize(new java.awt.Dimension(42, 36));
        jPanel15.add(jButton4);

        jPanel9.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel16.setFocusable(false);
        jPanel16.setPreferredSize(new java.awt.Dimension(1048, 5));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 805, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel16, java.awt.BorderLayout.SOUTH);

        jPanel14.setFocusable(false);
        jPanel14.setPreferredSize(new java.awt.Dimension(380, 35));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel23.setFocusable(false);
        jPanel23.setPreferredSize(new java.awt.Dimension(520, 52));
        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        jTextFieldBarcode.setPreferredSize(new java.awt.Dimension(260, 36));
        jTextFieldBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBarcodeActionPerformed(evt);
            }
        });
        jTextFieldBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBarcodeKeyPressed(evt);
            }
        });
        jPanel23.add(jTextFieldBarcode);

        jComboBoxProduct.setFocusable(false);
        jComboBoxProduct.setPreferredSize(new java.awt.Dimension(260, 36));
        jPanel23.add(jComboBoxProduct);

        jPanel14.add(jPanel23, java.awt.BorderLayout.CENTER);

        jPanel22.setFocusable(false);
        jPanel22.setPreferredSize(new java.awt.Dimension(1, 35));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel22, java.awt.BorderLayout.WEST);

        jPanel21.setFocusable(false);
        jPanel21.setPreferredSize(new java.awt.Dimension(110, 55));
        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        jToggleButton1.setIcon(new FlatSVGIcon("svg/barcode.svg",32,32));
        jToggleButton1.setFocusable(false);
        jToggleButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButton1.setPreferredSize(new java.awt.Dimension(42, 36));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel21.add(jToggleButton1);

        jButton5.setIcon(new FlatSVGIcon("svg/keyboard.svg",32,32));
        jButton5.setToolTipText("Manual entry");
        jButton5.setFocusable(false);
        jButton5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton5.setPreferredSize(new java.awt.Dimension(42, 36));
        jPanel21.add(jButton5);

        jPanel14.add(jPanel21, java.awt.BorderLayout.EAST);

        jPanel9.add(jPanel14, java.awt.BorderLayout.WEST);

        jPanel5.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel37.setFocusable(false);
        jPanel37.setOpaque(false);
        jPanel37.setPreferredSize(new java.awt.Dimension(815, 3));

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel37, java.awt.BorderLayout.NORTH);

        jPanel38.setFocusable(false);
        jPanel38.setOpaque(false);
        jPanel38.setPreferredSize(new java.awt.Dimension(815, 5));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel38, java.awt.BorderLayout.SOUTH);

        jPanel39.setFocusable(false);
        jPanel39.setOpaque(false);
        jPanel39.setPreferredSize(new java.awt.Dimension(5, 503));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel39, java.awt.BorderLayout.EAST);

        jPanel40.setFocusable(false);
        jPanel40.setOpaque(false);
        jPanel40.setPreferredSize(new java.awt.Dimension(5, 503));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel40, java.awt.BorderLayout.LINE_START);

        jPanel25.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel26.setFocusable(false);
        jPanel26.setPreferredSize(new java.awt.Dimension(815, 5));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel25.add(jPanel26, java.awt.BorderLayout.SOUTH);

        jPanel3.add(jPanel25, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel12.setFocusable(false);
        jPanel12.setPreferredSize(new java.awt.Dimension(1170, 42));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel24.setFocusable(false);
        jPanel24.setLayout(new java.awt.BorderLayout());

        jToolBar1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jToolBar1.setRollover(true);
        jToolBar1.setFocusable(false);

        jButtonStockIn.setIcon(new FlatSVGIcon("svg/stockin.svg",32,32));
        jButtonStockIn.setToolTipText("Stock-in");
        jButtonStockIn.setFocusable(false);
        jButtonStockIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonStockIn.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonStockIn.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonStockIn.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonStockIn.setPreferredSize(new java.awt.Dimension(40, 40));
        jButtonStockIn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonStockIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStockInActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonStockIn);

        jButtonDispense.setIcon(new FlatSVGIcon("svg/sales.svg",32,32));
        jButtonDispense.setToolTipText("Invoice list");
        jButtonDispense.setFocusable(false);
        jButtonDispense.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDispense.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonDispense.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonDispense.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonDispense.setPreferredSize(new java.awt.Dimension(40, 40));
        jButtonDispense.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDispense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDispenseActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonDispense);

        jButtonProducts.setIcon(new FlatSVGIcon("svg/chart.svg",32,32));
        jButtonProducts.setToolTipText("Product remaining");
        jButtonProducts.setFocusable(false);
        jButtonProducts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonProducts.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonProducts.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonProducts.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonProducts.setPreferredSize(new java.awt.Dimension(40, 40));
        jButtonProducts.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProductsActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonProducts);

        jButtonSale.setIcon(new FlatSVGIcon("svg/sale.svg",32,32));
        jButtonSale.setToolTipText("User sales");
        jButtonSale.setFocusable(false);
        jButtonSale.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSale.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSale.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonSale.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonSale.setPreferredSize(new java.awt.Dimension(40, 40));
        jButtonSale.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaleActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonSale);

        jButtonSale1.setIcon(new FlatSVGIcon("svg/profit.svg",32,32));
        jButtonSale1.setToolTipText("Profit");
        jButtonSale1.setFocusable(false);
        jButtonSale1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSale1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSale1.setMaximumSize(new java.awt.Dimension(40, 40));
        jButtonSale1.setMinimumSize(new java.awt.Dimension(40, 40));
        jButtonSale1.setPreferredSize(new java.awt.Dimension(40, 40));
        jButtonSale1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSale1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSale1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonSale1);

        jPanel24.add(jToolBar1, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel24, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel12, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenuBar1.setOpaque(true);

        jMenuUser.setText("File");
        jMenuUser.setFocusable(false);

        jMenuItem9.setIcon(new FlatSVGIcon("svg/change password.svg",20,20));
        jMenuItem9.setText("Change Password");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenuUser.add(jMenuItem9);

        jMenuItem8.setIcon(new FlatSVGIcon("svg/logout.svg",20,20));
        jMenuItem8.setText("Logout");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenuUser.add(jMenuItem8);
        jMenuUser.add(jSeparator1);

        jMenuItem10.setIcon(new FlatSVGIcon("svg/close.svg",20,20));
        jMenuItem10.setText("Exit");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenuUser.add(jMenuItem10);

        jMenuBar1.add(jMenuUser);

        jMenuManage.setText("Manage");
        jMenuManage.setFocusable(false);

        jMenuItem2.setIcon(new FlatSVGIcon("svg/user.svg",16,16));
        jMenuItem2.setText("User accounts");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuManage.add(jMenuItem2);

        jMenuBar1.add(jMenuManage);

        jMenuSetting.setText("Settings");
        jMenuSetting.setFocusable(false);

        jMenuItem3.setIcon(new FlatSVGIcon("svg/category.svg",16,16));
        jMenuItem3.setText("Category");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuSetting.add(jMenuItem3);

        jMenuItem6.setIcon(new FlatSVGIcon("svg/truck.svg",16,16));
        jMenuItem6.setText("Transporter");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuSetting.add(jMenuItem6);

        jMenuItem5.setIcon(new FlatSVGIcon("svg/scale.svg",16,16));
        jMenuItem5.setText("Unit");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenuSetting.add(jMenuItem5);

        jMenuItem4.setIcon(new FlatSVGIcon("svg/product.svg",16,16));
        jMenuItem4.setText("Product");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuSetting.add(jMenuItem4);

        jMenuBar1.add(jMenuSetting);

        jMenu1.setText("Themes");

        jMenuItemLight.setText("Light ");
        jMenuItemLight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLightActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemLight);

        jMenuItemDark.setText("Dark");
        jMenuItemDark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDarkActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDark);
        jMenu1.add(jSeparator2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem1.setIcon(new FlatSVGIcon("svg/about.svg",16,16));
        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1186, 596));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JDialogUserAccount userAccount = new JDialogUserAccount(this, true);
        userAccount.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JDialogCategory category = new JDialogCategory(this, true);
        category.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JDialogProduct product = new JDialogProduct(this, true);
        product.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JDialogUnit unit = new JDialogUnit(this, true);
        unit.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        JDialogTransporter transporter = new JDialogTransporter(this, true);
        transporter.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButtonStockInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStockInActionPerformed
        JDialogStockIn stockIn = new JDialogStockIn(this, true);
        stockIn.setVisible(true);
    }//GEN-LAST:event_jButtonStockInActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            jTextFieldBarcode.setVisible(true);
            jComboBoxProduct.setVisible(false);
            jToggleButton1.setIcon(new FlatSVGIcon("svg/barcode.svg", 32, 32));
            jTextFieldBarcode.setFocusable(true);
            jComboBoxProduct.setFocusable(false);
        } else {
            jTextFieldBarcode.setVisible(false);
            jComboBoxProduct.setVisible(true);
            jToggleButton1.setIcon(new FlatSVGIcon("svg/list.svg", 32, 32));
            jTextFieldBarcode.setFocusable(false);
            jComboBoxProduct.setFocusable(true);

        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButtonProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProductsActionPerformed
        JDialogProductRemaining remaining = new JDialogProductRemaining(this, true);
        remaining.setVisible(true);
    }//GEN-LAST:event_jButtonProductsActionPerformed

    private void jButtonDispenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDispenseActionPerformed
        JDialogInvoice invoice = new JDialogInvoice(this, true);
        invoice.setVisible(true);
    }//GEN-LAST:event_jButtonDispenseActionPerformed

    private void jTextFieldBarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBarcodeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addProductToTable();
            autoCalulateTable();
            paymentIsReady();
            jLabelItems.setText(String.valueOf(jTableDispense.getRowCount()));
        }
    }//GEN-LAST:event_jTextFieldBarcodeKeyPressed

    private void jTextFieldBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBarcodeActionPerformed

    private void jButtonQtyAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQtyAddActionPerformed
        qtyAdd();
        autoCalulateTable();
    }//GEN-LAST:event_jButtonQtyAddActionPerformed

    private void jButtonQtyMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQtyMinusActionPerformed
        qtyMinus();
        autoCalulateTable();
    }//GEN-LAST:event_jButtonQtyMinusActionPerformed

    private void jButtonQtyEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQtyEditActionPerformed
        qtyEdit();
        autoCalulateTable();
    }//GEN-LAST:event_jButtonQtyEditActionPerformed

    private void jButtonQtyRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQtyRemoveActionPerformed
        removeProduct();
        autoCalulateTable();
        paymentIsReady();
        jLabelItems.setText(String.valueOf(jTableDispense.getRowCount()));
    }//GEN-LAST:event_jButtonQtyRemoveActionPerformed

    private void jButtonNewDispenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewDispenseActionPerformed
        newTransaction();

    }//GEN-LAST:event_jButtonNewDispenseActionPerformed

    private void jButtonHoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHoldActionPerformed

        if (jButtonHold.getText().equals("<html><center>Unhold<br><h4 style=\"color:red; padding:0; margin:0;\">[F4]</h4></center></html>")) {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure to unhold order?", "Hold confirmation!", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                jButtonHold.setText("<html><center>Hold<br><h4 style=\"color:red; padding:0; margin:0;\">[F4]</h4></center></html>");
                tableModel.setRowCount(0);
                unHoldOrderList();
                jButtonHold.setForeground(Color.green);
                jButtonHold.setOpaque(true);
                jButtonHold.setEnabled(true);
                jButtonPayment.setEnabled(true);
                autoCalulateTable();
                this.holdOrderList.clear();
            }
        } else if (jButtonHold.getText().equals("<html><center>Hold<br><h4 style=\"color:red; padding:0; margin:0;\">[F4]</h4></center></html>")) {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure to hold order?", "Hold confirmation!", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                jButtonHold.setText("<html><center>Unhold<br><h4 style=\"color:red; padding:0; margin:0;\">[F4]</h4></center></html>");
                holdOrderList();
                tableModel.setRowCount(0);
                jButtonHold.setForeground(Color.red);
                jButtonHold.setOpaque(true);
                jButtonHold.setEnabled(true);
                jButtonPayment.setEnabled(false);
                jLabelTotal.setText("");
            }
        }

    }//GEN-LAST:event_jButtonHoldActionPerformed

    private void jButtonPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPaymentActionPerformed
        addPayment();
    }//GEN-LAST:event_jButtonPaymentActionPerformed

    private void jButtonSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaleActionPerformed
        JDialogUserSales userSales = new JDialogUserSales(this, true);
        userSales.setVisible(true);
    }//GEN-LAST:event_jButtonSaleActionPerformed

    private void jButtonSale1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSale1ActionPerformed
        JDialogProfit profit = new JDialogProfit(this, true);
        profit.setVisible(true);
    }//GEN-LAST:event_jButtonSale1ActionPerformed

    private void jMenuItemLightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLightActionPerformed
        SwingUtilities.invokeLater(() -> {
            try {
                svgColorChange(new java.awt.Color(218, 165, 32));
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JFrameAldrinPOS.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }//GEN-LAST:event_jMenuItemLightActionPerformed

    private void jMenuItemDarkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDarkActionPerformed
        SwingUtilities.invokeLater(() -> {
            try {
//                svgColorChange(new java.awt.Color(135,206,250));//LightSkyBlue
//                svgColorChange(new java.awt.Color(175,238,238));//PaleTurquoise
//                svgColorChange(new java.awt.Color(46,139,87));//SeaGreen
                svgColorChange(new java.awt.Color(135, 206, 235));//SeaGreen
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JFrameAldrinPOS.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }//GEN-LAST:event_jMenuItemDarkActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        JDialogChangePassword changePassword = new JDialogChangePassword(this, true, userAccount);
        changePassword.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        this.dispose();
        new POSController(new JFrameLogin());
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        quitApp();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JDialogAbout about = new JDialogAbout(this, true);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameAldrinPOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonDispense;
    private javax.swing.JButton jButtonHold;
    private javax.swing.JButton jButtonNewDispense;
    private javax.swing.JButton jButtonPayment;
    private javax.swing.JButton jButtonProducts;
    private javax.swing.JButton jButtonQtyAdd;
    private javax.swing.JButton jButtonQtyEdit;
    private javax.swing.JButton jButtonQtyMinus;
    private javax.swing.JButton jButtonQtyRemove;
    private javax.swing.JButton jButtonSale;
    private javax.swing.JButton jButtonSale1;
    private javax.swing.JButton jButtonStockIn;
    private javax.swing.JComboBox<Object> jComboBoxProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelAmountDue;
    private javax.swing.JLabel jLabelCash;
    private javax.swing.JLabel jLabelCash1;
    private javax.swing.JLabel jLabelCash2;
    private javax.swing.JLabel jLabelCash3;
    private javax.swing.JLabel jLabelCash4;
    private javax.swing.JLabel jLabelChange;
    private javax.swing.JLabel jLabelItems;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabeltxtAmountDue;
    private javax.swing.JLabel jLabeltxtCash;
    private javax.swing.JLabel jLabeltxtCash1;
    private javax.swing.JLabel jLabeltxtCash2;
    private javax.swing.JLabel jLabeltxtCash4;
    private javax.swing.JLabel jLabeltxtChange;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemDark;
    private javax.swing.JMenuItem jMenuItemLight;
    private javax.swing.JMenu jMenuManage;
    private javax.swing.JMenu jMenuSetting;
    private javax.swing.JMenu jMenuUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelButtons1;
    private javax.swing.JPanel jPanelCash;
    private javax.swing.JPanel jPanelCash1;
    private javax.swing.JPanel jPanelCash2;
    private javax.swing.JPanel jPanelCash3;
    private javax.swing.JPanel jPanelCash4;
    private javax.swing.JPanel jPanelChange;
    private javax.swing.JPanel jPanelFrameContainer;
    private javax.swing.JPanel jPanelFrameTotalDetails;
    private javax.swing.JPanel jPanelGrandTotal;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JPanel jPanelTotal;
    private javax.swing.JPanel jPanelTotalDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTableDispense;
    private javax.swing.JTextField jTextFieldBarcode;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private CategoryDAOImpl roleDAOImpl = new CategoryDAOImpl();

    public DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"STOCK IN ID", "#", "UNIT", "PRODUCT", "QUANTITY", "PRICEUF", "LINE TOTALUF", "PRICE", "LINE TOTAL"}, 0) {
        public Class getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            }
            switch (columnIndex) {
                case 1:
                    return Integer.class;
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
        jTableDispense.setCellSelectionEnabled(true);
        jTableDispense = new JTable(tableModel);
        jScrollPane1.setViewportView(jTableDispense);
        jTableDispense.addMouseListener(this);
        jTableDispense.setRowSorter(sorter);
        TableColumn hide0 = jTableDispense.getColumnModel().getColumn(0);
        hide0.setMinWidth(0);
        hide0.setMaxWidth(0);
        hide0.setPreferredWidth(0);
        TableColumn hide5 = jTableDispense.getColumnModel().getColumn(5);
        hide5.setMinWidth(0);
        hide5.setMaxWidth(0);
        hide5.setPreferredWidth(0);
        TableColumn hide6 = jTableDispense.getColumnModel().getColumn(6);
        hide6.setMinWidth(0);
        hide6.setMaxWidth(0);
        hide6.setPreferredWidth(0);

//"STOCK IN ID", "UNIT", "PRODUCT", "QUANTITY", "PRICE", "LINE TOTAL"
        TableColumn[] column = new TableColumn[100];
        column[1] = jTableDispense.getColumnModel().getColumn(1);
        column[1].setPreferredWidth(30);

        column[2] = jTableDispense.getColumnModel().getColumn(2);
        column[2].setPreferredWidth(80);

        column[3] = jTableDispense.getColumnModel().getColumn(3);
        column[3].setPreferredWidth(320);

        column[4] = jTableDispense.getColumnModel().getColumn(4);
        column[4].setPreferredWidth(60);

        column[5] = jTableDispense.getColumnModel().getColumn(5);
        column[5].setPreferredWidth(100);

        column[6] = jTableDispense.getColumnModel().getColumn(6);
        column[6].setPreferredWidth(100);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jTableDispense) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getClickCount() == 1) {
                    int row = jTableDispense.getSelectedRow();
                    if (row != -1) {
                        jButtonQtyAdd.setEnabled(true);
                        jButtonQtyMinus.setEnabled(true);
                        jButtonQtyEdit.setEnabled(true);
                        jButtonQtyRemove.setEnabled(true);
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

    private void comboBoxProduct() {
        productDAOImpl.comboBoxProduct();
        jComboBoxProduct.removeAllItems();
        for (ComboBoxList a : productDAOImpl.getList()) {
            this.jComboBoxProduct.addItem(a);
        }
    }

    private void addProductToTable() {
        boolean multipleProduct = false;
        StockInEntryDAOImpl productDAOImpl = new StockInEntryDAOImpl();
        StockInEntry se = new StockInEntry();
        Product p = new Product();
        p.setBarcode(jTextFieldBarcode.getText());
        se.setProduct(p);
        se = productDAOImpl.findProductByBarCode(se);
        boolean runOut = productDAOImpl.validateQtyRunOut(se.getProduct().getId(), 1);
        if (runOut) {
            JOptionPane.showMessageDialog(this, "Quantity is run out.", "Quantity message", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        if (se == null) {
            JOptionPane.showConfirmDialog(this, "No barcode found in database!", "Warning message!!", JOptionPane.WARNING_MESSAGE);
            jTextFieldBarcode.setText("");
            return;
        } else {
            int qty = 0;
            if (jTableDispense.getRowCount() == 0) {
                tableModel.addRow(new Object[]{se.getProduct().getId(), jTableDispense.getRowCount() + 1, se.getUnit().getUnit(), se.getProduct().getProduct(), 1, se.getPriceBuying(), se.getPriceBuying(), df.format(se.getPriceBuying()), df.format(se.getPriceBuying())});
                jTextFieldBarcode.setText("");
                return;
            } else {
//          edit quantity  if duplicate product id               
                itemCounter:
                for (int i = 0; i < jTableDispense.getRowCount(); i++) {
                    Long productId = Long.parseLong(jTableDispense.getValueAt(i, 0).toString());
                    Integer previousQty = Integer.parseInt(jTableDispense.getValueAt(i, 4).toString());
                    float total = Float.parseFloat(jTableDispense.getValueAt(i, 5).toString());
                    qty = 1 + previousQty;
                    runOut = productDAOImpl.validateQtyRunOut(se.getProduct().getId(), qty);
                    if ((runOut) && (se.getProduct().getId() == productId)) {
                        JOptionPane.showMessageDialog(this, "Quantity is run out.", "Quantity message", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    if (se.getProduct().getId() == productId) {
                        jTableDispense.setValueAt(qty, i, 4);
                        jTextFieldBarcode.setText("");
                        multipleProduct = false;
                        break itemCounter;
                    } else {
                        multipleProduct = true;
                    }
                }
                if (multipleProduct == true) {
                    tableModel.addRow(new Object[]{se.getProduct().getId(), jTableDispense.getRowCount() + 1, se.getUnit().getUnit(), se.getProduct().getProduct(), 1, se.getPriceBuying(), se.getPriceBuying(), df.format(se.getPriceBuying()), df.format(se.getPriceBuying())});
                }
            }
            calculateLineTotal();
            se = null;
            jTextFieldBarcode.setText("");
        }
    }

    private void calculateLineTotal() {
        try {
            float grandTotal = 0.0F;
            for (int i = 0; i < jTableDispense.getRowCount(); i++) {
                Integer qty = Integer.parseInt(jTableDispense.getValueAt(i, 4).toString());
                Float lineTotal = Float.parseFloat(jTableDispense.getValueAt(i, 5).toString());
                grandTotal = qty * lineTotal;
                jTableDispense.setValueAt(grandTotal, i, 6);
                jTableDispense.setValueAt(df.format(grandTotal), i, 8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void autoCalulateTable() {
        try {
            setTotalAmount(0.0F);
            float grandTotal = 0.0F;
            for (int i = 0; i < jTableDispense.getRowCount(); i++) {
                float lineTotal = Float.parseFloat(jTableDispense.getValueAt(i, 6).toString());
                grandTotal = grandTotal + lineTotal;
                jLabelTotal.setText(String.valueOf(df.format(grandTotal)));
                jLabeltxtAmountDue.setText(String.valueOf(df.format(grandTotal)));
            }
            if (tableModel.getRowCount() == 0) {
                jLabelTotal.setText("0.00");
            }
            setTotalAmount(grandTotal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    "STOCK IN ID", "#", "UNIT", "PRODUCT", "QUANTITY", "PRICE", "LINE TOTAL"
    private void qtyAdd() {
        try {
            int addQty = 0;
            int selected = jTableDispense.getSelectedRow();
            Long productId = Long.parseLong(jTableDispense.getValueAt(selected, 0).toString());
            Integer qty = Integer.parseInt(jTableDispense.getValueAt(selected, 4).toString());
            Float price = Float.parseFloat(jTableDispense.getValueAt(selected, 5).toString());
            addQty = 1 + qty;
            StockInEntryDAOImpl stockInEntryDAOImpl = new StockInEntryDAOImpl();
            boolean runOut = stockInEntryDAOImpl.validateQtyRunOut(productId, addQty);
            if (runOut) {
                JOptionPane.showMessageDialog(this, "Quantity is run out.", "Quantity message", JOptionPane.PLAIN_MESSAGE);
                return;
            } else {
                jTableDispense.setValueAt(addQty, selected, 4);
                jTableDispense.setValueAt(addQty * price, selected, 6);
                jTableDispense.setValueAt(df.format(addQty * price), selected, 8);
                jButtonQtyAdd.setEnabled(false);
                jButtonQtyMinus.setEnabled(false);
                jButtonQtyEdit.setEnabled(false);
                jButtonQtyRemove.setEnabled(false);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
    }

    private void qtyMinus() {
        try {
            int minusQty = 0;
            int selected = jTableDispense.getSelectedRow();
            Integer qty = Integer.parseInt(jTableDispense.getValueAt(selected, 4).toString());
            Float price = Float.parseFloat(jTableDispense.getValueAt(selected, 5).toString());
            if (qty == 1) {
                //remove product
                return;
            } else {
                minusQty = qty - 1;
                jTableDispense.setValueAt(minusQty, selected, 4);
                jTableDispense.setValueAt(minusQty * price, selected, 6);
                jTableDispense.setValueAt(df.format(minusQty * price), selected, 8);

                jButtonQtyAdd.setEnabled(false);
                jButtonQtyMinus.setEnabled(false);
                jButtonQtyEdit.setEnabled(false);
                jButtonQtyRemove.setEnabled(false);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
    }

    private void qtyEdit() {
        StockInEntryDAOImpl stockInEntryDAOImpl = new StockInEntryDAOImpl();
        String q = null;
        try {
            int selected = jTableDispense.getSelectedRow();
            Object qty = jTableDispense.getValueAt(selected, 4);
            q = JOptionPane.showInputDialog(this, "Change the quantity in text", qty);
            int qty1 = 0;

            if (q != null) {
                qty1 = Integer.parseInt(q);
            }
            if (qty1 == 0) {
                JOptionPane.showMessageDialog(this, "Invalid input!!", "Warning message!!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Long productId = Long.parseLong(jTableDispense.getValueAt(selected, 0).toString());
            boolean runOut = stockInEntryDAOImpl.validateQtyRunOut(productId, qty1);
            if (runOut) {
                JOptionPane.showMessageDialog(this, "Quantity is run out.", "Quantity message", JOptionPane.PLAIN_MESSAGE);
                jButtonQtyAdd.setEnabled(false);
                jButtonQtyMinus.setEnabled(false);
                jButtonQtyEdit.setEnabled(false);
                jButtonQtyRemove.setEnabled(false);
                return;
            }

            Float price = Float.parseFloat(jTableDispense.getValueAt(selected, 5).toString());

            jTableDispense.setValueAt(qty1, selected, 4);
            jTableDispense.setValueAt(qty1 * price, selected, 6);
            jTableDispense.setValueAt(df.format(qty1 * price), selected, 8);
            jButtonQtyAdd.setEnabled(false);
            jButtonQtyMinus.setEnabled(false);
            jButtonQtyEdit.setEnabled(false);
            jButtonQtyRemove.setEnabled(false);

        } catch (NumberFormatException e) {
            JOptionPane.showConfirmDialog(this, q + " is not a valid integer", "Error quantiy input!!", JOptionPane.PLAIN_MESSAGE);
            return;
        }
    }

    private void removeProduct() {

        int numRows = jTableDispense.getSelectedRows().length;
        if (numRows == 0) {
            jLabelTotal.setText("0.00");
            return;
        }
        for (int i = 0; i < numRows; i++) {
            tableModel.removeRow(jTableDispense.getSelectedRow());
        }
        jButtonQtyAdd.setEnabled(false);
        jButtonQtyMinus.setEnabled(false);
        jButtonQtyEdit.setEnabled(false);
        jButtonQtyRemove.setEnabled(false);
    }

    private void newTransaction() {
        try {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure to create new transaction?", "New transaction confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                tableModel.setRowCount(0);
                jLabelTotal.setText("0.00");
                jButtonPayment.setEnabled(false);
                jButtonPayment.setEnabled(false);
                if (jButtonHold.getText().equals("<html><center>Unhold<br><h4 style=\"color:red; padding:0; margin:0;\">[F2]</h4></center></html>")) {
                    jButtonHold.setEnabled(true);
                } else if (jButtonHold.getText().equals("<html><center>Hold<br><h4 style=\"color:red; padding:0; margin:0;\">[F2]</h4></center></html>")) {
                    jButtonHold.setEnabled(false);
                }

                JOptionPane.showConfirmDialog(this, "Creating new transaction.", "Message", JOptionPane.PLAIN_MESSAGE);
                jLabelItems.setText(String.valueOf(jTableDispense.getRowCount()));
                jLabeltxtChange.setText("0.00");
                jLabeltxtCash.setText("0.00");
                jLabeltxtAmountDue.setText("0.00");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void paymentIsReady() {

        if (jTableDispense.getRowCount() == 0) {
            jButtonNewDispense.setEnabled(false);
            jButtonHold.setEnabled(false);
            jButtonPayment.setEnabled(false);
        } else {
            jButtonNewDispense.setEnabled(true);
            jButtonHold.setEnabled(true);
            jButtonPayment.setEnabled(true);
        }
    }
    private HoldOrder holdOrder = new HoldOrder();
    private ArrayList<HoldOrder> holdOrderList = new ArrayList<>();

    private void holdOrderList() {
        for (int i = 0; i < jTableDispense.getRowCount(); i++) {
//            "STOCK IN ID", "#", "UNIT", "PRODUCT", "QUANTITY", "PRICEUF", "LINE TOTALUF,"PRICE", "LINE TOTAL"
            String stockInId = jTableDispense.getValueAt(i, 0).toString();
            String unit = jTableDispense.getValueAt(i, 2).toString();
            String product = jTableDispense.getValueAt(i, 3).toString();
            String quantity = jTableDispense.getValueAt(i, 4).toString();
            String priceuf = jTableDispense.getValueAt(i, 5).toString();
            String lineTotaluf = jTableDispense.getValueAt(i, 6).toString();
            String price = jTableDispense.getValueAt(i, 7).toString();
            String lineTotal = jTableDispense.getValueAt(i, 8).toString();
            HoldOrder ho = new HoldOrder(stockInId, unit, product, quantity, priceuf, lineTotaluf, price, lineTotal);
            holdOrderList.add(ho);
        }
    }

    private void unHoldOrderList() {
//            "STOCK IN ID", "#", "UNIT", "PRODUCT", "QUANTITY", "PRICE", "LINE TOTAL"
        for (HoldOrder h : holdOrderList) {
            tableModel.addRow(new Object[]{h.getStockInId(), jTableDispense.getRowCount() + 1, h.getUnit(), h.getProduct(), h.getQuantity(), h.getPriceUf(), h.getLinePriceUf(), h.getPrice(), h.getLinePrice()});
        }
    }

    private void fontsInit() {
        try {

            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStyle).deriveFont(44f);
            jLabelAmountDue.setFont(font.deriveFont(28f));
            jLabeltxtAmountDue.setFont(font);
            jLabelCash.setFont(font);
            jLabelChange.setFont(font);
            jLabeltxtCash.setFont(font);
            jLabeltxtChange.setFont(font);
            jPanelTotal.putClientProperty(FlatClientProperties.STYLE,
                    "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
            jPanelCash.putClientProperty(FlatClientProperties.STYLE,
                    "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
            jPanelChange.putClientProperty(FlatClientProperties.STYLE,
                    "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Float totalAmount = 0.00F;

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    private void addPayment() {
        try {
            JDialogPayment payment = new JDialogPayment(this, true, getTotalAmount());
            payment.setVisible(true);
            StockInEntryDAOImpl stockInEntryDAOImpl = new StockInEntryDAOImpl();
            InvoiceEntryDAOImpl invoiceEntryDAOImpl = new InvoiceEntryDAOImpl();

            if (payment.isPaid() == true) {
                Invoice invoice = new Invoice();
                InvoiceEntry invoiceEntry = new InvoiceEntry();
                UserAccount userAccount = new UserAccount();
                StockInEntry stockInEntry = new StockInEntry();
//                problem of column of invoice id  from invoice enrty table
                InvoiceDAOImpl invoiceDaoImpl = new InvoiceDAOImpl();
                userAccount.setId(this.getUserAccount().getId());
                invoice.setUserAccount(userAccount);
                invoiceDaoImpl.addInvoice(invoice);
//              "STOCK IN ID", "#", "UNIT", "PRODUCT", "QUANTITY", "PRICE", "LINE TOTAL"
                for (int i = 0; i < jTableDispense.getRowCount(); i++) {
                    Integer qty = Integer.parseInt(jTableDispense.getValueAt(i, 4).toString());
                    if (qty == 1) {
                        Long productId = Long.parseLong(jTableDispense.getValueAt(i, 0).toString());
                        Long stockInEntryId = stockInEntryDAOImpl.getStockInEntryIDByProcessId(productId);

                        Long invoiceId = invoiceDaoImpl.getMaxIdForRef();
                        invoice.setId(invoiceId);
                        stockInEntry.setId(stockInEntryId);
                        invoiceEntry.setInvoice(invoice);
                        invoiceEntry.setStockInEntryId(stockInEntry);
                        stockInEntryDAOImpl.updateStockInEntryProcess(stockInEntry);
                        invoiceEntryDAOImpl.addInvoiceEntry(invoiceEntry);
                    } else if (qty > 1) {
                        for (int j = qty; 0 < j; j--) {
                            Long stockInEntryIdParam = Long.parseLong(jTableDispense.getValueAt(i, 0).toString());
                            Long stockInEntryId = stockInEntryDAOImpl.getStockInEntryIDByProcessId(stockInEntryIdParam);
                            Long invoiceId = invoiceDaoImpl.getMaxIdForRef();
                            invoice.setId(invoiceId);
                            stockInEntry.setId(stockInEntryId);
                            invoiceEntry.setInvoice(invoice);
                            invoiceEntry.setStockInEntryId(stockInEntry);
                            stockInEntryDAOImpl.updateStockInEntryProcess(stockInEntry);
                            invoiceEntryDAOImpl.addInvoiceEntry(invoiceEntry);
                        }
                    }
                }
                jButtonHold.setEnabled(false);
                jButtonPayment.setEnabled(false);
                jLabeltxtChange.setText(payment.getChange());
                jLabeltxtCash.setText(payment.getCash());
                jLabelItems.setText("0");
                createItemList();
                printReciept();
                tableModel.setRowCount(0);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ComboBoxItemKeyListener extends KeyAdapter {

        public void keyPressed(KeyEvent evt) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                comboBoxAddProductToTable();
                autoCalulateTable();
                paymentIsReady();
                jLabelItems.setText(String.valueOf(jTableDispense.getRowCount()));
            } else if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_A) {
            } else if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_D) {
            }
        }
    }

    private void comboBoxAddProductToTable() {
        boolean multipleProduct = false;
        StockInEntryDAOImpl productDAOImpl = new StockInEntryDAOImpl();
        StockInEntry se = new StockInEntry();
        Product p = new Product();
        ComboBoxList pId = (ComboBoxList) this.jComboBoxProduct.getSelectedItem();
        p.setId(pId.getId());
        se.setProduct(p);
        System.out.println("product id:" + p.getId());
        se = productDAOImpl.findProductById(se);

        boolean soldOut = productDAOImpl.validateQtyRunOut(se.getProduct().getId(), 1);
        if (soldOut) {
            JOptionPane.showMessageDialog(this, "Quantity is run out.", "Quantity message", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        if (se == null) {
            JOptionPane.showConfirmDialog(this, "No barcode found in database!", "Warning message!!", JOptionPane.WARNING_MESSAGE);
            jTextFieldBarcode.setText("");
            return;
        } else {
            int qty = 0;
            if (jTableDispense.getRowCount() == 0) {
                tableModel.addRow(new Object[]{se.getProduct().getId(), jTableDispense.getRowCount() + 1, se.getUnit().getUnit(), se.getProduct().getProduct(), 1, se.getPriceBuying(), se.getPriceBuying(), df.format(se.getPriceBuying()), df.format(se.getPriceBuying())});
                jTextFieldBarcode.setText("");
                return;
            } else {
//          edit quantity  if duplicate product id               
                itemCounter:
                for (int i = 0; i < jTableDispense.getRowCount(); i++) {
                    Long productId = Long.parseLong(jTableDispense.getValueAt(i, 0).toString());
                    Integer previousQty = Integer.parseInt(jTableDispense.getValueAt(i, 4).toString());
                    float total = Float.parseFloat(jTableDispense.getValueAt(i, 5).toString());
                    qty = 1 + previousQty;
                    soldOut = productDAOImpl.validateQtyRunOut(se.getProduct().getId(), qty);
                    if ((soldOut) && (se.getProduct().getId() == productId)) {
                        JOptionPane.showMessageDialog(this, "Quantity is run out.", "Quantity message", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    if (se.getProduct().getId() == productId) {
                        jTableDispense.setValueAt(qty, i, 4);
                        jTextFieldBarcode.setText("");
                        multipleProduct = false;
                        break itemCounter;
                    } else {
                        multipleProduct = true;
                    }
                }
                if (multipleProduct == true) {
                    tableModel.addRow(new Object[]{se.getProduct().getId(), jTableDispense.getRowCount() + 1, se.getUnit().getUnit(), se.getProduct().getProduct(), 1, se.getPriceBuying(), se.getPriceBuying(), df.format(se.getPriceBuying()), df.format(se.getPriceBuying())});
                }
            }
            calculateLineTotal();
            se = null;
            jTextFieldBarcode.setText("");
        }
    }

    private java.util.ArrayList<ProductToPrint> createItemList() {
        java.util.ArrayList<ProductToPrint> iL = new java.util.ArrayList<ProductToPrint>();
//        "STOCK IN ID", "#", "UNIT", "PRODUCT", "QUANTITY", "PRICE", "LINE TOTAL"
        for (int i = 0; i < jTableDispense.getRowCount(); i++) {
            String no = jTableDispense.getValueAt(i, 1).toString();
            String unit = jTableDispense.getValueAt(i, 2).toString();
            String product = jTableDispense.getValueAt(i, 3).toString();
            String qty = jTableDispense.getValueAt(i, 4).toString();
            String unitPrice = jTableDispense.getValueAt(i, 7).toString();
            String lineTotal = jTableDispense.getValueAt(i, 8).toString();
            ProductToPrint itm = new ProductToPrint(no, unit, product, qty, unitPrice, lineTotal);
            iL.add(itm);
        }
        return iL;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Float totalAmount = 0.0F;
        Float change = 0.0F;
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/aldrinPOS.png"));
        int result = NO_SUCH_PAGE;
        if (pageIndex == 0) {

            Graphics2D g2d = (Graphics2D) graphics;
            double width = pageFormat.getImageableWidth();
            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

            FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));
            try {
                int y = 15;
                int yShift = 10;
                int headerRectHeight = 15;
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
                double wh = pageFormat.getImageableWidth();
                double ht = pageFormat.getImageableHeight();
                g2d.drawImage(null, 0, 0, (int) wh, (int) ht, null);

                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                g2d.drawImage(icon.getImage(), 55, 20, 60, 30, rootPane);
                y += yShift + 30;
                g2d.drawString("------------------------------", 10, y);
                y += yShift;
                g2d.drawString(" Java Programming with Aldrin", 10, y);
                y += yShift;
                g2d.drawString("  Rufino St.,Legaspi Village ", 10, y);
                y += yShift;
                g2d.drawString("   Makati City, Metro Manila ", 10, y);
                y += yShift;
                g2d.drawString("------------------------------", 10, y);
                InvoiceDAOImpl invoiceDaoImpl = new InvoiceDAOImpl();
                Long invoiceId = invoiceDaoImpl.getMaxIdForRef();
                y += yShift;
                y += yShift;
                g2d.drawString("RECEIPT#: " + invoiceId + "   ", 10, y);
                y += yShift;
                g2d.drawString("------------------------------", 10, y);
                y += yShift;

                g2d.drawString(" Item                 Price", 10, y);
                y += yShift;
                g2d.drawString("------------------------------", 10, y);
                y += headerRectHeight;

                for (ProductToPrint item : createItemList()) {
                    g2d.drawString(" " + item.getProduct() + "                    ", 10, y);
                    y += yShift;
                    g2d.drawString("   " + item.getQuantity() + " x " + item.getUnitPrice(), 5, y);
                    g2d.drawString(String.valueOf(item.getLineTotal()), 130, y);
                    y += yShift;
                }
                Float cash = Float.parseFloat(jLabeltxtCash.getText());

                g2d.drawString("------------------------------", 10, y);
                y += yShift;
                g2d.drawString(" Total   :            " + String.valueOf(jLabeltxtAmountDue.getText()) + "   ", 10, y);
                y += yShift;
                g2d.drawString("------------------------------", 10, y);
                y += yShift;
                g2d.drawString(" Cash    :            " + df.format(cash) + "   ", 10, y);
                y += yShift;
                g2d.drawString("------------------------------", 10, y);
                y += yShift;
                g2d.drawString(" Change  :            " + String.valueOf(jLabeltxtChange.getText()) + "   ", 10, y);
                y += yShift;
                y += yShift;
                y += yShift;

                g2d.drawString("******************************", 10, y);
                y += yShift;
                g2d.drawString("    THANK YOU, COME AGAIN!!    ", 10, y);
                y += yShift;
                g2d.drawString("******************************", 10, y);
                y += yShift;

            } catch (Exception e) {
                e.printStackTrace();
            }
            result = PAGE_EXISTS;
        }
        return result;
    }

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double width = pf.getImageableWidth();
        double height = pf.getImageableHeight();
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height - cm_to_pp(1));
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);
        return pf;
    }

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 58d;
    }

    private void printReciept() {

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(this, getPageFormat(pj));

//      there's dialog
        pj.setPrintable(this);
        boolean ok = pj.printDialog();
        if (ok) {
            try {
                pj.print();
            } catch (PrinterException ex) {
            }
        }
    }

    private void svgColorChange(Color clr) {
        try {
            Function<Color, Color> mapper = null;
            float[] redHSL = HSLColor.fromRGB(clr);
            mapper = (color -> {
                float[] hsl = HSLColor.fromRGB(color);
                return HSLColor.toRGB(redHSL[0], 70.0F, hsl[2]);
            });
            FlatSVGIcon.ColorFilter.getInstance().setMapper(mapper);
            SwingUtilities.windowForComponent(jPanel1).repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the jMenuUser
     */
    public javax.swing.JMenu getjMenuUser() {
        return jMenuUser;
    }

    /**
     * @param jMenuUser the jMenuUser to set
     */
    public void setjMenuUser(javax.swing.JMenu jMenuUser) {
        this.jMenuUser = jMenuUser;
    }

    /**
     * @return the userAccount
     */
    public UserAccount getUserAccount() {
        return userAccount;
    }

    /**
     * @param userAccount the userAccount to set
     */
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * @return the jMenuManage
     */
    public javax.swing.JMenu getjMenuManage() {
        return jMenuManage;
    }

    /**
     * @param jMenuManage the jMenuManage to set
     */
    public void setjMenuManage(javax.swing.JMenu jMenuManage) {
        this.jMenuManage = jMenuManage;
    }

    /**
     * @return the jMenuSetting
     */
    public javax.swing.JMenu getjMenuSetting() {
        return jMenuSetting;
    }

    /**
     * @param jMenuSetting the jMenuSetting to set
     */
    public void setjMenuSetting(javax.swing.JMenu jMenuSetting) {
        this.jMenuSetting = jMenuSetting;
    }

    private void quitApp() {
        try {
            int reply = JOptionPane.showConfirmDialog(this,
                    "Are you sure to exit AldrinPOS application?",
                    "AldrinPOS - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (reply == JOptionPane.YES_OPTION) {
                System.exit(0);        //Close the Application.
            } else if (reply == JOptionPane.NO_OPTION) {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return the jButtonStockIn
     */
    public javax.swing.JButton getjButtonStockIn() {
        return jButtonStockIn;
    }

    /**
     * @param jButtonStockIn the jButtonStockIn to set
     */
    public void setjButtonStockIn(javax.swing.JButton jButtonStockIn) {
        this.jButtonStockIn = jButtonStockIn;
    }

    /**
     * @return the userLogin
     */
    public static UserAccount getUserLogin() {
        return userLogin;
    }

    /**
     * @param aUserLogin the userLogin to set
     */
    public static void setUserLogin(UserAccount aUserLogin) {
        userLogin = aUserLogin;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.pos.gui.dialog.au;

import com.aldrin.pos.AldrinPOS;
import com.aldrin.pos.data.dao.impl.TransporterDAOImpl;
import com.aldrin.pos.gui.JFrameAldrinPOS;
import com.aldrin.pos.model.Transporter;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ALDRIN B. C.
 */
public class JDialogTransporterAU extends javax.swing.JDialog {

    private TransporterDAOImpl studentDAOImpl = new TransporterDAOImpl();
    private JFrameAldrinPOS jFrameSariPOS;

    /**
     * Creates new form JDialogStudent
     */
    private Transporter transporter = new Transporter();
    static String title = "";

    public JDialogTransporterAU(JFrameAldrinPOS jFrameSariPOS, boolean modal) {
        super(jFrameSariPOS, modal);
        initComponents();
        setTitle("New transporter");
        this.title = "New";
        jTextFieldFirstname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "FIRST NAME");
        jTextFieldSurname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SURNAME");
        jTextFieldMobille.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MOBILE");
        jTextFieldEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "EMAIL");
        jTextFieldAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ADDRESS");
        jTextFieldCompany.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "COMPANY");
        jTextFieldCompAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "COMPANY'S ADDRESS");
        jButton1.setIcon(new FlatSVGIcon("svg/save.svg", 24, 24));

    }

    public JDialogTransporterAU(JFrameAldrinPOS jFrameSariPOS, boolean modal, Transporter transporter, String title) {
        super(jFrameSariPOS, modal);
        initComponents();
        setTitle("Update transporter");
        this.transporter = transporter;
        this.title = title;
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(187, 187, 187));
        jTextFieldFirstname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "FIRST NAME");
        jTextFieldSurname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SURNAME");
        jTextFieldMobille.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MOBILE");
        jTextFieldEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "EMAIL");
        jTextFieldAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ADDRESS");
        jTextFieldCompany.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "COMPANY");
        jTextFieldCompAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "COMPANY'S ADDRESS");
        jTextFieldFirstname.setText(transporter.getFirstname());
        jTextFieldSurname.setText(transporter.getSurname());
        jTextFieldMobille.setText(transporter.getMobile());
        jTextFieldEmail.setText(transporter.getEmail());
        jTextFieldAddress.setText(transporter.getAddress());
        jTextFieldCompany.setText(transporter.getCompany());
        jTextFieldCompAddress.setText(transporter.getCompany_address());
        displayPicture(transporter);
        jButton1.setIcon(new FlatSVGIcon("svg/edit.svg", 24, 24));

    }

    public JDialogTransporterAU(JFrameAldrinPOS jFrameSariPOS, boolean modal, String title, Transporter transporter) {
        super(jFrameSariPOS, modal);
        initComponents();
        setTitle("Delete transporter");
        this.transporter = transporter;
        this.title = title;
        getRootPane().putClientProperty(FlatClientProperties.TITLE_BAR_BACKGROUND, new Color(187, 187, 187));
        jTextFieldFirstname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "FIRST NAME");
        jTextFieldSurname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "SURNAME");
        jTextFieldMobille.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MOBILE");
        jTextFieldEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "EMAIL");
        jTextFieldAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "ADDRESS");
        jTextFieldCompany.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "COMPANY");
        jTextFieldCompAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "COMPANY'S ADDRESS");
        jTextFieldFirstname.setText(transporter.getFirstname());
        jTextFieldSurname.setText(transporter.getSurname());
        jTextFieldMobille.setText(transporter.getMobile());
        jTextFieldEmail.setText(transporter.getEmail());
        jTextFieldAddress.setText(transporter.getAddress());
        jTextFieldCompany.setText(transporter.getCompany());
        jTextFieldCompAddress.setText(transporter.getCompany_address());
        displayPicture(transporter);
        jButton1.setIcon(new FlatSVGIcon("svg/delete.svg", 24, 24));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldFirstname = new javax.swing.JTextField();
        jTextFieldSurname = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelPicture = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldCompAddress = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldMobille = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCompany = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("FIRST NAME:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 20, 70, 30));

        jTextFieldFirstname.setPreferredSize(new java.awt.Dimension(64, 30));
        getContentPane().add(jTextFieldFirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 250, -1));

        jTextFieldSurname.setPreferredSize(new java.awt.Dimension(64, 30));
        getContentPane().add(jTextFieldSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 250, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)), "PHOTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabelPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/no photo.jpg"))); // NOI18N
        jLabelPicture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPictureMouseClicked(evt);
            }
        });
        jPanel2.add(jLabelPicture, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(210, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 210, 230));

        jTextFieldCompAddress.setMinimumSize(new java.awt.Dimension(64, 32));
        jTextFieldCompAddress.setPreferredSize(new java.awt.Dimension(64, 32));
        getContentPane().add(jTextFieldCompAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 480, -1));

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 168, 32));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("SURNAME:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 60, 70, 30));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("EMAIL:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 100, 70, 30));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("MOBILE NO.:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 70, 30));

        jTextFieldEmail.setMinimumSize(new java.awt.Dimension(64, 32));
        jTextFieldEmail.setPreferredSize(new java.awt.Dimension(64, 32));
        getContentPane().add(jTextFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 250, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("<html><center>COMPANY<BR>ADDRESS</center></html>");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 260, 70, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 550, 1));

        jTextFieldMobille.setMinimumSize(new java.awt.Dimension(64, 32));
        jTextFieldMobille.setPreferredSize(new java.awt.Dimension(64, 32));
        getContentPane().add(jTextFieldMobille, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 250, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ADDRESS:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 180, 70, 30));

        jTextFieldAddress.setMinimumSize(new java.awt.Dimension(64, 32));
        jTextFieldAddress.setPreferredSize(new java.awt.Dimension(64, 32));
        getContentPane().add(jTextFieldAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 250, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("COMPANY:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 220, 70, 30));

        jTextFieldCompany.setMinimumSize(new java.awt.Dimension(64, 32));
        jTextFieldCompany.setPreferredSize(new java.awt.Dimension(64, 32));
        getContentPane().add(jTextFieldCompany, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 250, -1));

        setSize(new java.awt.Dimension(589, 405));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (this.title.equals("New")) {
            int response = JOptionPane.showConfirmDialog(jFrameSariPOS, "Are you sure to save " + jTextFieldFirstname.getText() + " " + jTextFieldSurname.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                this.transporter.setFirstname(jTextFieldFirstname.getText());
                this.transporter.setSurname(jTextFieldSurname.getText());
                this.transporter.setMobile(jTextFieldMobille.getText());
                this.transporter.setEmail(jTextFieldEmail.getText());
                this.transporter.setAddress(jTextFieldAddress.getText());
                this.transporter.setCompany(jTextFieldCompany.getText());
                this.transporter.setCompany_address(jTextFieldCompAddress.getText());
                try {
                    validatePhoto();
                } catch (URISyntaxException ex) {
                    Logger.getLogger(JDialogTransporterAU.class.getName()).log(Level.SEVERE, null, ex);
                }
                studentDAOImpl.addTransporter(transporter);
                this.dispose();
            }
        } else if (this.title.equals("Update")) {
            int response = JOptionPane.showConfirmDialog(jFrameSariPOS, "Are you sure to update " + jTextFieldFirstname.getText() + " " + jTextFieldSurname.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                this.transporter.setFirstname(jTextFieldFirstname.getText());
                this.transporter.setSurname(jTextFieldSurname.getText());
                this.transporter.setMobile(jTextFieldMobille.getText());
                this.transporter.setEmail(jTextFieldEmail.getText());
                this.transporter.setAddress(jTextFieldAddress.getText());
                this.transporter.setCompany(jTextFieldCompany.getText());
                this.transporter.setCompany_address(jTextFieldCompAddress.getText());
                try {
                    validatePhoto();
                } catch (URISyntaxException ex) {
                    Logger.getLogger(JDialogTransporterAU.class.getName()).log(Level.SEVERE, null, ex);
                }
                studentDAOImpl.updateTransporter(transporter);
                this.dispose();
            }
        } else if (this.title.equals("Delete")) {
            int response = JOptionPane.showConfirmDialog(jFrameSariPOS, "Are you sure to delete " + jTextFieldFirstname.getText() + " " + jTextFieldSurname.getText() + " ?", "Save confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                studentDAOImpl.deleteTransporter(transporter);
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabelPictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPictureMouseClicked

        browse();
    }//GEN-LAST:event_jLabelPictureMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelPicture;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldCompAddress;
    private javax.swing.JTextField jTextFieldCompany;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFirstname;
    private javax.swing.JTextField jTextFieldMobille;
    private javax.swing.JTextField jTextFieldSurname;
    // End of variables declaration//GEN-END:variables

    private File pictureFile = null;

    private void browse() {
        try {
            int returnVal = jFileChooser1.showOpenDialog(this);
            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                pictureFile = jFileChooser1.getSelectedFile();
                uploadPhoto(pictureFile);
                int IMG_WIDTH = jLabelPicture.getWidth();
                int IMG_HEIGHT = jLabelPicture.getHeight();

                try {
                    BufferedImage originalImage = ImageIO.read(pictureFile);
                    int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
                    Graphics2D g = resizedImage.createGraphics();
                    g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
                    g.dispose();
                    g.setComposite(AlphaComposite.Src);

                    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

                    jLabelPicture.setIcon(new ImageIcon(resizedImage)); //to eliminate .jpeg from picture filename
                    ImageIO.write(resizedImage, "png", new File(AldrinPOS.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "\\images\\model.jpg"));
                    

                } catch (final IOException ex) {
                  
                }

            } else {
                File defaultDirectory = new File(System.getProperty("user.home"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadPhoto(File file) {
        if (file != null) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] imageData = new byte[(int) file.length()];
                fis.read(imageData);
                transporter.setPhoto(imageData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    int IMG_WIDTH = 200;
    int IMG_HEIGHT = 200;

    private void displayPicture(Transporter transporter) {
        try {
            byte[] imageData = transporter.getPhoto();
            ImageIcon imageIcon = new ImageIcon(imageData);

            Image image = imageIcon.getImage();
            int type = BufferedImage.TYPE_INT_ARGB;

            BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(image, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
            g.dispose();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            jLabelPicture.setIcon(new ImageIcon(resizedImage));//image to label

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validatePhoto() throws URISyntaxException {
        if (transporter.getPhoto() == null) {
            File targetClassesDir = new File(AldrinPOS.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "\\images\\no photo.jpg");
            System.out.println("photo location:"+targetClassesDir );
            try {
                FileInputStream fis = new FileInputStream(targetClassesDir);
                byte[] imageData = new byte[(int) targetClassesDir.length()];
                fis.read(imageData);
                transporter.setPhoto(imageData);
            } catch (Exception e) {
                System.out.println("default of no photo is error");
            }
        }
    }

}

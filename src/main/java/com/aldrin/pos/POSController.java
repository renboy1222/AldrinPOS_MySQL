/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos;

import com.aldrin.pos.data.dao.impl.UserAccountDAOImpl;
import com.aldrin.pos.gui.JFrameAldrinPOS;
import com.aldrin.pos.gui.JFrameLogin;
import com.aldrin.pos.model.Role;
import com.aldrin.pos.model.UserAccount;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ALRIN B.C.
 */
@Setter
@Getter
@ToString
public class POSController {

    JFrameAldrinPOS jFrameAldrinPOS = new JFrameAldrinPOS();
    JFrameLogin jFrameLogin = new JFrameLogin();

    public POSController(JFrameLogin login) {
        this.jFrameLogin = login;
        this.jFrameLogin.setVisible(true);
        this.jFrameLogin.addLoingListener(new LoginListener());
        this.jFrameLogin.addLoginKeyListener(new LoginKeyListener());

    }

    class LoginKeyListener extends KeyAdapter {

        public void keyPressed(KeyEvent evt) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                userAccessAccount();
            }
        }
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userAccessAccount();
        }
    }

    private void userAccessAccount() {
        UserAccountDAOImpl userDAOImpl = new UserAccountDAOImpl();
        UserAccount user = new UserAccount();
        user.setUsername(jFrameLogin.getjTextFieldUsername().getText());
        user.setPassword(jFrameLogin.getjPasswordFieldPassword().getText());
        user = userDAOImpl.loginUserAccount(user);
        if (user != null) {
            loginRole(user.getRole());
            jFrameLogin.dispose();
            jFrameAldrinPOS.getjMenuUser().setText(user.getSurname() + ", " + user.getFirstname());
            jFrameAldrinPOS.setUserAccount(user);
            jFrameAldrinPOS.userLogin = user;
            jFrameAldrinPOS.setVisible(true);
        } else {
            jFrameLogin.getjLabelErrorMessage().setText("Please check your username and password and try again.");
            jFrameLogin.getjTextFieldUsername().putClientProperty("JComponent.outline", "error");
            jFrameLogin.getjPasswordFieldPassword().putClientProperty("JComponent.outline", "error");
        }

    }

    private void loginRole(Role r) {
        String role = r.getRole();
        switch (role) {
            case "ADMIN":
                jFrameAldrinPOS.getjMenuManage().setVisible(true);
                jFrameAldrinPOS.getjMenuSetting().setVisible(true);
                jFrameAldrinPOS.getjButtonStockIn().setVisible(true);
                break;
            case "USER":
                jFrameAldrinPOS.getjMenuManage().setVisible(false);
                jFrameAldrinPOS.getjMenuSetting().setVisible(false);
                jFrameAldrinPOS.getjButtonStockIn().setVisible(false);
                break;
            default:
                throw new AssertionError();
        }
    }
}

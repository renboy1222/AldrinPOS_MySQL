/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.model;

import java.sql.Date;
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
public class StockIn {

    private Long id;

    private UserAccount userAccount;

    private Transporter transporter;

//    ALTER TABLE STOCK_IN
//    ALTER COLUMN CREATEDAT   SET DEFAULT CURRENT_TIMESTAMP;
    private Date createdAt;

    java.sql.Timestamp timestamp;

    public StockIn() {

    }

    public StockIn(long id) {
        this.id = id;
    }

    public StockIn(Transporter transporter, UserAccount userAccount) {
        this.transporter = transporter;
        this.userAccount = userAccount;
    }

}

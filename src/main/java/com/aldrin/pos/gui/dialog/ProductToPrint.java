/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.gui.dialog;

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
public class ProductToPrint {

    private String no;
    private String unit;
    private String product;
    private String quantity;
    private String unitPrice;
    private String lineTotal;

    public ProductToPrint(String no, String unit, String product, String quantity, String unitPrice, String lineTotal) {
        this.no = no;
        this.unit = unit;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
    }
}

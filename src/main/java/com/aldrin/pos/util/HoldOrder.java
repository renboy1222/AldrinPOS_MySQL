/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.util;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ALRIN B.C.
 */
@Getter
@Setter
@ToString
public class HoldOrder {
//"STOCK IN ID", "#", "UNIT", "PRODUCT", "QUANTITY", "PRICE", "LINE TOTAL"

    private String stockInId;
    private String unit;
    private String product;
    private String quantity;
    private String priceUf;
    private String linePriceUf;
    private String price;
    private String linePrice;
    private static ArrayList<HoldOrder> holdOrderList;

    public HoldOrder() {

    }

    public HoldOrder(String stockInId, String unit, String product, String quantity, String priceUf, String lineTotalUf,String price, String lineTotal) {
        this.stockInId = stockInId;
        this.unit = unit;
        this.quantity = quantity;
        this.priceUf = priceUf;
        this.linePriceUf = lineTotalUf;
        this.price = price;
        this.linePrice = lineTotal;
        this.product = product;
    }

    /**
     * @return the holdOrderList
     */
    public static ArrayList<HoldOrder> getHoldOrderList() {
        return holdOrderList;
    }

    /**
     * @param aHoldOrderList the holdOrderList to set
     */
    public static void setHoldOrderList(ArrayList<HoldOrder> aHoldOrderList) {
        holdOrderList = aHoldOrderList;
    }
}

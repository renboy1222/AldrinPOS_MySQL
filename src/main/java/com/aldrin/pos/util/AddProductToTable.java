/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.util;

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
public class AddProductToTable {

    private Long stockInEntryId;
    private Long productId;
    private Long unitId;
    private Integer quantity;
    private String product;
    private String unit;
    private String priceBuying;
    private String priceSelling;

    public AddProductToTable(Long stockInEntryId, Long productId, Long unitId, Integer qty, String product, String unit, String priceBuying, String priceSelling) {
        this.stockInEntryId = stockInEntryId;
        this.productId = productId;
        this.unitId = unitId;
        this.unit = unit;
        this.quantity = qty;
        this.product = product;
        this.unit = unit;
        this.priceBuying = priceBuying;
        this.priceSelling = priceSelling;
    }

}

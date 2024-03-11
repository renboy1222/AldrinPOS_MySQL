/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.model;

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
public class StockInEntry {

    private Long id;

    private StockIn stockIn;

    private Product product;

    private Unit unit;

    private Float priceBuying;

    private Float priceSelling;

    private Process process;

    private Integer qty;

    private String user;

    private String transporter;

}

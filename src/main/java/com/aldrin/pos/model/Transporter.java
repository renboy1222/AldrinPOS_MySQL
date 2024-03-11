/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ALDRIN B. C.
 */
@Setter
@Getter
@ToString
public class Transporter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String surname;
    private String email;
    private String mobile;
    private String address;
    private String company;
    private String company_address;
    private byte[] photo;

    public Transporter() {

    }

    public Transporter(Long id, String firstname, String surname, String email, String mobile, String address, String company, String companyAddress, byte[] photo) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.company = company;
        this.company_address = companyAddress;
        this.photo = photo;
    }

}

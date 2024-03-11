/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.pos.model;

import javax.persistence.Entity;
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
public class Unit {
    private Long id;
    private String unit;
    private String description;
    
    public Unit(){
        
    }
    
    public Unit(String unit){
        this.unit =unit;
    }
    
    public Unit(Long id, String unit){
        this.id =id;
        this.unit =unit;
    }
}

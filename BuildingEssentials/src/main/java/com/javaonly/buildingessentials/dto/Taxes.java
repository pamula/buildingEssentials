/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.dto;

import java.math.BigDecimal;

/**
 *
 * @author Prathima
 */
public class Taxes {
    private int taxId;
     private String stateAbbrevation;
    private String stateName;
    private BigDecimal taxRate;

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public String getStateAbbrevation() {
        return stateAbbrevation;
    }

    public void setStateAbbrevation(String stateAbbrevation) {
        this.stateAbbrevation = stateAbbrevation;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    
}

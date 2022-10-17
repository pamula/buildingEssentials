/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.service;

import com.javaonly.buildingessentials.dto.Taxes;
import java.util.List;

/**
 *
 * @author Prathima
 */
public interface TaxServiceInterface {
    Taxes addTax(Taxes tax);
    List<Taxes> getAllTaxes();
    Taxes getOneTax(int taxId );
    void updateTax(Taxes tax);
    void deleteTax(int taxId);
}

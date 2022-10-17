/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.dao;

import com.javaonly.buildingessentials.dto.Taxes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Prathima
 */

@Repository
public class TaxesDaoImpl implements TaxesDaoInterface {

     @Autowired
   JdbcTemplate jdbc;
     
     
     
      @Override
      @Transactional
    public Taxes addTax(Taxes tax) {
        
        final String INSERT_INTO_TAXES = "INSERT INTO tax(stateAbbreviation,stateName,taxRate) VALUES(?,?,?)";
        jdbc.update(INSERT_INTO_TAXES , tax.getStateAbbrevation(),tax.getStateName(),tax.getTaxRate());
        int newTaxId  = jdbc.queryForObject("SELECT LAST_INSERT_ID()",Integer.class);
        tax.setTaxId(newTaxId);
        return tax;
        
    }
    
    @Override
    public List<Taxes> getAllTaxes() {
        final String GET_ALL_TAXES = "SELECT * FROM tax";
        return jdbc.query(GET_ALL_TAXES, new TaxMapper());
         
    }

    @Override
    public Taxes getOneTax(int taxId) {
        try{
        final String GET_ONE_TAX_BY_ID = "SELECT * from tax WHERE taxId=?";
        return jdbc.queryForObject(GET_ONE_TAX_BY_ID,new TaxMapper(), taxId);
        }catch(DataAccessException ex){
            return null;
        }
        
    }

    @Override
    public void updateTax(Taxes tax) {
        final String UPDATE_TAX = "UPDATE tax set stateAbbreviation=?,stateName=?, taxRate=? WHERE taxId=?";
        jdbc.update(UPDATE_TAX, tax.getStateAbbrevation(),
                             tax.getStateName(),tax.getTaxRate(),tax.getTaxId());
        
    }

    @Override
    @Transactional
    public void deleteTax(int taxId) {
        final String DELETE_FROM_ORDERS = "DELETE FROM orders WHERE taxId=?";
        jdbc.update(DELETE_FROM_ORDERS,taxId);
        
        final String DELETE_FROM_TAXES = "DELETE FROM tax WHERE taxId=?";
        jdbc.update(DELETE_FROM_TAXES,taxId);
        
        //To change body of generated methods, choose Tools | Templates.
    }

   
    
    public static final class TaxMapper implements RowMapper<Taxes>{
      
        @Override
        public Taxes mapRow(ResultSet rs, int index) throws SQLException{
            Taxes tax = new Taxes();
            tax.setTaxId(rs.getInt("taxId"));
            tax.setStateAbbrevation(rs.getString("stateAbbreviation"));
            tax.setStateName(rs.getString("stateName"));
            tax.setTaxRate(rs.getBigDecimal("taxRate"));
            return tax;
        }
    }
    
}

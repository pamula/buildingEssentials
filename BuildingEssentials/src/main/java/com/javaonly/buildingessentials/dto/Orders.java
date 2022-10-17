/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaonly.buildingessentials.dto;

import java.util.Objects;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Prathima
 */
public class Orders {
    /*
     MaterialCost = (Area * CostPerSquareFoot)
LaborCost = (Area * LaborCostPerSquareFoot)
totalTax = (MaterialCost + LaborCost) * (TaxRateofstate/100)

TotalCost = (MaterialCost + LaborCost + totalTax)
    */
    private int orderId;
    
    @NotBlank(message = "CustomerName name must not be empty.")
    @Size(max = 25, message = "Customer name must be less than 25 characters.")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]*" , message="name should have only string values")
    private String customerName;
    
    @DateTimeFormat(pattern = "yyyy-mm-dd")
   private LocalDate orderDate;
   // private LocalDate userDate;

    private String state;
     private String productType;
     private int taxId;
     private int productId;
     private Products product;
     private Taxes tax;
    
    //@DecimalMin(value = "0.0", inclusive = false)
   
   @Digits(integer=2, fraction=8, message = "area value out of bound should be of decimal(2,8) ")
   private  BigDecimal area;
   private BigDecimal materialCost;
   private BigDecimal totalLaborCost;
   private BigDecimal totalTaxCost;
   private BigDecimal totalOrderCost;
   
   
    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Taxes getTax() {
        return tax;
    }

    //we can get these from the objects
    public void setTax(Taxes tax) {
        this.tax = tax;
    }

    //private BigDecimal taxValues; //taxrate in that state
    // private BigDecimal costPerSquareFoot;
    // private BigDecimal laborCostPerSquareFoot;
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

   


    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalTaxCost() {
        return totalTaxCost;
    }

    public void setTotalTaxCost(BigDecimal totalTaxCost) {
        this.totalTaxCost = totalTaxCost;
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

    public void setTotalOrderCost(BigDecimal totalOrderCost) {
        this.totalOrderCost = totalOrderCost;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
//    public LocalDate getUserDate() {
//        return userDate;
//    }
//
//    public void setUserDate(LocalDate userDate) {
//        this.userDate = userDate;
//    }
   
}

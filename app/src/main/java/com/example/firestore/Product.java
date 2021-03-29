package com.example.firestore;

public class Product {

    private String productName;
    private String brand;
    private String companyName;
    private String barcode;
    private String date;
    private String unsuitability;

    public Product() {

    }

    public Product(String date, String companyName,String unsuitability, String brand, String barcode, String productName) {
        this.productName = productName;
        this.brand = brand;
        this.companyName = companyName;
        this.barcode = barcode;
        this.date = date;
        this.unsuitability = unsuitability;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnsuitability() {
        return unsuitability;
    }

    public void setUnsuitability(String unsuitability) {
        this.unsuitability = unsuitability;
    }
}

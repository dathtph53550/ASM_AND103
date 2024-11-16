package com.example.asm_and103.Models;

public class ProductModel {
    private String _id;
    private String productName;
    private String Description;
    private double price;
    private String CateID;
    private String image;

    public ProductModel(String _id, String productName, String description, double price, String cateID) {
        this._id = _id;
        this.productName = productName;
        Description = description;
        this.price = price;
        this.CateID = cateID;
    }


    public ProductModel(String _id, String productName, String description, double price, String cateID, String image) {
        this._id = _id;
        this.productName = productName;
        Description = description;
        this.price = price;
        this.CateID = cateID;
        this.image = image;
    }

    public ProductModel() {
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCateID() {
        return CateID;
    }

    public void setCateID(String cateID) {
        CateID = cateID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}

package com.example.asm_and103.Models;

public class CategoryModel {
    private int cateID;
    private String cateName;

    public CategoryModel(int cateID, String cateName) {
        this.cateID = cateID;
        this.cateName = cateName;
    }

    public CategoryModel() {
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}

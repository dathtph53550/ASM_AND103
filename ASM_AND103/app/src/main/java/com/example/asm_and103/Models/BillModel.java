package com.example.asm_and103.Models;

public class BillModel {
    private int billID;
    private String dateBill;
    private String email;

    public BillModel(int billID, String dateBill, String email) {
        this.billID = billID;
        this.dateBill = dateBill;
        this.email = email;
    }

    public BillModel() {
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getDateBill() {
        return dateBill;
    }

    public void setDateBill(String dateBill) {
        this.dateBill = dateBill;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

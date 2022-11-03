package com.example.myapplication22.Database;

public class BankModel {
    private String bank_name,account_holder;
    private int bank_id,account_no,routing_no;

    public BankModel() {
    }

    public BankModel(int bank_id, int account_no, int routing_no, String bank_name, String account_holder) {
        this.bank_id = bank_id;
        this.account_no = account_no;
        this.routing_no = routing_no;
        this.bank_name = bank_name;
        this.account_holder = account_holder;
    }

    public BankModel(int account_no, int routing_no, String bank_name, String account_holder) {
        this.account_no = account_no;
        this.routing_no = routing_no;
        this.bank_name = bank_name;
        this.account_holder = account_holder;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public int getAccount_no() {
        return account_no;
    }

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    public int getRouting_no() {
        return routing_no;
    }

    public void setRouting_no(int routing_no) {
        this.routing_no = routing_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getAccount_holder() {
        return account_holder;
    }

    public void setAccount_holder(String account_holder) {
        this.account_holder = account_holder;
    }
}

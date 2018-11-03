package com.example.davidang.letsgetthisbread;

public class Transaction {
    private String action;
    private double value;

    public Transaction(String a, double v){
        action = a;
        value = v;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAction() {
        return action;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", action, value);
    }
}
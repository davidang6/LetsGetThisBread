package com.example.davidang.letsgetthisbread;

public class Stats {
    private double income;
    private double expenses;
    private double difference;

    public Stats(double i, double e, double d){
        this.income = i;
        this.expenses = e;
        this.difference = d;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public double getIncome() {
        return income;
    }

    public double getExpenses() {
        return expenses;
    }

    public double getDifference() {
        return difference;
    }

    public String toString(){
        return String.format("Income: %d\nExpenses: %d\nTotal Change: %d",income,expenses,difference);
    }
}


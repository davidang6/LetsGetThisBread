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

    /**
     * Gets total gains
     */
    public double getIncome() {
        return income;
    }

    /**
     * Gets total losses
     */
    public double getExpenses() {
        return expenses;
    }

    /**
     * Gets the total change
     */
    public double getDifference() {
        return difference;
    }

    public String toString(){
        return String.format("Income: %s\nExpenses: %s\nNet Change: %s", incomeString(), expensesString(), differenceString());
    }

    public String incomeString(){
        return String.format("$%.2f", income);
    }

    public String expensesString(){
        return expenses == 0 ?  String.format("-$%.2f",expenses) : String.format("-$%.2f",-expenses);
    }

    public String differenceString(){
        return difference >= 0 ? String.format("$%.2f",difference) : String.format("-$%.2f",-difference);
    }


}


package com.shahriar.hasan.monthlyexpenserecorder.data;

/**
 * Created by H. M. Shahriar on 10/18/2017.
 */

public class BudgetData {
    private int id;
    private int month;
    private int year;
    private String description;
    private int category;
    private double amount;

    public BudgetData(int id, int month, int year, String description, int category, double amount) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }

    public BudgetData(int month, int year, String description, int category, double amount) {
        this.month = month;
        this.year = year;
        this.description = description;
        this.category = category;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}

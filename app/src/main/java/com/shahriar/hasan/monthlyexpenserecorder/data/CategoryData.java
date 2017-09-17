package com.shahriar.hasan.monthlyexpenserecorder.data;

/**
 * Created by H. M. Shahriar on 9/17/2017.
 */

public class CategoryData {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    private int id;
    private String name;
    private String description;
    private boolean isIncome;


}

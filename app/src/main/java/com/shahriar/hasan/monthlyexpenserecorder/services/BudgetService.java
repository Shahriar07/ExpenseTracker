package com.shahriar.hasan.monthlyexpenserecorder.services;

import com.shahriar.hasan.monthlyexpenserecorder.dblayer.DBHelper;

/**
 * Created by H. M. Shahriar on 8/6/2017.
 */

public class BudgetService {
    static double budget = 0;
    DBHelper helper = DBHelper.getInstance(null);

    public double getTotalBudget(int month, int year){
        if (budget != 0) return budget;

        BudgetService.budget = 48000;
        return BudgetService.budget;
    }

    public long addBudget(int month, int year, double amount, String description, int categoryId){
        return helper.addBudget(month,year, amount, description,categoryId);
    }
}

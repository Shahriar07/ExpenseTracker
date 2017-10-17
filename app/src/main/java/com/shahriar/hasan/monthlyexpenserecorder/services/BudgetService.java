package com.shahriar.hasan.monthlyexpenserecorder.services;

import com.shahriar.hasan.monthlyexpenserecorder.data.BudgetData;
import com.shahriar.hasan.monthlyexpenserecorder.dblayer.DBHelper;

import java.util.ArrayList;

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

    public long addBudget(BudgetData data){
        return helper.addBudget(data);
    }

    public long addMultipleBudget(ArrayList<BudgetData> budgetList){
        return helper.addMultipleBudget( budgetList);
    }
}

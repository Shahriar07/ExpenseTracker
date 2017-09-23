package com.shahriar.hasan.monthlyexpenserecorder.Contorller;

import com.shahriar.hasan.monthlyexpenserecorder.data.CategoryData;
import com.shahriar.hasan.monthlyexpenserecorder.enums.CategoryTypeEnum;
import com.shahriar.hasan.monthlyexpenserecorder.services.BudgetService;
import com.shahriar.hasan.monthlyexpenserecorder.services.CategoryService;
import com.shahriar.hasan.monthlyexpenserecorder.services.ExpenseService;
import com.shahriar.hasan.monthlyexpenserecorder.services.IncomeService;

import java.util.ArrayList;

/**
 * Created by H. M. Shahriar on 8/6/2017.
 */

public class DashboardController {

    IncomeService incomeService = new IncomeService();
    ExpenseService expenseService = new ExpenseService();
    CategoryService categoryService = new CategoryService();
    BudgetService budgetService = new BudgetService();

    public double getTotalSavings(int month, int year){
        return incomeService.getTotalIncome(month,year) - expenseService.getTotalExpense(month, year);
    }

    public long addNewCategory(String name, String description, CategoryTypeEnum type){
        return categoryService.addCategory(name, description, type);
    }
    public ArrayList<CategoryData> getAllCategory(CategoryTypeEnum type){
        return categoryService.getAllCategory(type);
    }
    public long addBudget(int month, int year, double amount, String description, int categoryId){
        return budgetService.addBudget(month,year, amount, description,categoryId);
    }

    public double getTotalExpense(int month, int year){
        return expenseService.getTotalExpense(month,year);
    }

}

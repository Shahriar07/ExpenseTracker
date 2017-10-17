package com.shahriar.hasan.monthlyexpenserecorder.contorller;

import com.shahriar.hasan.monthlyexpenserecorder.data.BudgetData;
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

    // Delete a category
    public boolean deleteCategory(CategoryData data){
        return categoryService.deleteCategory(data);
    }

    public ArrayList<CategoryData> getAllCategory(CategoryTypeEnum type){
        return categoryService.getAllCategory(type);
    }
    public long addBudget(int month, int year, double amount, String description, int categoryId){
        BudgetData data = new BudgetData(month, year, description, categoryId,amount);
        return budgetService.addBudget(data);
    }

    public double getTotalExpense(int month, int year){
        return expenseService.getTotalExpense(month,year);
    }

}

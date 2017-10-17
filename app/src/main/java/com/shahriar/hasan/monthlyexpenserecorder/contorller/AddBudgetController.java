package com.shahriar.hasan.monthlyexpenserecorder.contorller;

import com.shahriar.hasan.monthlyexpenserecorder.data.BudgetData;
import com.shahriar.hasan.monthlyexpenserecorder.data.CategoryData;
import com.shahriar.hasan.monthlyexpenserecorder.enums.CategoryTypeEnum;
import com.shahriar.hasan.monthlyexpenserecorder.services.BudgetService;
import com.shahriar.hasan.monthlyexpenserecorder.services.CategoryService;

import java.util.ArrayList;

/**
 * Created by H. M. Shahriar on 10/12/2017.
 */

public class AddBudgetController {

    CategoryService categoryService;
    BudgetService budgetService;
    public AddBudgetController(){
        categoryService = new CategoryService();
        budgetService = new BudgetService();
    }

    public ArrayList<CategoryData> getIncomeCategoryList(){
     return  categoryService.getAllCategory(CategoryTypeEnum.INCOME_CATEGORY);
    }

    public ArrayList<CategoryData> getExpenseCategoryList(){
        return  categoryService.getAllCategory(CategoryTypeEnum.EXPENSE_CATEGORY);
    }

    public void addBudget(BudgetData data){
        budgetService.addBudget(data);
    }
}

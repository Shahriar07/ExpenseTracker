package com.shahriar.hasan.monthlyexpenserecorder.services;

/**
 * Created by USER on 7/16/2017.
 */

public class ExpenseService {

    static double expense = 0;

    public double getTotalExpense(int month, int year){
        if (expense != 0) return ExpenseService.expense;

        ExpenseService.expense = 10000;
        return ExpenseService.expense;
    }


}

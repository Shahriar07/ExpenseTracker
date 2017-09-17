package com.shahriar.hasan.monthlyexpenserecorder.services;

/**
 * Created by USER on 8/6/2017.
 */

public class IncomeService {

    static double income = 0;

    public double getTotalIncome(int month, int year){
        if (income != 0) return income;
        IncomeService.income = 50000;
        return IncomeService.income;
    }
}

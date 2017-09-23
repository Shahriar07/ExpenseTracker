package com.shahriar.hasan.monthlyexpenserecorder.enums;

/**
 * Created by H. M. Shahriar on 8/6/2017.
 */

public enum  CategoryTypeEnum {
    INCOME_CATEGORY(1),
    EXPENSE_CATEGORY(0);

    CategoryTypeEnum (int i)
    {
        this.type = i;
    }

    private int type;

    public int getNumericType()
    {
        return type;
    }
}

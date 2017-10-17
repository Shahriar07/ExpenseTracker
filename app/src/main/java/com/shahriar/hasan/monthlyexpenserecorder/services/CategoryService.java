package com.shahriar.hasan.monthlyexpenserecorder.services;

import android.database.Cursor;

import com.shahriar.hasan.monthlyexpenserecorder.data.CategoryData;
import com.shahriar.hasan.monthlyexpenserecorder.dblayer.DBHelper;
import com.shahriar.hasan.monthlyexpenserecorder.enums.CategoryTypeEnum;

import java.util.ArrayList;

import static com.shahriar.hasan.monthlyexpenserecorder.dblayer.DBHelper.CATEGORY_COLUMN_DESCRIPTION;
import static com.shahriar.hasan.monthlyexpenserecorder.dblayer.DBHelper.CATEGORY_COLUMN_ID;
import static com.shahriar.hasan.monthlyexpenserecorder.dblayer.DBHelper.CATEGORY_COLUMN_NAME;
import static com.shahriar.hasan.monthlyexpenserecorder.dblayer.DBHelper.CATEGORY_COLUMN_TYPE;

/**
 * Created by H. M. Shahriar on 8/6/2017.
 */

public class CategoryService {

    DBHelper helper = DBHelper.getInstance(null);

    public long addCategory(String name, String description, CategoryTypeEnum type){
        return helper.addCategory(name,description,type.getNumericType());
    }

    public boolean updateCategory(CategoryData data){
        return helper.updateCategory(data);
    }

    public boolean deleteCategory(CategoryData data){
        return helper.deleteCategory(data);
    }

    public CategoryData getCategoryByName(String categoryName){
        CategoryData categoryData = new CategoryData();
        Cursor res = helper.getCategoryByName(categoryName);
        while(res != null && res.isAfterLast() == false){
            categoryData.setId(res.getInt(res.getColumnIndex(CATEGORY_COLUMN_ID)));
            categoryData.setName(res.getString(res.getColumnIndex(CATEGORY_COLUMN_NAME)));
            categoryData.setDescription(res.getString(res.getColumnIndex(CATEGORY_COLUMN_DESCRIPTION)));
            categoryData.setIncome(res.getInt(res.getColumnIndex(CATEGORY_COLUMN_TYPE))==1?CategoryTypeEnum.INCOME_CATEGORY:CategoryTypeEnum.EXPENSE_CATEGORY);
            res.moveToNext();
            break;
        }
        return categoryData;
    }

    public ArrayList<CategoryData> getAllCategory(CategoryTypeEnum type){
        ArrayList<CategoryData> categoryDatas = new ArrayList<CategoryData>();
        Cursor res = helper.getAllCategory(type.getNumericType());
        while(res != null && res.isAfterLast() == false){
            CategoryData categoryData = new CategoryData();
            categoryData.setId(res.getInt(res.getColumnIndex(CATEGORY_COLUMN_ID)));
            categoryData.setName(res.getString(res.getColumnIndex(CATEGORY_COLUMN_NAME)));
            categoryData.setDescription(res.getString(res.getColumnIndex(CATEGORY_COLUMN_DESCRIPTION)));
            categoryData.setIncome(res.getInt(res.getColumnIndex(CATEGORY_COLUMN_TYPE))==1?CategoryTypeEnum.INCOME_CATEGORY:CategoryTypeEnum.EXPENSE_CATEGORY);
            res.moveToNext();
            categoryDatas.add(categoryData);
        }
        return categoryDatas;
    }
}

package com.shahriar.hasan.monthlyexpenserecorder.services;

import android.database.Cursor;

import com.shahriar.hasan.monthlyexpenserecorder.data.CategoryData;
import com.shahriar.hasan.monthlyexpenserecorder.dblayer.DBHelper;
import com.shahriar.hasan.monthlyexpenserecorder.enums.CategoryTypeEnum;

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
        return helper.addCategory(name,description,type);
    }

    public boolean updateCategory(CategoryData data){
        return helper.updateCategory(data);
    }

    public CategoryData getCategoryByName(String categoryName){
        CategoryData categoryData = new CategoryData();
        Cursor res = helper.getCategoryByName(categoryName);
        while(res.isAfterLast() == false){
            categoryData.setId(res.getInt(res.getColumnIndex(CATEGORY_COLUMN_ID)));
            categoryData.setName(res.getString(res.getColumnIndex(CATEGORY_COLUMN_NAME)));
            categoryData.setDescription(res.getString(res.getColumnIndex(CATEGORY_COLUMN_DESCRIPTION)));
            categoryData.setIncome(res.getInt(res.getColumnIndex(CATEGORY_COLUMN_TYPE))==1?true:false);
            res.moveToNext();
            break;
        }
        return categoryData;
    }
}

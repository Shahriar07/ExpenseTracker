package com.shahriar.hasan.monthlyexpenserecorder.dblayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shahriar.hasan.monthlyexpenserecorder.data.CategoryData;
import com.shahriar.hasan.monthlyexpenserecorder.enums.CategoryTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by USER on 7/16/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Budget.db";
    private static final String EXPENSE_TABLE_NAME = "expense";
    private static final String EXPENSE_COLUMN_ID = "id";
    private static final String EXPENSE_COLUMN_DATE = "date";
    private static final String EXPENSE_COLUMN_MONTH = "month";
    public static final String EXPENSE_COLUMN_YEAR = "year";
    public static final String EXPENSE_COLUMN_AMOUNT = "amount";
    private static final String EXPENSE_COLUMN_DESCRIPTION = "description";
    private static final String EXPENSE_COLUMN_CATEGORY = "category_id";


    private static final String CATEGORY_TABLE_NAME = "category";
    public static final String CATEGORY_COLUMN_ID = "id";
    public static final String CATEGORY_COLUMN_NAME = "name";
    public static final String CATEGORY_COLUMN_DESCRIPTION = "description";
    public static final String CATEGORY_COLUMN_TYPE = "type"; // income = 1 or expense = 0

    private static final String INCOME_TABLE_NAME = "income";
    private static final String INCOME_COLUMN_ID = "id";
    private static final String INCOME_COLUMN_DESCRIPTION = "description";
    private static final String INCOME_COLUMN_CATEGORY = "category_id";
    private static final String INCOME_COLUMN_MONTH = "month";
    private static final String INCOME_COLUMN_YEAR = "year";
    private static final String INCOME_COLUMN_DATE = "date";
    private static final String INCOME_COLUMN_AMOUNT = "amount";

    private static final String BUDGET_TABLE_NAME = "budget";
    private static final String BUDGET_COLUMN_ID = "id";
    private static final String BUDGET_COLUMN_CATEGORY = "category_id";
    private static final String BUDGET_COLUMN_DESCRIPTION = "description";
    private static final String BUDGET_COLUMN_MONTH = "month";
    private static final String BUDGET_COLUMN_YEAR = "year";
    private static final String BUDGET_COLUMN_AMOUNT = "amount";


    private HashMap hp;

    private DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    private static DBHelper instance;

    public  static DBHelper getInstance(Context context){
        synchronized (DBHelper.class){
            if (instance == null){
                instance = new DBHelper(context);
            }
            return instance;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table expense " +
                        "(id integer primary key, date long, month int, year int, amount double,description text, category_id int)"
        );
        db.execSQL(
                "create table " + CATEGORY_TABLE_NAME + " " +
                        "(id integer primary key, " + CATEGORY_COLUMN_NAME + " text, " + CATEGORY_COLUMN_DESCRIPTION + " text, " + CATEGORY_COLUMN_TYPE + " int)"
        );
        db.execSQL(
                "create table income " +
                        "(id integer primary key, description text, date long, month int, year int, amount double, category_id int)"
        );
        db.execSQL(
                "create table budget " +
                        "(id integer primary key, month int, year int, description text, amount double, category_id int)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS expense");
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS income");
        db.execSQL("DROP TABLE IF EXISTS budget");
        onCreate(db);
    }

    public long addExpense (long date, int month, int year, double amount,String description, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("amount", amount);
        contentValues.put("description", description);
        contentValues.put("category_id", categoryId);
        long id = db.insert("expense", null, contentValues);
        return id;
    }

    public long addCategory (String name, String description, CategoryTypeEnum type) {
        int catType = 1; //income:1 expense:0
        Cursor res = getCategoryByName(name);
        if (res != null && res.getCount() > 0){
            return 0;
        }
        //TODO: check if category already exist. If exist return error.
        if (CategoryTypeEnum.EXPENSE_CATEGORY == type) catType = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("type", catType);
        long id = db.insert("category", null, contentValues);
        return id;
    }

    public boolean updateCategory (CategoryData data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", data.getName());
        contentValues.put("description", data.getDescription());
        contentValues.put("type", data.isIncome());
        int i = db.update("category", contentValues, "id = ? ", new String[] { Integer.toString(data.getId()) } );
        return (i>0)?true:false;
    }

    public long addIncome (long date, int month, int year, double amount,String description, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("amount", amount);
        contentValues.put("description", description);
        contentValues.put("category_id", categoryId);
        long id = db.insert("income", null, contentValues);
        return id;
    }

    public long addBudget (int month, int year, double amount,String description, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("amount", amount);
        contentValues.put("description", description);
        contentValues.put("category_id", categoryId);
        long id = db.insert("expense", null, contentValues);
        return id;
    }


    public boolean updateExpense (int id,long date, int month, int year, double amount,String description, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("amount", amount);
        contentValues.put("description", description);
        contentValues.put("category_id", categoryId);
        int i = db.update("expense", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return (i>0)?true:false;
    }

        public boolean updateIncome (int id, long date, int month, int year, double amount,String description, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("amount", amount);
        contentValues.put("description", description);
        contentValues.put("category_id", categoryId);
        int i = db.update("income", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return (i>0)?true:false;
    }

    public boolean updateBudget (int id, int month, int year, double amount,String description, int categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("amount", amount);
        contentValues.put("description", description);
        contentValues.put("category_id", categoryId);
        int i = db.update("budget", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return (i>0)?true:false;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from expense where id="+id+"", null );
        return res;
    }

    private int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, EXPENSE_TABLE_NAME);
        return numRows;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("expense",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from expense", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(EXPENSE_COLUMN_AMOUNT)));
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getCategoryByName(String categoryName) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor res = db.rawQuery("select * FROM " + CATEGORY_TABLE_NAME + " WHERE " + CATEGORY_COLUMN_NAME + "= ?", new String[]{categoryName});
            res.moveToFirst();
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
package com.shahriar.hasan.monthlyexpenserecorder.views;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.shahriar.hasan.monthlyexpenserecorder.R;
import com.shahriar.hasan.monthlyexpenserecorder.contorller.AddBudgetController;
import com.shahriar.hasan.monthlyexpenserecorder.data.BudgetData;
import com.shahriar.hasan.monthlyexpenserecorder.data.CategoryData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by H. M. Shahriar on 9/24/2017.
 */

public class AddBudgetActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, View.OnClickListener{
    DatePickerDialog datePickerDialog;
    private Date date;
    private EditText budgetMonth;
    private String TAG;
    private int year;
    private int month;
    AddBudgetController controller = new AddBudgetController();

    ArrayList<CategoryData> incomeCategoryList = new ArrayList<CategoryData>();
    ArrayList<CategoryData> expenseCategoryList = new ArrayList<CategoryData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_budget_activity);
        TAG = this.getLocalClassName();
        budgetMonth = (EditText) findViewById(R.id.budgetMonth);
        budgetMonth.setOnClickListener(this);
        year = 1970;
        month = 0;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveBudget);
        if(fab!= null){
            fab.setOnClickListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        incomeCategoryList = controller.getIncomeCategoryList();
        expenseCategoryList = controller.getExpenseCategoryList();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        Log.i(TAG, "Year " + year + " Month " + monthOfYear);
    }
    void showDatePicker(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        Log.d(TAG,"Year "+year +" Month "+month + " Day "+day);
        datePickerDialog = new DatePickerDialog(
                this, AddBudgetActivity.this, year , month, day);
        datePickerDialog.show();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.budgetMonth){
            showDatePicker();
        }
        if(v.getId() == R.id.saveBudget){
            Snackbar.make(v, "Save budget clicked", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            BudgetData data = new BudgetData(11,2017,"Transport", 1, 10000);
            controller.addBudget(data);
        }
    }
}

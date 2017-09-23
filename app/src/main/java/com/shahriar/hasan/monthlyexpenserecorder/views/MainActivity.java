package com.shahriar.hasan.monthlyexpenserecorder.views;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shahriar.hasan.monthlyexpenserecorder.Contorller.DashboardController;
import com.shahriar.hasan.monthlyexpenserecorder.Dialogs.AddCategoryDialog;
import com.shahriar.hasan.monthlyexpenserecorder.R;
import com.shahriar.hasan.monthlyexpenserecorder.dblayer.DBHelper;
import com.shahriar.hasan.monthlyexpenserecorder.enums.CategoryTypeEnum;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DashboardController controller;
    private Button addExpense;
    private Button addIncome;
    private Button addCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the database
        DBHelper.getInstance(this.getApplicationContext());
        controller = new DashboardController();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        if(fab!= null){
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });
//        }


        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(uiOptions);

        final ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setHomeButtonEnabled(false); // disable the button
//            ab.setDisplayHomeAsUpEnabled(true); // remove the left caret
//            ab.setDisplayShowHomeEnabled(true); // remove the icon
//        }

//        //ab.setHomeAsUpIndicator(android.R.drawable.title_bar); // set a custom icon for the default home button
////        ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
//        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
//        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
//        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
        System.out.println("Total height "+getSoftButtonsBarSizePort(this));
        initializeView();
    }

    private void initializeView(){
        addExpense = (Button) findViewById(R.id.addExpense);
        addIncome = (Button)findViewById(R.id.addIncome);
        addCategory = (Button)findViewById(R.id.addCategory);
        addCategory.setOnClickListener(this);
        addIncome.setOnClickListener(this);
        addExpense.setOnClickListener(this);
    }


    public static int getSoftButtonsBarSizePort(Activity activity) {
        // getRealMetrics is only available with API 17 and +
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addBudget();
        TextView totalExpense  = (TextView) findViewById(R.id.totalExpenseAmount);
        totalExpense.setText(controller.getTotalExpense(7,17) + " TK");
    }

    private  void addCategory(){
        if(controller.addNewCategory("Transportation", "Transport", CategoryTypeEnum.EXPENSE_CATEGORY)!=0){
            System.out.println("Category added");
        }
        else {
            System.out.println("Error in category add");
        }
    }

    private  void addBudget(){
        if(controller.addBudget(8, 17, 1000, "Budget for transaction", 1)!=0){
            System.out.println("Budget added");
        }
        else {
            System.out.println("Error in budget add");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == addExpense){
            // addExpense
            Toast.makeText(this, getText(R.string.add_expense),Toast.LENGTH_SHORT).show();
        }
        if (v == addIncome){
            // addIncome
            Toast.makeText(this, getText(R.string.add_income),Toast.LENGTH_SHORT).show();
        }
        if (v == addCategory){
            // addCategory
            System.out.println("Category added ");
            DialogFragment dialog = new AddCategoryDialog();
            dialog.show(MainActivity.this.getFragmentManager(), "AddCategoryDialog");
            //Toast.makeText(this, getText(R.string.add_category),Toast.LENGTH_SHORT).show();
        }
    }
}

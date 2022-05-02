package com.example.feedbacksystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class MenuActivity extends AppCompatActivity {
    /*TextView tv_roll,tv_sem,tv_dept;
    Button logout_btn;*/
    DrawerLayout drawerLayout;
    TextView textView;
    CardView card1,card2,card3,card4;
        
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        drawerLayout = findViewById(R.id.drawer_layout);
        textView = findViewById(R.id.textview_rollno);
        User user = SharedPrefManager.getInstance(this).getUser();
        String string = "Roll No. : "+user.getRoll_no();
        textView.setText(string);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        //First card item
       card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TeachersList.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"First Item Clicked",Toast.LENGTH_SHORT).show();

            }
        });
        //Second card item
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Second Item Clicked",Toast.LENGTH_SHORT).show();

            }
        });
        //Third card item
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Third Item Clicked",Toast.LENGTH_SHORT).show();

            }
        });
        //Fourth card item
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Fourth Item Clicked",Toast.LENGTH_SHORT).show();

            }
        });

        /*tv_roll = (TextView) findViewById(R.id.textview_rollno);
        tv_sem=(TextView) findViewById(R.id.textview_semester);
        tv_dept=(TextView) findViewById(R.id.textview_dept);
        logout_btn=(Button) findViewById(R.id.logout_btn);
        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        //Getting the details of logged in user from SharedPrefManager
        User user = SharedPrefManager.getInstance(this).getUser();
        tv_roll.setText(user.getRoll_no());
        String semester_string = "Semester - "+user.getSemester();
        tv_sem.setText(semester_string);
        String dept_string = "Department - "+user.getDepartment();
        tv_dept.setText(dept_string);

        //If user presses the logout button
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).userLogout();
            }
        });
         */


    }
    // Function for opening drawer
    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }
    public static void openDrawer(DrawerLayout drawerLayout){
        //Open Drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        //CloseDrawer
        closeDrawer(drawerLayout);
    }
    public static void closeDrawer(DrawerLayout drawerLayout){
        //Close Drawer
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When Drawer in Open close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        //Recreate Activity
        recreate();
    }
    public void ClickAnonymousMessage(View view){
        redirectActivity(this,AnonymousMessageActivity.class);

    }
    public void ClickChangePassword(View view){
        redirectActivity(this,ChangePasswordActivity.class);
    }
    public void ClickLogout(View view){
        logout(this);
    }
    public static void logout(Activity activity){

        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             /*   activity.finishAffinity();
                System.exit(0);*/
                SharedPrefManager.getInstance(activity.getApplicationContext()).userLogout(activity);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
    public static void redirectActivity(Activity activity,Class aClass){
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}

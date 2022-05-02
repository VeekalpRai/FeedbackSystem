package com.example.feedbacksystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/*When the user registers or log in we will create a login session using SharedPreferences.
So to make it simple we will create a separate class that will handle the SharedPreferences operations. */

public class SharedPrefManager {
    //Declarations of constants
    private static final String SHARED_PERF_NAME = "sharedpref";
    private static final String KEY_ROLLNO = "keyrollnumber";
    private static final String KEY_SEMESTER = "keysemester";
    private static final String KEY_ID = "keyid";
    private static final String KEY_DEPARTMENT = "keydepartment";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    //Constructor
    public SharedPrefManager(Context context){
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance==null){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    //Method for login
    //This method will store the user data in shared preference manager
    public void userLogin(User user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PERF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID,user.getId());
        editor.putString(KEY_ROLLNO, user.getRoll_no());
        editor.putString(KEY_SEMESTER,user.getSemester());
        editor.putString(KEY_DEPARTMENT, user.getDepartment());
        editor.apply();
    }

    //This method will check whether the user is already logged in or not
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PERF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ROLLNO,null)!=null;
    }
    //This method will return the data of user that is logged in
    public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PERF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID,-1),
                sharedPreferences.getString(KEY_ROLLNO,null),
                sharedPreferences.getString(KEY_SEMESTER,null),
                sharedPreferences.getString(KEY_DEPARTMENT,null)
        );
    }

    //Method for logout
    public void userLogout(Activity activity){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PERF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(mCtx,MainActivity.class);

        mCtx.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP));

     //   mCtx.startActivity(new Intent(mCtx,MainActivity.class));

        activity.finish();
    }
}

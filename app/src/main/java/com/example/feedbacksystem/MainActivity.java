package com.example.feedbacksystem;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText editTextRollno,editTextPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextRollno = (EditText) findViewById(R.id.roll_no_et);
        editTextPassword=(EditText) findViewById(R.id.password_et);
        //If user is already logged in
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MenuActivity.class));
            return;
        }
       //If user presses the login button
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Calling the login function
                userLogin();
            }
        });



    }
    public void RollError(){
        editTextRollno.setError("Please enter your roll number");
        editTextRollno.requestFocus();
    }
    public void userLogin(){
        final Custom_Dialog dialog = new Custom_Dialog(MainActivity.this);

        //first getting the values
        final String roll_no = editTextRollno.getText().toString();
        final String password = editTextPassword.getText().toString();
        //validating inputs
        if (TextUtils.isEmpty(roll_no)) {
            editTextRollno.setError("Please enter your roll number");
            editTextRollno.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        //If inputs are valid
        class UserLogin extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("roll_no"),
                                userJson.getString("semester"),
                                userJson.getString("department")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequsetHandler requestHandler = new RequsetHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("roll_no", roll_no);
                params.put("password", password);

                //returning the response
                return requestHandler.sendPostRequest(URLs.LOGIN_URL, params);
            }
        }
        UserLogin ul = new UserLogin();
        ul.execute();
    }
}

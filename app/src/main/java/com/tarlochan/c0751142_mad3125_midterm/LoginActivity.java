package com.tarlochan.c0751142_mad3125_midterm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.tarlochan.c0751142_mad3125_midterm.DataBase.DBUser;
import com.tarlochan.c0751142_mad3125_midterm.DataBase.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    TextInputEditText edtEmail;
    @BindView(R.id.edtPassword)
    TextInputEditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.switchRememberMe)
    Switch switchRememberMe;

    Map<String,String > usersMapList = new HashMap<>();
    SharedPreferences mSharedpreferences;
    SharedPreferences.Editor mEditor;
    ArrayList<User> mUsersArrayList;
    private DBUser mDBUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mDBUser = new DBUser(this);
        mUsersArrayList = mDBUser.getAllUsers();
        if (mUsersArrayList.isEmpty())
        {
            loadUserIntoDB();
            Log.d("USER ----->>>>","Users have been Loaded");

        }
        else
        {
            Log.d("Total Users -->>>>>>",String.valueOf(mUsersArrayList.size()));
            for(User u : mUsersArrayList)
            {
                Log.d("USER", u.toString());
            }
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        convertListToMap();
        //usersMapList.put("admin@admin.com","admin"); // key , value
        //usersMapList.put("test@test.com","test");
        if(switchRememberMe.isChecked() == true)
        {
            getRememberMe();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if(edtEmail.getText().toString().isEmpty() || edtEmail.getText().toString().trim().length() == 0)
                {
                    edtEmail.setError("Enter Email : ");
                    if(edtPassword.getText().toString().isEmpty() || edtPassword.getText().toString().trim().length() == 0)
                    {
                        edtPassword.setError("Enter Password : ");
                    }
                }
                else
                {
                    if(validateEmail(email))
                    {
                        if (usersMapList.containsKey(email))
                        {
                            //Valid email of user
                            if (usersMapList.containsValue(password))
                            {
                                //password matches so User confirmed
                                //Toast.makeText(LoginActivity.this, "Correct Details Entered", Toast.LENGTH_SHORT).show();
                                if(switchRememberMe.isChecked() == true)
                                {
                                    saveRememeberMe();
                                }
                                else
                                {
                                    saveRememeberMeEmpty();
                                }
                                showAlertBox();
                            }
                            else
                            {
                                //Password is incorrect
                                edtPassword.setError("Password is Incorrect");

                            }
                        }
                        else
                        {
                            //No User with this Email in DataBase
                            edtEmail.setError("No User with this Email in DataBase");
                        }
                    }
                    else
                    {
                        edtEmail.setError("Not Valid Email Address");
                    }
                }
            }
        });



    }
//Remember Me to save
    private void saveRememeberMe()
    {
        mSharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        mEditor = mSharedpreferences.edit();
        mEditor.putString("email", edtEmail.getText().toString());
        mEditor.putString("password",edtPassword.getText().toString());
        mEditor.commit();
    }
    //Remember Me Empty code
    private void saveRememeberMeEmpty()
    {
        mSharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        mEditor = mSharedpreferences.edit();
        mEditor.putString("email", "");
        mEditor.putString("password","");
        mEditor.commit();
    }

    // to get back remember me values
    private void getRememberMe()
    {
        mSharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        mEditor = mSharedpreferences.edit();
        String email = mSharedpreferences.getString("email", "");
        String password = mSharedpreferences.getString("password","");
        edtEmail.setText(email);
        edtPassword.setText(password);
    }
//To validate email
    public Boolean validateEmail(String email) {

        String regex = "^[a-z0-9A-Z\\.]*@[a-z0-9A-Z]*\\.[a-zA-Z]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //load some static users into DB for first time
    public void loadUserIntoDB()
    {
        User user1 = new User(1,"admin@admin.com","admin");
        mDBUser.insert(user1);
        User user2 = new User(2,"test@test.com","test");
        mDBUser.insert(user2);
        User user3 = new User(3,"tar@tar.com","tar");
        mDBUser.insert(user3);
        User user4 = new User(4,"abc@abc.com","abc");
        mDBUser.insert(user4);
        User user5 = new User(5,"xyz@xyz.com","xyz");
        mDBUser.insert(user5);
    }
//Convert List of users into Map so that user email and password is easily checked by key value pair
    private void convertListToMap()
    {
        for(User u : mUsersArrayList)
        {
            usersMapList.put(u.getEmail(),u.getPassword());
        }
    }

    private void showAlertBox()
    {
        //Step 1 create alert Builder
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Correct Details Entered");
        //alertDialog.setIcon(R.drawable.ic_smile);
        alertDialog.setMessage("Would You Like To Continue ? ");
        alertDialog.setCancelable(false); // outside click will not hide the dialog box
        //Step 2 Add Positive Button
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(HomeActivity.this, "Clicked on Ok", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //Add Negative Button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(HomeActivity.this, "Clicked on Cancel", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,SplashActivity.class);
                startActivity(intent);
            }
        });
        //Add Neutral Button
        alertDialog.setNeutralButton("Ignore", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(HomeActivity.this, "Clicked on Ignore", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }
}

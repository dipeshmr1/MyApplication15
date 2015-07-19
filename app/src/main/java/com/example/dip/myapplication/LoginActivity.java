package com.example.dip.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends Activity {
    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mLoginBtn;
    protected Button mCreateAccountBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initialize
        mUsername = (EditText) findViewById(R.id.usernameLoginTextBox);
        mPassword = (EditText) findViewById(R.id.passwordLoginTextBox);
        mLoginBtn = (Button)findViewById(R.id.LoginBtn);
        mCreateAccountBtn= (Button)findViewById(R.id.createAccountbtnLogin);

        //listen to login when login is clicked

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the user input from edit and convert to string
                String username= mUsername.getText().toString().trim();
                String password= mPassword.getText().toString().trim();
                //login the user using parse sdk
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (e == null) {
                            //success user log in
                            Toast.makeText(LoginActivity.this, "welcome back!", Toast.LENGTH_LONG).show();
                            //take user to the homepage
                            Intent takeUserHome = new Intent(LoginActivity.this, HomepageActivity.class);
                            startActivity(takeUserHome);
                        } else {
                            //sorry error user log in
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Sorry");
                            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //close the dialog
                                    dialog.dismiss();


                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();

                        }

                    }

                    {


                    }
                });


            }
        });


    }



}

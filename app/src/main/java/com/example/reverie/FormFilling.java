package com.example.reverie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormFilling extends AppCompatActivity {

    Db_helper db;

    EditText email, password;
    Button LogIn;
    Button SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form_filling);


        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);

        LogIn = (Button) findViewById(R.id.Logbutton);
        SignUp = (Button) findViewById(R.id.signbutton);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = email.getText().toString();
                String s2 = password.getText().toString();

                if (s1.equals("") || s2.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();
                }
                else{
                    Boolean checked = db.checkmail(s1);
                    Boolean checkp = db.checkpass(s2);
                    if (checked == true) {
                        if (checkp == true) {
                            Toast.makeText(getApplicationContext(), "Registered User", Toast.LENGTH_LONG).show();
                            Intent homepagemove = new Intent(FormFilling.this, Homepage.class);
                            startActivity(homepagemove);
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                            Toast.makeText(getApplicationContext(), "User not signed in", Toast.LENGTH_LONG).show();
                            Intent signupmove = new Intent(FormFilling.this, SignUpForm.class);
                            startActivity(signupmove);
                        }

                }
            }


        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(FormFilling.this,SignUpForm.class);
                startActivity(signup);
            }
        });
    }

}

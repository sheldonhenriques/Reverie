package com.example.reverie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Attributes;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class SignUpForm extends AppCompatActivity {

    Db_helper db;

    Button Capture;
    ImageView Image;


    EditText Name_input, Email_input, Password_input, ConfirmPassword_input;
    Button SignUp_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);

        db = new Db_helper(this);

        Capture = (Button) findViewById(R.id.cap);

        Image = (ImageView) findViewById(R.id.imagedp);

        Capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

        Name_input = (EditText) findViewById(R.id.Name);
        Email_input = (EditText) findViewById(R.id.EmailId);
        Password_input = (EditText) findViewById(R.id.Password);
        ConfirmPassword_input = (EditText) findViewById(R.id.ConfirmPassword);
        SignUp_Button = (Button) findViewById(R.id.SignUp);

        SignUp_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = Name_input.getText().toString();
                String s2 = Email_input.getText().toString();
                String s3 = Password_input.getText().toString();
                String s4 = ConfirmPassword_input.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_LONG).show();
                }
                else {
                    if(s3.equals(s4)){
                        Boolean checked = db.checkmail(s2);
                        if(checked==true){
                            Boolean insert = db.insert(s1,s2,s3);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                Intent home = new Intent(SignUpForm.this, Homepage.class);
                                startActivity(home);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_LONG).show();
                            Intent form = new Intent(SignUpForm.this, FormFilling.class);
                            startActivity(form);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        Image.setImageBitmap(bitmap);

    }

}

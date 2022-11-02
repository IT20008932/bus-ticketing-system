package com.csse.busticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    Button signupScreen, loginBtn;
    TextView loginText;
    ImageView loginImage;
    TextInputLayout username, password;
    TextInputEditText editTextUsername, editTextPassword;

    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        signupScreen = findViewById(R.id.signup_screen);
        loginText = findViewById(R.id.login_name);
        loginImage = findViewById(R.id.login_image);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> userDetails = dbHelper.LoginUser(editTextUsername.getText().toString(), editTextPassword.getText().toString());

                if (userDetails.size() != 0) {
                    User user = userDetails.get(0);
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"Invalid UserName or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this,RegisterScreen.class);

                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair(loginText,"splash_text");
                pairs[1] = new Pair(loginImage,"splash_image");
                pairs[2] = new Pair(username,"username_tran");
                pairs[3] = new Pair(password,"password_tran");
                pairs[4] = new Pair(loginBtn,"button_tran");
                pairs[5] = new Pair(signupScreen,"login_signup_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginScreen.this,pairs);
                startActivity(intent,options.toBundle());
                finish();
            }
        });
    }
}
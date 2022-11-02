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

public class RegisterScreen extends AppCompatActivity {

    Button signInScreen, registerBtn;
    TextView signupText;
    ImageView signupImage;
    TextInputLayout username, confirmPassword;
    TextInputEditText editUsername, editEmail, editPassword, editConfirmPassword;

    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        signInScreen = findViewById(R.id.signIn_screen);
        registerBtn = findViewById(R.id.register_btn);
        signupText = findViewById(R.id.signup_name);
        signupImage = findViewById(R.id.signup_image);
        username = findViewById(R.id.username);
        confirmPassword = findViewById(R.id.confirm_password);
        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editUsername.getText().toString().isEmpty() || editEmail.getText().toString().isEmpty() || editPassword.getText().toString().isEmpty() || editConfirmPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"All Fields Are Required",Toast.LENGTH_LONG).show();
                }
                else if (!editPassword.getText().toString().equals(editConfirmPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Password Not Match",Toast.LENGTH_LONG).show();
                }
                else {
                    User user = new User(editUsername.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                    if (dbHelper.RegisterUser(user)) {
                        Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterScreen.this,LoginScreen.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"User Registration Failed",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signInScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterScreen.this,LoginScreen.class);

                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair(signupText,"splash_text");
                pairs[1] = new Pair(signupImage,"splash_image");
                pairs[2] = new Pair(username,"username_tran");
                pairs[3] = new Pair(confirmPassword,"password_tran");
                pairs[4] = new Pair(registerBtn,"button_tran");
                pairs[5] = new Pair(signInScreen,"login_signup_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterScreen.this,pairs);
                startActivity(intent,options.toBundle());
                finish();
            }
        });

    }

}
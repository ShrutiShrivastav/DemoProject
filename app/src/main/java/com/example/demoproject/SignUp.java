package com.example.demoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    private EditText etName;
    private EditText etMobile;
    private EditText etEmail;
    private EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.name);
        etMobile = findViewById(R.id.mobile);
        etEmail = findViewById(R.id.email);
        etPass = findViewById(R.id.pass);
    }

    //EMAIL VALIDATION
    private boolean validateEmail() {
        String emailInput = etEmail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            etEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            etEmail.setError("Please enter a valid email address");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }
    //NAME VALIDATION
    private boolean validateUsername() {
        String usernameInput = etName.getText().toString().trim();
        if (usernameInput.isEmpty()) {
            etName.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            etName.setError("Username too long");
            return false;
        } else {
            etName.setError(null);
            return true;
        }
    }

    //PASSWORD VALIDATION
    private boolean validatePassword() {
        String passwordInput = etPass.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            etPass.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            etPass.setError("Password too weak");
            return false;
        } else {
            etPass.setError(null);
            return true;
        }
    }
    public void confirmInput(View v) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }
        String input = "Username: " + etName.getText().toString();
        input += "\n";
        input += "Email: " + etEmail.getText().toString();
        input += "\n";
        input += "Password: " + etPass.getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}

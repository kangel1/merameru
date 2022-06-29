package com.example.merameru1.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.merameru1.R;


public class register extends AppCompatActivity {
    private final AppCompatActivity activity = register.this;
    private Button btn_reg;
    private EditText text_user, text_email, text_repassw, text_passw;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref", Name = "nameKey", Email = "emailKey";

    private boolean validate() {
        boolean valid = false;

        String UserName = text_user.getText().toString();
        String Email = text_email.getText().toString();
        String Password = text_passw.getText().toString();
        String RePassword = text_repassw.getText().toString();

        if (UserName.isEmpty()) {
            valid = false;
            text_user.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 0) {
                valid = true;
                text_user.setError(null);
            } else {
                valid = false;
                text_user.setError("Username is to short!");
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            text_email.setError("Please enter valid email!");
        } else {
            valid = true;
            text_email.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            text_passw.setError("Please enter valid password!");
        } else {
            if (Password.length() >= 6) {
                valid = true;
                text_passw.setError(null);
            } else {
                valid = false;
                text_passw.setError("Password is to short!");
            }
        }

        if (RePassword.isEmpty()) {
            valid = false;
            text_repassw.setError("Please enter re password!");
        } else {
            if (Password.toString().equals(RePassword)) {
                valid = true;
                text_repassw.setError(null);
            } else {
                valid = false;
                text_repassw.setError("Password not same!");
            }
        }

        return valid;
    }

    private void emptyInputEditText() {
        text_user.setText(null);
        text_email.setText(null);
        text_passw.setText(null);
        text_repassw.setText(null);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), com.example.merameru1.ui.login.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void goToLogin(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        btn_reg = findViewById(R.id.btn_register);
        text_user = findViewById(R.id.username);
        text_email = findViewById(R.id.email);
        text_passw = findViewById(R.id.pass);
        text_repassw = findViewById(R.id.passCopy);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            text_user.setText(sharedpreferences.getString(Name, " "));
        }
        if (sharedpreferences.contains(Email)) {
            text_email.setText(sharedpreferences.getString(Email, " "));
        }

        emptyInputEditText();

        btn_reg.setOnClickListener(v -> goToLogin());
    }
}
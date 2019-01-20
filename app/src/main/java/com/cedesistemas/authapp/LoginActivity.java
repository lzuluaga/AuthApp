package com.cedesistemas.authapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cedesistemas.authapp.helper.ValidateInternet;
import com.cedesistemas.authapp.models.User;
import com.cedesistemas.authapp.services.Repository;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUser;
    private EditText editTextPass;
    private Button buttonLogIn;
    private ValidateInternet validateInternet;
    private Repository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        validateInternet = new ValidateInternet(this);
        repository = new Repository();
        editTextUser = findViewById(R.id.login_etUser);
        editTextPass = findViewById(R.id.login_etPassword);
        buttonLogIn = findViewById(R.id.login_btnLogin);
    }

    public void login(View view) {
        if (validateInternet.isConnected()){
            createThreadToLogIn();
        }else {
            // toast o alert dialog o snackbar
        }
    }

    private void createThreadToLogIn() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                logInRepository();
            }
        });
        thread.start();
    }

    private void logInRepository() {
        try {
            User user = repository.logIn(editTextUser.getText().toString(), editTextPass.getText().toString());
            showToast(user.getName());
        } catch (IOException e) {
            showToast(e.getMessage());
        }
    }

    private void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}


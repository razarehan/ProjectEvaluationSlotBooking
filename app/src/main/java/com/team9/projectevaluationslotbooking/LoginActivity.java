package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private EditText user, pass;
    private FirebaseAuth fAuth;
    private ProgressBar pgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Student/Teacher LOGIN");
        fAuth = FirebaseAuth.getInstance();

        user = (EditText)findViewById(R.id.editTextTextEmailAddress);
        pass = (EditText)findViewById(R.id.editTextTextPassword);
        pgBar = (ProgressBar)findViewById(R.id.progressBar2);
        btnLogin = (Button)findViewById(R.id.login1);
        btnRegister = (Button)findViewById(R.id.reg1);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString().toLowerCase();
                String password = pass.getText().toString();

                if(username.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this,"Fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                pgBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            if(username.contains("admin")) {
                                pgBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this,"Logged in as ADMIN", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                startActivity(intent);
                                return;
                            }
                            else if(username.contains("_")) {
                                pgBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);

                                return;
                            }
                            else {
                                pgBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this,"Logged in as Teacher", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), TeacherActivity.class);
                                startActivity(intent);
                            }
                        }
                        else {
                            pgBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this,"Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
}
package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    
    Button teacherAdd, logout;
    EditText email, pass;
    FirebaseAuth fAuth;
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()==null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        teacherAdd = (Button)findViewById(R.id.button3);
        logout = (Button)findViewById(R.id.btnlogout);
        email = (EditText)findViewById(R.id.teacherEmail);
        pass = (EditText)findViewById(R.id.editTextTextPassword);

        fAuth = FirebaseAuth.getInstance();

        teacherAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail_ID = email.getText().toString();
                String password = pass.getText().toString();

                if(mail_ID.contains("admin")) {
                    Toast.makeText(AdminActivity.this, "Invalid Email ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mail_ID.contains("_")) {
                    Toast.makeText(AdminActivity.this, "Invalid Email ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mail_ID.equals("") || password.equals("")) {
                    Toast.makeText(AdminActivity.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(mail_ID).matches()) {
                    email.setError("Invalid email address");
                    email.requestFocus();
                    return;
                }

                fAuth.createUserWithEmailAndPassword(mail_ID,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(AdminActivity.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(AdminActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
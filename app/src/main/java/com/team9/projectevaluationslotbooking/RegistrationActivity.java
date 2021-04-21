package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText fname, lname, phone, roll, email, pass, cnfPass;
    Spinner branch;
    RadioButton male,female;
    ProgressBar pgBar;
    FirebaseAuth fAuth;

    //DBHandler myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("Student Registration");

        //myDB = new DBHandler(this);
        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        phone = (EditText)findViewById(R.id.phone);
        branch = (Spinner) findViewById(R.id.branch_spinner);
        roll = (EditText)findViewById(R.id.roll);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        cnfPass = (EditText)findViewById(R.id.cnfPass);
        pgBar = (ProgressBar)findViewById(R.id.progressBar);
        btnLogin = (Button)findViewById(R.id.login);
        btnRegister = (Button)findViewById(R.id.reg);

        female = (RadioButton)findViewById(R.id.female);
        male = (RadioButton)findViewById(R.id.male);

        fAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = fname.getText().toString();
                String lastName = lname.getText().toString();
                String phoneNumber = phone.getText().toString();
                String brnh = branch.getSelectedItem().toString();
                String rollNumber = roll.getText().toString();
                String mail_ID = email.getText().toString().toLowerCase();
                String password = pass.getText().toString();
                String cnfPassword = cnfPass.getText().toString();

                if(!mail_ID.contains("_") || mail_ID.contains("admin")) {
                    Toast.makeText(RegistrationActivity.this, "Invalid Email ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean m=false, f=false;
                m = male.isChecked();
                f = female.isChecked();

                if(!password.equals(cnfPassword)) {
                    Toast.makeText(RegistrationActivity.this, "Passwords are not same", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(m==false && f==false) {
                    Toast.makeText(RegistrationActivity.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(firstName.equals("") || lastName.equals("") || phoneNumber.equals("") || brnh.equals("") || rollNumber.equals("") || mail_ID.equals("") || password.equals("")) {
                    Toast.makeText(RegistrationActivity.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(mail_ID).matches()) {
                    email.setError("Invalid email address");
                    email.requestFocus();
                    return;
                }

                String sex;
                if(m) {
                    sex="M";
                }
                else {
                    sex="F";
                }
                //=============================
                pgBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(mail_ID,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Student student = new Student(firstName, lastName, sex, phoneNumber, brnh, rollNumber, mail_ID);

                            FirebaseDatabase.getInstance().getReference("Student")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegistrationActivity.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                                        pgBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        pgBar.setVisibility(View.GONE);
                                        Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            pgBar.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
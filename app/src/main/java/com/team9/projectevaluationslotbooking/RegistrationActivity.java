package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText fname, lname, phone, roll, email, pass, cnfPass;
    Spinner branch;
    RadioButton male,female;

    FirebaseAuth fAuth;

    //DBHandler myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //myDB = new DBHandler(this);
        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        phone = (EditText)findViewById(R.id.phone);
        branch = (Spinner) findViewById(R.id.branch_spinner);
        roll = (EditText)findViewById(R.id.roll);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        cnfPass = (EditText)findViewById(R.id.cnfPass);

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

                String sex;
                if(m) {
                    sex="M";
                }
                else {
                    sex="F";
                }
                //=============================

                fAuth.createUserWithEmailAndPassword(mail_ID,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "Error! " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    Button btnLogout, btnMsg, btn_BookSlot, btnStatus;
    TextView tvWelcome;

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
        setContentView(R.layout.activity_home);

        btnMsg = (Button)findViewById((R.id.btnMsg));
        btnLogout = (Button)findViewById(R.id.btnlogout);
        tvWelcome = (TextView) findViewById(R.id.textView2);
        btn_BookSlot =(Button)findViewById(R.id.btn_BookSlot);
        btnStatus =(Button)findViewById(R.id.button5);



        FirebaseDatabase.getInstance().getReference("Student")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()) {
                            tvWelcome.setText("Welcome " + String.valueOf(task.getResult().child("fname").getValue()));
                        }
                    }
                });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
        btn_BookSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookSlotActivity.class);
                startActivity(intent);
            }
        });

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatusActivity.class);
                startActivity(intent);
            }
        });

    }
}
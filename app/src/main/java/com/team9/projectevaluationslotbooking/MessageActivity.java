package com.team9.projectevaluationslotbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageActivity extends AppCompatActivity {

    Button btnCmp, btnInbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getSupportActionBar().setTitle("Message");

        btnCmp = (Button)findViewById(R.id.btnCmp);
        btnInbox = (Button)findViewById(R.id.btnInbox);


        btnCmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageComposeActivity.class);
                startActivity(intent);
            }
        });

        btnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageInboxActivity.class);
                startActivity(intent);
            }
        });




    }
}
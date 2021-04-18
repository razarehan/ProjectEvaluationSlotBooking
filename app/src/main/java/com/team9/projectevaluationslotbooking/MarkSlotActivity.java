package com.team9.projectevaluationslotbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MarkSlotActivity extends AppCompatActivity
{
    private TextView pName, reqSlotDate;
    private Button btnMarkSlot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_slot);

        Intent intent = getIntent();
        String pname = intent.getStringExtra("pname");
        String reqDate = intent.getStringExtra("reqDate");

        pName = (TextView)findViewById(R.id.textView9);
        reqSlotDate = (TextView)findViewById(R.id.textView14);

        pName.setText("Project Name: "+pname);
        reqSlotDate.setText("Slot request on: "+reqDate);


    }
}
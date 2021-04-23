package com.team9.projectevaluationslotbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setTitle("Search Teacher");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
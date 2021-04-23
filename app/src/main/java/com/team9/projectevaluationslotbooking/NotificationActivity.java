package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    List<Project> projectList;
    RecyclerView recyclerView;
    HelperAdapter1 helperAdapter;
    DatabaseReference databaseReference;
    String teacherCode;

    @Override
    protected void onStart() {
        super.onStart();
        teacherCode = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        teacherCode = teacherCode.substring(0,8);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        projectList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Project");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Project project = ds.getValue(Project.class);
                    if(project.getTeacher1().equals("null") && teacherCode.equals("teacher1")) {
                        projectList.add(project);
                    }
                    if(project.getTeacher2().equals("null") && teacherCode.equals("teacher2")) {
                        projectList.add(project);
                    }
                    if(project.getTeacher3().equals("null") && teacherCode.equals("teacher3")) {
                        projectList.add(project);
                    }
                    if(project.getTeacher4().equals("null") && teacherCode.equals("teacher4")) {
                        projectList.add(project);
                    }
                }
                helperAdapter = new HelperAdapter1(projectList, NotificationActivity.this);
                recyclerView.setAdapter(helperAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
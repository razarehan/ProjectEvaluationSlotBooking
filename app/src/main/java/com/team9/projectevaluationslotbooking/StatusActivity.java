package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatusActivity extends AppCompatActivity {

    private TextView textView;
    private DatabaseReference databaseReference;
    private Project project;

    @Override
    protected void onStart() {
        super.onStart();

        textView = (TextView)findViewById(R.id.textView21);
        getSupportActionBar().setTitle("STATUS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("Project");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    project = ds.getValue(Project.class);
                    if(project.getStudent().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        if(project.getTimeAlloted().equals("retry")) {
                            textView.setText("Re-BOOK Slot");
                            return;
                        }
                        if(project.getTeacher1().equals("null") || project.getTeacher2().equals("null") ||
                                project.getTeacher3().equals("null") || project.getTeacher4().equals("null")) {
                            textView.setText("WAIT!! Not all TEACHERS has marked the SLOT");
                            return;
                        }
//                        if(project.getTeacher1().equals("slot0") || project.getTeacher2().equals("slot0") ||
//                                project.getTeacher3().equals("slot0") || project.getTeacher4().equals("slot0")) {
//                            textView.setText("Slot can not marked on that date");
//                            return;
//                        }
                        if(project.getTeacher1().equals("slot1") && project.getTeacher2().equals("slot1") &&
                                project.getTeacher3().equals("slot1") && project.getTeacher4().equals("slot1")) {
                            textView.setText("Slot marked on "+project.getSlotRequested()+"\nat 8:00am - 10:00am");
                            return;
                        }
                        if(project.getTeacher1().equals("slot2") && project.getTeacher2().equals("slot2") &&
                                project.getTeacher3().equals("slot2") && project.getTeacher4().equals("slot2")) {
                            textView.setText("Slot marked on "+project.getSlotRequested()+"\nat 10:00am - 12:00pm");
                            return;
                        }
                        if(project.getTeacher1().equals("slot3") && project.getTeacher2().equals("slot3") &&
                                project.getTeacher3().equals("slot3") && project.getTeacher4().equals("slot3")) {
                            textView.setText("Slot marked on "+project.getSlotRequested()+"\nat 1:00pm - 3:00pm");
                            return;
                        }
                        if(project.getTeacher1().equals("slot4") && project.getTeacher2().equals("slot4") &&
                                project.getTeacher3().equals("slot4") && project.getTeacher4().equals("slot4")) {
                            textView.setText("Slot marked on "+project.getSlotRequested()+"\nat 3:00pm - 5:00pm");
                            return;
                        }
                        textView.setText("Desired SLOT not available...\nPlease REQUEST Again");
                        updateTimeAlloted(ds.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

//        textView = (TextView)findViewById(R.id.textView21);
//        getSupportActionBar().setTitle("STATUS");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("Project");
//
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot ds:snapshot.getChildren()) {
//                    project = ds.getValue(Project.class);
//                    if(project.getStudent().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
//                        if(project.getTimeAlloted().equals("retry")) {
//                            textView.setText("Please Retry");
//                            return;
//                        }
//                        if(project.getTeacher1().equals("null") || project.getTeacher2().equals("null") ||
//                            project.getTeacher3().equals("null") || project.getTeacher4().equals("null")) {
//                            textView.setText("Not marked yet");
//                            return;
//                        }
////                        if(project.getTeacher1().equals("slot0") || project.getTeacher2().equals("slot0") ||
////                                project.getTeacher3().equals("slot0") || project.getTeacher4().equals("slot0")) {
////                            textView.setText("Slot can not marked on that date");
////                            return;
////                        }
//                        if(project.getTeacher1().equals("slot1") || project.getTeacher2().equals("slot1") ||
//                                project.getTeacher3().equals("slot1") || project.getTeacher4().equals("slot1")) {
//                            textView.setText("Slot marked on "+project.getSlotRequested()+"\nat 8:00am - 10:00am");
//                            return;
//                        }
//                        if(project.getTeacher1().equals("slot2") || project.getTeacher2().equals("slot2") ||
//                                project.getTeacher3().equals("slot2") || project.getTeacher4().equals("slot2")) {
//                            textView.setText("Slot marked on "+project.getSlotRequested()+"\nat 10:00am - 12:00pm");
//                            return;
//                        }
//                        if(project.getTeacher1().equals("slot3") || project.getTeacher2().equals("slot3") ||
//                                project.getTeacher3().equals("slot3") || project.getTeacher4().equals("slot3")) {
//                            textView.setText("Slot marked on "+project.getSlotRequested()+"\nat 1:00pm - 3:00pm");
//                            return;
//                        }
//                        if(project.getTeacher1().equals("slot4") || project.getTeacher2().equals("slot4") ||
//                                project.getTeacher3().equals("slot4") || project.getTeacher4().equals("slot4")) {
//                            textView.setText("Slot marked on "+project.getSlotRequested()+"\nat 3:00pm - 5:00pm");
//                            return;
//                        }
//                        textView.setText("Please Retry");
//                        updateTimeAlloted(ds.getKey());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    private void updateTimeAlloted(String id) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Project").child(id);
        project.setTimeAlloted("retry");
        databaseReference.setValue(project).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {

                }
            }
        });

    }

}
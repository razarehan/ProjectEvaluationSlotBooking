package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class MarkSlotActivity extends AppCompatActivity
{
    private TextView pName, reqSlotDate;
    private Button btnMarkSlot;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String markSlot;
    private DatabaseReference databaseReference;
    private String userID;
    private Project project;
    private String teacherCode;

    @Override
    protected void onStart() {
        super.onStart();
        teacherCode = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        teacherCode = teacherCode.substring(0,8);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_slot);
        getSupportActionBar().setTitle("Mark Slot");
        Intent intent = getIntent();
        String pname = intent.getStringExtra("pname");
        String reqDate = intent.getStringExtra("reqDate");
        String user = intent.getStringExtra("useremail");

        databaseReference = FirebaseDatabase.getInstance().getReference("Project");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Project pro = ds.getValue(Project.class);
                    if(pro.getStudent().equals(user)) {
                        userID = ds.getKey();
                        project = pro;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pName = (TextView)findViewById(R.id.textView9);
        reqSlotDate = (TextView)findViewById(R.id.textView14);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        btnMarkSlot = (Button)findViewById(R.id.btnMarkSlot);

        pName.setText("Project Name: "+pname);
        reqSlotDate.setText("Slot request on: "+reqDate);

        btnMarkSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkButton(v)==-1)  return;
                if(markSlot.equals(((RadioButton)findViewById(R.id.slot1)).getText().toString())) {
                    markSlot = "slot1";
                }
                if(markSlot.equals(((RadioButton)findViewById(R.id.slot2)).getText().toString())) {
                    markSlot = "slot2";
                }
                if(markSlot.equals(((RadioButton)findViewById(R.id.slot3)).getText().toString())) {
                    markSlot = "slot3";
                }
                if(markSlot.equals(((RadioButton)findViewById(R.id.slot4)).getText().toString())) {
                    markSlot = "slot4";
                }
                if(markSlot.equals(((RadioButton)findViewById(R.id.slot5)).getText().toString())) {
                    markSlot = "slot0";
                }
                if(markSlot.equals("")) {
                    Toast.makeText(MarkSlotActivity.this, "Please select slot", Toast.LENGTH_LONG).show();
                    return;
                }

                //================= update slot =================//
                updateSlot(userID);
            }
        });
    }

    public int checkButton(View v) {
        if(radioGroup.getCheckedRadioButtonId()==-1) {
            Toast.makeText(MarkSlotActivity.this, "Please select slot", Toast.LENGTH_LONG).show();
            return -1;
        }
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        markSlot = radioButton.getText().toString();
        return 0;
    }
    public void updateSlot(String id) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Project").child(id);
        if(teacherCode.equals("teacher1"))
            project.setTeacher1(markSlot);
        if(teacherCode.equals("teacher2"))
            project.setTeacher2(markSlot);
        if(teacherCode.equals("teacher3"))
            project.setTeacher3(markSlot);
        if(teacherCode.equals("teacher4"))
            project.setTeacher4(markSlot);

        databaseReference.setValue(project).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MarkSlotActivity.this, "Marked successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                }
            }
        });
    }
}
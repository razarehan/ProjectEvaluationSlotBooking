package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class BookSlotActivity extends AppCompatActivity {

    private int dd,mm,yyyy;
    private Button dateButton, submitBtn;
    private EditText dateVal, pName;
    private DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference = FirebaseDatabase.getInstance().getReference("Project");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Project project = ds.getValue(Project.class);
                    if(project.getStudent().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        Toast.makeText(BookSlotActivity.this, "Already Requested", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        return;
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
        setContentView(R.layout.activity_book_slot);
        getSupportActionBar().setTitle("Book Slot");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dateVal = (EditText)findViewById(R.id.editTextDate);
        pName = (EditText)findViewById(R.id.projectName);
        dateButton = (Button)findViewById(R.id.date);
        submitBtn = (Button)findViewById(R.id.btn_ReqSlot);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                dd = calendar.get(Calendar.DATE);
                mm = calendar.get(Calendar.MONTH);
                yyyy = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookSlotActivity.this, android.R.style.Theme_DeviceDefault_Dialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month++;
                                dateVal.setText(dayOfMonth+"/"+month+"/"+year);
                            }
                        },yyyy,mm,dd);
                        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                        datePickerDialog.show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projectName = pName.getText().toString();
                String slotDate = dateVal.getText().toString();
                if(projectName.equals("") || slotDate.equals("")) {
                    Toast.makeText(BookSlotActivity.this, "Fill all fields", Toast.LENGTH_LONG).show();
                    return;
                }

                Project project = new Project(projectName, slotDate, FirebaseAuth.getInstance().getCurrentUser().getEmail(), "null","null","null","null","null");

                FirebaseDatabase.getInstance().getReference().child("Project").push().setValue(project).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(BookSlotActivity.this, "Request send successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(BookSlotActivity.this, "Requesting Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

}
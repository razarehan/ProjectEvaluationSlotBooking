package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText editText;
    private Button btnSearch;
    private TextView name, phone, mail, branch;
    private DatabaseReference databaseReference;
    private List<Teacher> teacherList;
    private int flag=0;
    private ProgressBar progressBar;
    @Override
    protected void onStart() {
        super.onStart();
        name.setVisibility(View.INVISIBLE);
        phone.setVisibility(View.INVISIBLE);
        mail.setVisibility(View.INVISIBLE);
        branch.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setTitle("Search Teacher");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView)findViewById(R.id.textView23);
        phone = (TextView)findViewById(R.id.textView24);
        mail = (TextView)findViewById(R.id.textView25);
        branch = (TextView)findViewById(R.id.textView26);
        editText = (EditText)findViewById(R.id.editTextTextPersonName);
        btnSearch = (Button)findViewById(R.id.button6);
        progressBar = (ProgressBar)findViewById(R.id.progressBar3);
        teacherList = new ArrayList<>();
        readTeacher();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                name.setText("");
                phone.setText("");
                mail.setText("");
                branch.setText("");

                if(editText.getText().toString().equals("")) {
                    editText.setError("Cant't be blank");
                    editText.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                for(Teacher teacher: teacherList) {
                    if(teacher.getName().equals(editText.getText().toString())) {
                        name.setText("Name : "+ "" +teacher.getName());
                        phone.setText("Contact Number : "+""+ teacher.getContact());
                        mail.setText("Email : " +""+ teacher.getEmail());
                        branch.setText("Branch : "+""+ teacher.getBranch());
                        flag=1;
                    }
                }
                progressBar.setVisibility(View.INVISIBLE);
                if(flag==1) {
                    name.setVisibility(View.VISIBLE);
                    phone.setVisibility(View.VISIBLE);
                    mail.setVisibility(View.VISIBLE);
                    branch.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(SearchActivity.this, "Not Found", Toast.LENGTH_LONG).show();
                    name.setVisibility(View.INVISIBLE);
                    phone.setVisibility(View.INVISIBLE);
                    mail.setVisibility(View.INVISIBLE);
                    branch.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void readTeacher() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Teacher");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Teacher teacher = ds.getValue(Teacher.class);
                    teacherList.add(teacher);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
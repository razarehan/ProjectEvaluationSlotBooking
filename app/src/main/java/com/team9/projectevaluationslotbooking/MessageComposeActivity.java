package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MessageComposeActivity extends AppCompatActivity {

    Button btnSend, btnCancel;
    EditText etTO, etMSG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_compose);
        getSupportActionBar().setTitle("Compose Message");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSend = (Button)findViewById(R.id.button);
        btnCancel = (Button)findViewById(R.id.button2);
        etTO = (EditText)findViewById(R.id.editTextTextEmailAddress2);
        etMSG = (EditText)findViewById(R.id.editTextTextMultiLine);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String receiver = etTO.getText().toString().toLowerCase();
                String message = etMSG.getText().toString();

//                if(!receiver.contains("_") && !FirebaseAuth.getInstance().getCurrentUser().getEmail().contains("_")) {
//                    Toast.makeText(MessageComposeActivity.this, "Can't send to teacher", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(receiver.contains("_") && FirebaseAuth.getInstance().getCurrentUser().getEmail().contains("_")) {
                    Toast.makeText(MessageComposeActivity.this, "Can't send to the Student", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(receiver.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                    Toast.makeText(MessageComposeActivity.this, "Can't send to your own account", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(receiver.equals("") || message.equals("")) {
                    Toast.makeText(MessageComposeActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(receiver).matches()) {
                    etTO.setError("Invalid email address");
                    etTO.requestFocus();
                    return;
                }
                Message myMessage = new Message(receiver, FirebaseAuth.getInstance().getCurrentUser().getEmail(), message);

                FirebaseDatabase.getInstance().getReference().child("Message").push().setValue(myMessage).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MessageComposeActivity.this, "Message send successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MessageComposeActivity.this, "Sending Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
    }
}
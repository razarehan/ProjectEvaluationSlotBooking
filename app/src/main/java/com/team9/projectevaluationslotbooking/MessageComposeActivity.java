package com.team9.projectevaluationslotbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageComposeActivity extends AppCompatActivity {

    Button btnSend, btnCancel;
    EditText etTO, etMSG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_compose);

        btnSend = (Button)findViewById(R.id.button);
        btnCancel = (Button)findViewById(R.id.button2);
        etTO = (EditText)findViewById(R.id.editTextTextEmailAddress2);
        etMSG = (EditText)findViewById(R.id.editTextTextMultiLine);;

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String receiver = etTO.getText().toString().toLowerCase();
                String message = etMSG.getText().toString();
//                if(!db.checkUsername(receiver)) {
//                    Toast.makeText(MessageComposeActivity.this, "Invalid email Address", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(receiver.equals("") || message.equals("")) {
//                    Toast.makeText(MessageComposeActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(db.sendMessage(sender, receiver, message)) {
//                    Toast.makeText(MessageComposeActivity.this, "Message send successfully", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(MessageComposeActivity.this, "Sending Failed", Toast.LENGTH_SHORT).show();
//                }
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
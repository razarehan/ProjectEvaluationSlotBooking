package com.team9.projectevaluationslotbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessageInboxActivity extends AppCompatActivity {

    RecyclerView recView;
    List<Message> messageList;
    HelperAdapter helperAdapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_inbox);
        getSupportActionBar().setTitle("Inbox");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recView = (RecyclerView)findViewById(R.id.recyclerView);
        recView.setLayoutManager(new LinearLayoutManager(this));    // diff
        messageList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Message");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    Message message = ds.getValue(Message.class);
                    if(message.getTo().equalsIgnoreCase(FirebaseAuth.getInstance().getCurrentUser().getEmail()))
                        messageList.add(message);
                }
                helperAdapter = new HelperAdapter(messageList);
                recView.setAdapter(helperAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
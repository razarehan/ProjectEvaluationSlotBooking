package com.team9.projectevaluationslotbooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter {

    List<Message> messageList;

    public HelperAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);


        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        Message message = messageList.get(position);

        viewHolderClass.name.setText(message.getFrom());
        viewHolderClass.msg.setText(message.getMsg());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView name, msg;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView5);
            msg = itemView.findViewById(R.id.textView6);
        }
    }
}

package com.team9.projectevaluationslotbooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HelperAdapter1 extends RecyclerView.Adapter {
    private List<Project> projectList;
    private Context context;

    public HelperAdapter1(List<Project> projectList, Context context) {
        this.projectList = projectList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notify_design, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);


        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        Project project = projectList.get(position);

        viewHolderClass.name.setText(project.getProjectName());
        viewHolderClass.date.setText(project.getSlotRequested());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name, date;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.textView5);
            date = itemView.findViewById(R.id.textView6);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAbsoluteAdapterPosition();
            Project project = projectList.get(position);
            String pname = project.getProjectName();
            String date = project.getSlotRequested();

            Toast.makeText(context, "position", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(context, MarkSlotActivity.class);
            intent.putExtra("pname", pname);
            intent.putExtra("reqDate", date);
            context.startActivity(intent);
        }
    }
}

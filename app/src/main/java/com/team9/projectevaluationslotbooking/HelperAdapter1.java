package com.team9.projectevaluationslotbooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HelperAdapter1 extends RecyclerView.Adapter {
    List<Project> projectList;

    public HelperAdapter1(List<Project> projectList) {
        this.projectList = projectList;
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

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView name, date;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView5);
            date = itemView.findViewById(R.id.textView6);
        }
    }
}

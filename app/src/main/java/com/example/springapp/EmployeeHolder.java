package com.example.springapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeHolder extends RecyclerView.ViewHolder {

    TextView name, location, branch;

    public EmployeeHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.employees_list_name);
        location = itemView.findViewById(R.id.employees_location_name);
        branch = itemView.findViewById(R.id.employees_branch_name);
    }

}

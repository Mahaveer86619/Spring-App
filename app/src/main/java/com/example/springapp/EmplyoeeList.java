package com.example.springapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.springapp.Retrofit.EmployeeApi;
import com.example.springapp.Retrofit.RetrofitService;
import com.example.springapp.employee.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmplyoeeList extends AppCompatActivity {

    private RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplyoee_list);

        recyclerView = findViewById(R.id.list_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        floatingActionButton = findViewById(R.id.floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EmplyoeeList.this, EmployeeForm.class);
                startActivity(i);
            }
        });

        loadEmployees();
    }

    private void loadEmployees() {
        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

        employeeApi.getAllEmployee().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                populateListview(response.body());
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(EmplyoeeList.this, "Failed to load", Toast.LENGTH_SHORT).show();
                Logger.getLogger(EmployeeForm.class.getName())
                        .log(Level.SEVERE ,
                                "____________________HERE____________________" ,
                                t);
            }
        });
    }

    private void populateListview(List<Employee> employeeList) {
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(employeeList);
        recyclerView.setAdapter(employeeAdapter);
    }
}
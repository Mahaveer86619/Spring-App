package com.example.springapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.springapp.Retrofit.EmployeeApi;
import com.example.springapp.Retrofit.RetrofitService;
import com.example.springapp.employee.Employee;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initilizeComponents();


    }

    private void initilizeComponents() {
        TextInputEditText inputedName = findViewById(R.id.form_name);
        TextInputEditText inputedBranch = findViewById(R.id.form_branch);
        TextInputEditText inputedLocation = findViewById(R.id.form_location);
        Button button_save = findViewById(R.id.btn_form_save);

        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(inputedName.getText());
                String branch = String.valueOf(inputedBranch.getText());
                String location = String.valueOf(inputedLocation.getText());

                Employee employee = new Employee();
                employee.setName(name);
                employee.setBranch(branch);
                employee.setLocation(location);

                employeeApi.save(employee).enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Save Failed", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MainActivity.class.getName())
                                .log(Level.SEVERE ,
                                        "____________________HERE____________________" ,
                                        t);
                    }
                });
            }
        });


    }
}
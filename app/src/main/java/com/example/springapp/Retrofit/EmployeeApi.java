package com.example.springapp.Retrofit;

import com.example.springapp.employee.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmployeeApi {

    @GET("/employee/get-all")
    Call<List<Employee>> getAllEmployee();

    @POST("/employee/save")
    Call<Employee>save(@Body Employee employee);

}

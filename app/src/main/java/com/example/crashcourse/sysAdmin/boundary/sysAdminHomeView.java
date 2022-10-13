package com.example.crashcourse.sysAdmin.boundary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crashcourse.R;
import com.example.crashcourse.sysAdmin.controller.FeatureListAdapter;

import android.os.Bundle;
import android.util.Log;

/*
@File Name:sysAdminHomeView.java
@Brief: Boundary Class - System Admin Home Page View
User Story: 01-10
@Team:Crash Course
@Author:Allen Dylan Antony [7430449]
@Date: 14 - 10 - 22
*/

public class sysAdminHomeView extends AppCompatActivity {

    RecyclerView featureList;
    RecyclerView.LayoutManager featureListLayout;
    RecyclerView.Adapter featureListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_admin_home_view);

        Log.d("create", "apps created");
        featureList = findViewById(R.id.rv_menu_list);
        featureList.setHasFixedSize(true);

        Log.d("create", "list created");

        featureListLayout = new LinearLayoutManager(this);
        featureList.setLayoutManager(featureListLayout);

        Log.d("create", "layout done");

        featureListAdapter = new FeatureListAdapter(this);
        featureList.setAdapter(featureListAdapter);
        Log.d("create", "done");
    }
}
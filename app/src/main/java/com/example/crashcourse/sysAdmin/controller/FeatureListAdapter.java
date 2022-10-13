package com.example.crashcourse.sysAdmin.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import com.example.crashcourse.R;
import com.example.crashcourse.login.boundary.LoginView;
import com.example.crashcourse.sysAdmin.boundary.sysAdmin_createUser;

/*
@File Name:FeatureListAdapter.java
@Brief: Controller Class - Feature List Adapter to populate list with values
User Story: 01-10
@Team:Crash Course
@Author:Allen Dylan Antony [7430449]
@Date: 14 - 10 - 22
*/

public class FeatureListAdapter extends RecyclerView.Adapter<FeatureListAdapter.MyViewHolder> {

    private List<String> featuresList = Arrays.asList(new String[] {"Create User", "Display User List", "Update User List","Log Out" });
    Context context;

    public FeatureListAdapter(Context context){
        this.context = context;
        Log.d("test", "adapter created");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_feature,parent, false);
        Log.d("view result: ", new MyViewHolder(view).toString());
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.featureEnterButton.setText(featuresList.get(position));

        holder.featureEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, sysAdmin_createUser.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        if(position == getItemCount() - 1){
            holder.featureEnterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, LoginView.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return featuresList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Button featureEnterButton;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            featureEnterButton = itemView.findViewById(R.id.btn_enter_feature);
            parentLayout = itemView.findViewById(R.id.layout_sys_admin_feature_list);
        }
    }
}

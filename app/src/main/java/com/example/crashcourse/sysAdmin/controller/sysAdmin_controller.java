package com.example.crashcourse.sysAdmin.controller;

import android.widget.Toast;



import com.example.crashcourse.sysAdmin.boundary.sysAdmin_createUser;

public class sysAdmin_controller{

    private boolean checkInputFields(){
        if (input_userName.length() == 0){
            input_userName.setError("This field is required");
            Toast.makeText(sysAdmin_createUser.this,"Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (input_password.length() == 0){
            input_password.setError("This field is required");
            Toast.makeText(sysAdmin_createUser.this,"Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (input_Email.length() == 0){
            input_Email.setError("This field is required");
            Toast.makeText(sysAdmin_createUser.this,"Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (input_fName.length() == 0){
            input_fName.setError("This field is required");
            Toast.makeText(sysAdmin_createUser.this,"Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

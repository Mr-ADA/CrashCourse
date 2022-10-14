package com.example.crashcourse.sysAdmin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crashcourse.EntityClass.User;
import com.example.crashcourse.R;
import com.example.crashcourse.databaseHelper.sysAdmin_DatabaseHelper;

import java.util.List;
/*
@File Name:sysAdmin_createUser.java
@Brief: Controller Class - System Admin Create User
User Story: 03.
@Team:Crash Course
@Author:Sean Yeo Degen [7564880]
@Date: 12 - 10 - 22
 */

public class sysAdmin_createUser extends AppCompatActivity {
    //Reference to Layout Fields
    Button btn_createUser;
    EditText input_fName, input_userName,
            input_password,input_Email;
    Spinner spinner_userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sysadmin_create_user);

        btn_createUser = findViewById(R.id.btnCreateUser);
        input_fName = findViewById(R.id.fName);
        input_Email =findViewById(R.id.userEmail);
        input_userName = findViewById(R.id.userName);
        input_password = findViewById(R.id.password);
        spinner_userProfile = findViewById(R.id.userProfile);

        // Loading spinner data from database
        loadUserProfile();

        //System Admin Create User Button Listeners
        btn_createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = null;

                try {
                    if (checkInputFields() == true) {
                        user = new User(input_fName.getText().toString(),
                                input_userName.getText().toString(),
                                input_password.getText().toString(),
                                input_Email.getText().toString(),
                                spinner_userProfile.getSelectedItem().toString()); // Take note to add getSelectedItem()
                        Toast.makeText(sysAdmin_createUser.this, user.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(sysAdmin_createUser.this,"Error Creater User", Toast.LENGTH_SHORT).show();
                }

                //Reference to crashCourse DB - User Table
                sysAdmin_DatabaseHelper databaseHelper = new sysAdmin_DatabaseHelper(sysAdmin_createUser.this);
                boolean sucess = databaseHelper.createUser(user);
                Toast.makeText(sysAdmin_createUser.this, "Sucessfully Created User = " + sucess,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    @Function: loadUserProfile
    @Brief: Whenever the Application is lauched, loadUserProfile() will take User Profile Data from
    the USER_PROFILE_TABLE to populate items to the Spinner.
    @Team:Crash Course
    @Author:Sean Yeo Degen [7564880]
    @Date: 12 - 10 - 22
    */
    private void loadUserProfile(){
        sysAdmin_DatabaseHelper db = new sysAdmin_DatabaseHelper(getApplicationContext());
        List<String> userP = db.getuserP();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, userP);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_userProfile.setAdapter(dataAdapter);
    }

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

package com.example.crashcourse.sysAdmin.boundary;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crashcourse.EntityClass.User;
import com.example.crashcourse.R;
import com.example.crashcourse.databaseHelper.DatabaseHelper;

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

        //hardcode the value of the spinner
        populateSpinner();


        // Loading spinner data from database
        //loadUserProfile(); //-> a bug, table user profile is not defined

        //System Admin Create User Button Listeners
        btn_createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = null;

                try {
                    user = new User(input_fName.getText().toString(),
                            input_userName.getText().toString(),
                            input_password.getText().toString(),
                            input_Email.getText().toString(),
                            spinner_userProfile.getSelectedItem().toString());
                    Toast.makeText(sysAdmin_createUser.this,user.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(sysAdmin_createUser.this,"Error Creater User", Toast.LENGTH_SHORT).show();
                }


                //Reference to crashCourse DB - User Table
                DatabaseHelper databaseHelper = new DatabaseHelper(sysAdmin_createUser.this);
                boolean sucess = databaseHelper.createUser(user);
                Toast.makeText(sysAdmin_createUser.this, "Sucessfully Created User = " + sucess,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadUserProfile(){
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<String> userP = db.getuserP();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, userP);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_userProfile.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String userP = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + userP,
                Toast.LENGTH_LONG).show();

    }

    private void populateSpinner(){
        Spinner uPSpinner = (Spinner) findViewById(R.id.userProfile);
        ArrayAdapter<CharSequence> uPAdapter = ArrayAdapter.createFromResource(this, R.array.user_profile_array, android.R.layout.simple_spinner_dropdown_item);

        uPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uPSpinner.setAdapter(uPAdapter);
    }
}

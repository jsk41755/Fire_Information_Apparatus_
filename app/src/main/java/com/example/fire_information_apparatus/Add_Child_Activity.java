package com.example.fire_information_apparatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add_Child_Activity extends AppCompatActivity {

    TextInputLayout full_name;
    TextView fullNameLabel, usernameLabel;
    Button add_button;

    DatabaseReference reference;
    String _USERNAME, _NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        reference = FirebaseDatabase.getInstance().getReference("Data");

        full_name= findViewById(R.id.full_name_profile);// full name


        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

        add_button = findViewById(R.id.addBtn);


        
        showAllUserData();

        //Log.d("ergerrr",intent2.getStringExtra("Edit_Object_Name"));



        /*add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }

    private void showAllUserData() {
        Intent intent2 = getIntent();
        _USERNAME = intent2.getStringExtra("Edit_Object_Name");
        _NAME = intent2.getStringExtra("Edit_Old_Address");

        usernameLabel.setText(_USERNAME);
        fullNameLabel.setText(_NAME);
    }

    public void update(View view){
        if(isNameChanged()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(this, "Data is same and can not be updated", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private boolean isNameChanged() {
        if (!_NAME.equals(full_name.getEditText().getText().toString())){
            reference.child(_USERNAME).child("Old_Address").setValue(full_name.getEditText().getText().toString());
            //reference.child(_USERNAME).child("Object_Name").setValue(full_name.getEditText().getText().toString());
            return true;
        } else {
            return false;
        }
    }
}
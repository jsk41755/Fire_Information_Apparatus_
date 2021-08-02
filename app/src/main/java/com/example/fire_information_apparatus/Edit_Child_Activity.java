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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Child_Activity extends AppCompatActivity {

    Button add_button;

    EditText Edit_Object_Name, Edit_Old_Address, Edit_New_Address, Edit_Object_Manager, Edit_Manager_General_Telephone, Edit_Manager_Cell_Phone;

    DatabaseReference reference;
    String _Object_Name, _Old_Address, _New_Address, _Object_Manager, _Manager_General_Telephone, _Manager_Cell_Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_child);

        reference = FirebaseDatabase.getInstance().getReference("Data");

        Edit_Old_Address = findViewById(R.id.Edit_Old_Address);
        Edit_New_Address = findViewById(R.id.Edit_New_Address);
        Edit_Object_Manager = findViewById(R.id.Edit_Object_Manager);
        Edit_Manager_General_Telephone = findViewById(R.id.Edit_Manager_General_Telephone);
        Edit_Manager_Cell_Phone = findViewById(R.id.Edit_Manager_Cell_Phone);

        /*Edit_Object_Name = findViewById(R.id.Edit_Object_Name);
        Edit_Old_Address = findViewById(R.id.Edit_Old_Address);
        Edit_New_Address = findViewById(R.id.Edit_New_Address);
        Edit_Object_Manager = findViewById(R.id.Edit_Object_Manager);
        Edit_Manager_General_Telephone = findViewById(R.id.Edit_Manager_General_Telephone);
        Edit_Manager_Cell_Phone = findViewById(R.id.Edit_Manager_Cell_Phone);*/


        Intent intent = getIntent();
        Edit_Old_Address.setText(intent.getStringExtra("Edit_Old_Address"));
        Edit_New_Address.setText(intent.getStringExtra("Edit_New_Address"));
        Edit_Object_Manager.setText(intent.getStringExtra("Edit_Object_Manager"));
        Edit_Manager_General_Telephone.setText(intent.getStringExtra("Edit_Manager_General_Telephone"));
        Edit_Manager_Cell_Phone.setText(intent.getStringExtra("Edit_Manager_Cell_Phone"));

        add_button = findViewById(R.id.addBtn);


        
        showAllUserData();

        //Log.d("edit",intent.getStringExtra("Edit_Object_Name"));

    }

    private void showAllUserData() {
        Intent intent2 = getIntent();
        _Object_Name = intent2.getStringExtra("Edit_Object_Name");
        _Old_Address = intent2.getStringExtra("Edit_Old_Address");
        _New_Address = intent2.getStringExtra("Edit_New_Address");
        _Object_Manager = intent2.getStringExtra("Edit_Object_Manager");
        _Manager_General_Telephone = intent2.getStringExtra("Edit_Manager_General_Telephone");
        _Manager_Cell_Phone = intent2.getStringExtra("Edit_Manager_Cell_Phone");
/*
        usernameLabel.setText(_USERNAME);
        fullNameLabel.setText(_NAME);*/
    }

    public void update(View view){
        if(isNameChanged()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Data is same and can not be updated", Toast.LENGTH_SHORT).show();
        }
        finish();


    }

    private boolean isNameChanged() {
        //reference.child(_Object_Name).child("_Object_Name").setValue(Edit_Object_Name_TIL.getEditText().getText().toString());
        if (!_Old_Address.equals(Edit_Old_Address.getText().toString())){
            reference.child(_Object_Name).child("Old_Address").setValue(Edit_Old_Address.getText().toString());
            return true;
        } else {
            return false;
        }
    }
}
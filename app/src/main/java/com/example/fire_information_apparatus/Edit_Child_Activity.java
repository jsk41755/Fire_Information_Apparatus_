package com.example.fire_information_apparatus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_Child_Activity extends Activity {

    Button add_button, exit_button;

    EditText Edit_Old_Address, Edit_New_Address, Edit_Object_Manager, Edit_Manager_General_Telephone, Edit_Manager_Cell_Phone;

    DatabaseReference reference;
    String _Object_Name, _Old_Address, _New_Address, _Object_Manager, _Manager_General_Telephone, _Manager_Cell_Phone, _Jurisdiction_Center;

    String[] items = {"영운119안전센터", "오창안전센터", "내수안전센터","율량안전센터","북문안전센터","문의안전센터"};
    String Jurisdiction_Center_Select, Edit_Jurisdiction_Center;    //관할 선택

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_child);

        reference = FirebaseDatabase.getInstance().getReference("Data");

        Edit_Old_Address = findViewById(R.id.Edit_Old_Address);
        Edit_New_Address = findViewById(R.id.Edit_New_Address);
        Edit_Object_Manager = findViewById(R.id.Edit_Object_Manager);
        Edit_Manager_General_Telephone = findViewById(R.id.Edit_Manager_General_Telephone);
        Edit_Manager_Cell_Phone = findViewById(R.id.Edit_Manager_Cell_Phone);


        Intent intent = getIntent();
        Edit_Old_Address.setText(intent.getStringExtra("Edit_Old_Address"));
        Edit_New_Address.setText(intent.getStringExtra("Edit_New_Address"));
        Edit_Object_Manager.setText(intent.getStringExtra("Edit_Object_Manager"));
        Edit_Manager_General_Telephone.setText(intent.getStringExtra("Edit_Manager_General_Telephone"));
        Edit_Manager_Cell_Phone.setText(intent.getStringExtra("Edit_Manager_Cell_Phone"));
        Edit_Jurisdiction_Center = intent.getStringExtra("Jurisdiction_Center");

        add_button = findViewById(R.id.addBtn);

        exit_button = findViewById(R.id.exit_button);

        Spinner Jurisdiction_Center = findViewById(R.id.Edit_Jurisdiction_Center);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items
        );

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        showAllUserData();

        Jurisdiction_Center.setAdapter(adapter1);

        Jurisdiction_Center.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Jurisdiction_Center_Select = items[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void showAllUserData() {
        Intent intent2 = getIntent();
        _Object_Name = intent2.getStringExtra("Edit_Object_Name");
        _Old_Address = intent2.getStringExtra("Edit_Old_Address");
        _New_Address = intent2.getStringExtra("Edit_New_Address");
        _Object_Manager = intent2.getStringExtra("Edit_Object_Manager");
        _Jurisdiction_Center = intent2.getStringExtra("Edit_Jurisdiction_Center");
        _Manager_General_Telephone = intent2.getStringExtra("Edit_Manager_General_Telephone");
        _Manager_Cell_Phone = intent2.getStringExtra("Edit_Manager_Cell_Phone");
/*
        usernameLabel.setText(_USERNAME);
        fullNameLabel.setText(_NAME);*/
    }

    public void update(View view){
        isManagerCellPhoneChanged();
        isManagerGeneralTelephoneChanged();
        isObjectManagerChanged();
        isNewAddressChanged();
        isOldAddressChanged();
        isJurisdiction_Center();

        /*if(isOldAddressChanged() || isNewAddressChanged() || isObjectManagerChanged() || isManagerGeneralTelephoneChanged() || isManagerCellPhoneChanged()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Data is same and can not be updated", Toast.LENGTH_SHORT).show();
        }*/
        finish();


    }

    private boolean isManagerCellPhoneChanged() {
        if (!_Manager_Cell_Phone.equals(Edit_Manager_Cell_Phone.getText().toString())){
            reference.child(_Object_Name).child("Manager_Cell_Phone").setValue(Edit_Manager_Cell_Phone.getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isManagerGeneralTelephoneChanged() {
        if (!_Manager_General_Telephone.equals(Edit_Manager_General_Telephone.getText().toString())){
            reference.child(_Object_Name).child("Manager_General_Telephone").setValue(Edit_Manager_General_Telephone.getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isObjectManagerChanged() {
        if (!_Object_Manager.equals(Edit_Object_Manager.getText().toString())){
            reference.child(_Object_Name).child("Object_Manager").setValue(Edit_Object_Manager.getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isNewAddressChanged() {
        if (!_New_Address.equals(Edit_New_Address.getText().toString())){
            reference.child(_Object_Name).child("New_Address").setValue(Edit_New_Address.getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isOldAddressChanged() {
        //reference.child(_Object_Name).child("_Object_Name").setValue(Edit_Object_Name_TIL.getEditText().getText().toString());
        if (!_Old_Address.equals(Edit_Old_Address.getText().toString())){
            reference.child(_Object_Name).child("Old_Address").setValue(Edit_Old_Address.getText().toString());
            return true;
        } else {
            return false;
        }
    }

    private boolean isJurisdiction_Center() {
        //reference.child(_Object_Name).child("_Object_Name").setValue(Edit_Object_Name_TIL.getEditText().getText().toString());
        if (!_Jurisdiction_Center.equals(Edit_Jurisdiction_Center)){
            reference.child(_Object_Name).child("Jurisdiction_Center").setValue(Jurisdiction_Center_Select);
            return true;
        } else {
            return false;
        }
    }
}
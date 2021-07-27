package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Detail_activity extends AppCompatActivity {

    private TextView Object_Name, By_Place, Old_Address, New_Address, Jurisdiction_Center, Reporting_Time, Object_Manager, Manager_General_Telephone,
            Manager_Cell_Phone, by_Case_Cause;
    private Button editbtn;

    DatabaseReference ref, DataRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Object_Name = findViewById(R.id.Object_Name);
        Old_Address = findViewById(R.id.Old_Address);
        New_Address = findViewById(R.id.New_Address);
        Object_Manager = findViewById(R.id.Object_Manager);
        By_Place = findViewById(R.id.By_Place);
        Manager_General_Telephone = findViewById(R.id.Manager_General_Telephone);
        Manager_Cell_Phone = findViewById(R.id.Manager_Cell_Phone);

        editbtn = findViewById(R.id.button234);


        Intent intent = getIntent();

        Object_Name.setText(intent.getStringExtra("Object_Name"));
        Old_Address.setText(intent.getStringExtra("Old_Address"));
        New_Address.setText(intent.getStringExtra("New_Address"));
        Object_Manager.setText(intent.getStringExtra("Object_Manager"));
        Manager_General_Telephone.setText(intent.getStringExtra("Manager_General_Telephone"));
        Manager_Cell_Phone.setText(intent.getStringExtra("Manager_Cell_Phone"));
        By_Place.setText(intent.getStringExtra("By_Place"));


        /*String uKey = getIntent().getStringExtra("uKey");

        DataRef = FirebaseDatabase.getInstance().getReference().child("Data").child(uKey);*/

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent_edit = new Intent(v.getContext(), Add_Child_Activity.class);
                intent_edit.putExtra("Edit_Object_Name", intent.getStringExtra("Object_Name"));
                intent_edit.putExtra("Edit_Old_Address", intent.getStringExtra("Old_Address"));
                context.startActivity(intent_edit);
            }
        });

    }


}
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

    private TextView text;
    private Button editbtn;

    DatabaseReference ref, DataRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        text = findViewById(R.id.textView1);
        editbtn = findViewById(R.id.button234);


        Intent intent = getIntent();

        text.setText(intent.getStringExtra("Object_Name"));

        /*String uKey = getIntent().getStringExtra("uKey");

        DataRef = FirebaseDatabase.getInstance().getReference().child("Data").child(uKey);*/

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent_edit = new Intent(v.getContext(), Add_Child_Activity.class);
                intent_edit.putExtra("Edit_Object_Name", intent.getStringExtra("Object_Name"));
                context.startActivity(intent_edit);
            }
        });

    }


}
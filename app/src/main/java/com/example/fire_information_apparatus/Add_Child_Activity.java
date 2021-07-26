package com.example.fire_information_apparatus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add_Child_Activity extends AppCompatActivity {

    EditText add_object;
    Button add_button;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        reference = FirebaseDatabase.getInstance().getReference("Data");

        add_object= findViewById(R.id.add_Object);
        add_button = findViewById(R.id.addBtn);

        Intent intent2 = getIntent();

        Log.d("ergerrr",intent2.getStringExtra("Edit_Object_Name"));

        add_object.setText(intent2.getStringExtra("Edit_Object_Name"));

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("Object_Name",add_object.getText().toString());

            }
        });

    }
}
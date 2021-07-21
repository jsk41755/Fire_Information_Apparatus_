package com.example.fire_information_apparatus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Add_ListView extends Activity {

    private EditText etValue, etValue2;
    private Button button;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_information);

        etValue = findViewById(R.id.et_value);
        etValue2 = findViewById(R.id.et_value2);
        button = findViewById(R.id.finish_button);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Data");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sValue = etValue.getText().toString().trim();
                String sValue2 = etValue2.getText().toString().trim();

                String sKey = databaseReference.push().getKey();

                if(sKey != null){
                    databaseReference.child(sKey).child("value").setValue(sValue);
                    databaseReference.child(sKey).child("value2").setValue(sValue2);

                    etValue.setText("");
                    etValue2.setText("");
                }

                finish();
            }
        });


    }

}

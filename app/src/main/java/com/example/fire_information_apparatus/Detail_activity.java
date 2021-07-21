package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Detail_activity extends AppCompatActivity {

    private TextView text;
    private Button deletebtn;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        text = findViewById(R.id.textView1);
        deletebtn = findViewById(R.id.button23);


        Intent intent = getIntent();

        text.setText(intent.getStringExtra("abcd"));

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteContent();
            }
        });
    }

    private void onDeleteContent() {
        firebaseDatabase.getReference().child("Content").child(uidList.get(position)).removeValue().addOnSuccessListener(
                new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "삭제 성공", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("error: "+e.getMessage());
                Toast.makeText(context, "삭제 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
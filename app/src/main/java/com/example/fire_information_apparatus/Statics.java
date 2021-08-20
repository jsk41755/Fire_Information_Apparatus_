package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Statics extends Activity {

    private TextView Artificial_Factors_1, Artificial_Factors_2, Artificial_Factors_3, Artificial_Factors_4, Artificial_Factors_5;
    private TextView Administrative_Factors_1, Administrative_Factors_2, Administrative_Factors_3;
    private TextView System_Factors_1, System_Factors_2, System_Factors_3, System_Factors_4;
    private TextView Etc_Factors_1;

    private TextView Factory_Place, Dwelling_Place, Senior_Place, Etc_Place;
    private Button finish_button, Artificial_Factors;


    private FirebaseDatabase database;
    private DatabaseReference databaseReference_Place, databaseReference_Case;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_statics);


        Artificial_Factors_1 = findViewById(R.id.Artificial_Factors_1);
        Artificial_Factors_2 = findViewById(R.id.Artificial_Factors_2);
        Artificial_Factors_3 = findViewById(R.id.Artificial_Factors_3);
        Artificial_Factors_4 = findViewById(R.id.Artificial_Factors_4);
        Artificial_Factors_5 = findViewById(R.id.Artificial_Factors_5);

        Administrative_Factors_1 = findViewById(R.id.Administrative_Factors_1);
        Administrative_Factors_2 = findViewById(R.id.Administrative_Factors_2);
        Administrative_Factors_3 = findViewById(R.id.Administrative_Factors_3);

        System_Factors_1 = findViewById(R.id.System_Factors_1);
        System_Factors_2 = findViewById(R.id.System_Factors_2);
        System_Factors_3 = findViewById(R.id.System_Factors_3);
        System_Factors_4 = findViewById(R.id.System_Factors_4);

        Etc_Factors_1 = findViewById(R.id.Etc_Factors_1);

        Factory_Place = findViewById(R.id.Factory_Place);
        Dwelling_Place = findViewById(R.id.Dwelling_Place);
        Senior_Place = findViewById(R.id.Senior_Place);
        Etc_Place = findViewById(R.id.Etc_Place);

        finish_button = findViewById(R.id.finish_button);
        Artificial_Factors = findViewById(R.id.Artificial_Factors);

        database = FirebaseDatabase.getInstance();
        databaseReference_Place = database.getReference("Statistics").child("By_Place");
        databaseReference_Case = database.getReference("Statistics").child("Case_Stack");

        Artificial_Factors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StaticsGraph_Activity.class));
            }
        });

        databaseReference_Place.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Factory_Place.setText(String.valueOf(snapshot.child("Factory_Place").getValue()));
                Dwelling_Place.setText(String.valueOf(snapshot.child("Dwelling_Place").getValue()));
                Senior_Place.setText(String.valueOf(snapshot.child("Senior_Place").getValue()));
                Etc_Place.setText(String.valueOf(snapshot.child("Etc_Place").getValue()));
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

        databaseReference_Case.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Artificial_Factors_1.setText(String.valueOf(snapshot.child("Artificial_Factors").child("0").getValue()));
                Artificial_Factors_2.setText(String.valueOf(snapshot.child("Artificial_Factors").child("1").getValue()));
                Artificial_Factors_3.setText(String.valueOf(snapshot.child("Artificial_Factors").child("2").getValue()));
                Artificial_Factors_4.setText(String.valueOf(snapshot.child("Artificial_Factors").child("3").getValue()));
                Artificial_Factors_5.setText(String.valueOf(snapshot.child("Artificial_Factors").child("4").getValue()));

                Administrative_Factors_1.setText(String.valueOf(snapshot.child("Administrative_Factors").child("0").getValue()));
                Administrative_Factors_2.setText(String.valueOf(snapshot.child("Administrative_Factors").child("1").getValue()));
                Administrative_Factors_3.setText(String.valueOf(snapshot.child("Administrative_Factors").child("2").getValue()));

                Etc_Factors_1.setText(String.valueOf(snapshot.child("Etc_Factors").child("0").getValue()));

                System_Factors_1.setText(String.valueOf(snapshot.child("System_Factors").child("0").getValue()));
                System_Factors_2.setText(String.valueOf(snapshot.child("System_Factors").child("1").getValue()));
                System_Factors_3.setText(String.valueOf(snapshot.child("System_Factors").child("2").getValue()));
                System_Factors_4.setText(String.valueOf(snapshot.child("System_Factors").child("3").getValue()));
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });

    }
    public void mOnClose(View v){
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;

public class Statics extends Activity {

    private TextView Artificial_Factors_1, Artificial_Factors_2, Artificial_Factors_3, Artificial_Factors_4, Artificial_Factors_5;
    private TextView Administrative_Factors_1, Administrative_Factors_2, Administrative_Factors_3;
    private TextView System_Factors_1, System_Factors_2, System_Factors_3, System_Factors_4;
    private TextView Etc_Factors_1, Factors_All;

    private TextView Factory_Place, Dwelling_Place, Senior_Place, Etc_Place;
    private Button finish_button, Artificial_Factors, Administrative_Factors, System_Factors, Etc_Factors, Place;


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
        Administrative_Factors = findViewById(R.id.Administrative_Factors);
        System_Factors = findViewById(R.id.System_Factors);
        Etc_Factors = findViewById(R.id.Etc_Factors);

        Place = findViewById(R.id.Place);
        Factors_All = findViewById(R.id.Factors_All);

        final int[] Artificial_Factors_all = {0};
        final int[] Administrative_Factors_all = {0};
        final int[] System_Factors_all = {0};
        final int[] Etc_Factors_all = {0};

        final int[] Factors_all = {0};

        final int[] Place_all = {0};

        database = FirebaseDatabase.getInstance();
        databaseReference_Place = database.getReference("Statistics").child("By_Place");
        databaseReference_Case = database.getReference("Statistics").child("Case_Stack");

        databaseReference_Case.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i = 0; i < 5; i++)
                    Artificial_Factors_all[0] += Integer.parseInt(String.valueOf(snapshot.child("Artificial_Factors").child(String.valueOf(i)).getValue()));
                Artificial_Factors.setText("인위적요인 - " + Artificial_Factors_all[0]);

                for(int i = 0; i < 3; i++)
                    Administrative_Factors_all[0] += Integer.parseInt(String.valueOf(snapshot.child("Administrative_Factors").child(String.valueOf(i)).getValue()));
                Administrative_Factors.setText("관리적요인 - " + Administrative_Factors_all[0]);

                for(int i = 0; i < 4; i++)
                    System_Factors_all[0] += Integer.parseInt(String.valueOf(snapshot.child("System_Factors").child(String.valueOf(i)).getValue()));
                System_Factors.setText("시스템요인 - " + System_Factors_all[0]);

                for(int i = 0; i < 1; i++)
                    Etc_Factors_all[0] += Integer.parseInt(String.valueOf(snapshot.child("Etc_Factors").child(String.valueOf(i)).getValue()));
                Etc_Factors.setText("기타 - " + Etc_Factors_all[0]);

                Factors_all[0] += (Artificial_Factors_all[0] + Administrative_Factors_all[0] + System_Factors_all[0] + Etc_Factors_all[0]);
                Factors_All.setText("사례원인별 - " + Factors_all[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference_Place.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Place_all[0] += Integer.parseInt(String.valueOf(snapshot.child("Dwelling_Place").getValue()));
                Place_all[0] += Integer.parseInt(String.valueOf(snapshot.child("Etc_Place").getValue()));
                Place_all[0] += Integer.parseInt(String.valueOf(snapshot.child("Factory_Place").getValue()));
                Place_all[0] += Integer.parseInt(String.valueOf(snapshot.child("Senior_Place").getValue()));

                Place.setText("장소별 - " + Place_all[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Artificial_Factors.setText("인위적요인 - " + Artificial_Factors_all[0]);

        Artificial_Factors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(v.getContext(), StaticsGraph_Activity.class);
                intent.putExtra("Factors","Artificial_Factors");
                intent.putExtra("Num","5");
                intent.putExtra("Statics","0");

                context.startActivity(intent);
            }
        });

        Administrative_Factors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(v.getContext(), StaticsGraph_Activity.class);
                intent.putExtra("Factors","Administrative_Factors");
                intent.putExtra("Num","3");
                intent.putExtra("Statics","0");

                context.startActivity(intent);
            }
        });

        System_Factors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(v.getContext(), StaticsGraph_Activity.class);
                intent.putExtra("Factors","System_Factors");
                intent.putExtra("Num","4");
                intent.putExtra("Statics","0");

                context.startActivity(intent);
            }
        });

        Etc_Factors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(v.getContext(), StaticsGraph_Activity.class);
                intent.putExtra("Factors","Etc_Factors");
                intent.putExtra("Num","1");
                intent.putExtra("Statics","0");

                context.startActivity(intent);
            }
        });

        Place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(v.getContext(), StaticsGraph_Activity.class);
                intent.putExtra("Factors","By_Place");
                intent.putExtra("Num","4");
                intent.putExtra("Statics","1");

                context.startActivity(intent);
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
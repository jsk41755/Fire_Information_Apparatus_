package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StaticsGraph_Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference_Place, databaseReference_Case;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statics_graph);

        database = FirebaseDatabase.getInstance();
        databaseReference_Place = database.getReference("Statistics").child("By_Place");
        databaseReference_Case = database.getReference("Statistics").child("Case_Stack");
        BarChart barChart = findViewById(R.id.barChart);

        databaseReference_Case.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<BarEntry> visitors = new ArrayList<>();

                XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(false);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(getAreaCount));

                public ArrayList<String> getAreaCount() {

                    ArrayList<String> label = new ArrayList<>();
                    for (int i = 0; i < areaList.size(); i++)
                        label.add(areaList.get(i).getTopicName());
                    return label;
                }

                float a = Integer.parseInt(String.valueOf(snapshot.child("Artificial_Factors").child("0").getValue()));
                //Log.d("a", String.valueOf(a));
                visitors.add(new BarEntry(2014, Integer.parseInt(String.valueOf(snapshot.child("Artificial_Factors").child("0").getValue()))));
                visitors.add(new BarEntry(2015, Integer.parseInt(String.valueOf(snapshot.child("Artificial_Factors").child("1").getValue()))));
                visitors.add(new BarEntry(2016, Integer.parseInt(String.valueOf(snapshot.child("Artificial_Factors").child("2").getValue()))));
                visitors.add(new BarEntry(2017, Integer.parseInt(String.valueOf(snapshot.child("Artificial_Factors").child("3").getValue()))));
                visitors.add(new BarEntry(2018, Integer.parseInt(String.valueOf(snapshot.child("Artificial_Factors").child("4").getValue()))));

                BarDataSet barDataSet = new BarDataSet(visitors, "요인별 색상");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(24.7f);

                BarData barData = new BarData(barDataSet);

                barChart.setFitBars(true);
                barChart.setData(barData);
                barChart.getDescription().setText("인위적 요인");
                barChart.animateY(2000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_statics_graph);

        database = FirebaseDatabase.getInstance();
        databaseReference_Place = database.getReference("Statistics").child("By_Place");
        databaseReference_Case = database.getReference("Statistics").child("Case_Stack");
        BarChart barChart = findViewById(R.id.barChart);

        Intent intent = getIntent();

        if(Integer.parseInt(intent.getStringExtra("Statics")) == 0) {
            databaseReference_Case.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ArrayList Factors_name = new ArrayList();
                    ArrayList<BarEntry> visitors = new ArrayList<>();

                    String Factors = intent.getStringExtra("Factors");

                    Description description = new Description();

                    if (Factors.equals("Artificial_Factors")) {
                        Factors_name.add("먼지/연기/수증기");
                        Factors_name.add("조리/화기");
                        Factors_name.add("점검/공사중");
                        Factors_name.add("냉/난방기");
                        Factors_name.add("기타");
                        description.setText("인위적 요인");
                        for (int i = 0; i < Integer.parseInt(intent.getStringExtra("Num")); i++) {
                            visitors.add(new BarEntry(i, Integer.parseInt(String.valueOf(snapshot.child(Factors).child(Integer.toString(i)).getValue()))));
                        }
                    } else if (Factors.equals("Administrative_Factors")) {
                        Factors_name.add("습기");
                        Factors_name.add("관리불량");
                        Factors_name.add("기타");
                        description.setText("관리적 요인");
                        for (int i = 0; i < Integer.parseInt(intent.getStringExtra("Num")); i++) {
                            visitors.add(new BarEntry(i, Integer.parseInt(String.valueOf(snapshot.child(Factors).child(Integer.toString(i)).getValue()))));
                        }
                    } else if (Factors.equals("System_Factors")) {
                        Factors_name.add("노후");
                        Factors_name.add("시공");
                        Factors_name.add("기기오류");
                        Factors_name.add("기타");
                        description.setText("시스템 요인");
                        for (int i = 0; i < Integer.parseInt(intent.getStringExtra("Num")); i++) {
                            visitors.add(new BarEntry(i, Integer.parseInt(String.valueOf(snapshot.child(Factors).child(Integer.toString(i)).getValue()))));
                        }
                    } else if (Factors.equals("Etc_Factors")) {
                        Factors_name.add("복구/원인불명");
                        description.setText("기타 요인");
                        for (int i = 0; i < Integer.parseInt(intent.getStringExtra("Num")); i++) {
                            visitors.add(new BarEntry(i, Integer.parseInt(String.valueOf(snapshot.child(Factors).child(Integer.toString(i)).getValue()))));
                        }
                    }

                    BarDataSet barDataSet = new BarDataSet(visitors, "요인별 색상");
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(12.7f);

                    BarData barData = new BarData(barDataSet);

                    barChart.setData(barData);


                    barChart.setDescription(description);
                    barChart.animateY(2000);

                    XAxis xAxis = barChart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(Factors_name));
                    xAxis.setDrawGridLines(false);
                    xAxis.setDrawAxisLine(false);

                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1f);
                    xAxis.setTextSize(12f);
                    xAxis.setLabelCount(visitors.size());
                    xAxis.setGranularityEnabled(true);

                    barChart.setDragEnabled(true);
                    barChart.setVisibleXRangeMaximum(3);

                    float barSpace = 0.1f;
                    float groupSpace = 0.5f;

                    barData.setBarWidth(0.8f);

                    barChart.invalidate();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(Integer.parseInt(intent.getStringExtra("Statics")) == 1){
            databaseReference_Place.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ArrayList Factors_name = new ArrayList();
                    ArrayList<BarEntry> visitors = new ArrayList<>();

                    String Factors = intent.getStringExtra("Factors");

                    Description description = new Description();

                    Factors_name.add("주거");
                    Factors_name.add("공장/창고");
                    Factors_name.add("노유자");
                    Factors_name.add("기타");
                    description.setText("장소별");

                    visitors.add(new BarEntry(0, Integer.parseInt(String.valueOf(snapshot.child("Dwelling_Place").getValue()))));
                    visitors.add(new BarEntry(1, Integer.parseInt(String.valueOf(snapshot.child("Factory_Place").getValue()))));
                    visitors.add(new BarEntry(2, Integer.parseInt(String.valueOf(snapshot.child("Senior_Place").getValue()))));
                    visitors.add(new BarEntry(3, Integer.parseInt(String.valueOf(snapshot.child("Etc_Place").getValue()))));

                    BarDataSet barDataSet = new BarDataSet(visitors, "요인별 색상");
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(24.7f);

                    BarData barData = new BarData(barDataSet);

                    barChart.setData(barData);


                    barChart.setDescription(description);
                    barChart.animateY(2000);

                    XAxis xAxis = barChart.getXAxis();
                    xAxis.setValueFormatter(new IndexAxisValueFormatter(Factors_name));
                    xAxis.setDrawGridLines(false);
                    xAxis.setDrawAxisLine(false);

                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setGranularity(1f);

                    xAxis.setLabelCount(visitors.size());
                    xAxis.setGranularityEnabled(true);

                    barChart.setDragEnabled(true);

                    float barSpace = 0.1f;
                    float groupSpace = 0.5f;

                    barData.setBarWidth(1f);

                    barChart.invalidate();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

}
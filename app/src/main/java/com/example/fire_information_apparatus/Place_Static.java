package com.example.fire_information_apparatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

public class Place_Static extends AppCompatActivity {

    private TableLayout tableLayout;
    static int count = 0;
    static int sum = 0;
    static float before_years = 0;
    double range = 0.1f;

    static int factory_sum = 0;
    static int Senior_sum = 0;
    static int Dwelling_sum = 0;
    static int Etc_sum = 0;

    static float all_sum = 0;

    private DatabaseReference databaseReference;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference_Place;

    String[] items = {"공장/창고", "주거", "노유자","기타"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_place_static);


        databaseReference = FirebaseDatabase.getInstance().getReference("Statistics");

        databaseReference.child("By_Place").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tableLayout = (TableLayout) findViewById(R.id.tableLayout);

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    for(DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()){
                        if(count < Integer.valueOf(String.valueOf(dataSnapshot2.getKey()))){
                            count = Integer.valueOf(String.valueOf(dataSnapshot2.getKey()));
                        }
                    }
                }
                for(int i = 2019; i <=count; i++) {
                    TableRow tableRow = new TableRow(getApplicationContext());
                    tableRow.setLayoutParams(new TableRow.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));

                    for(int j = 0; j < 7; j++){
                        if(j == 0){
                            TextView textView = new TextView(getApplicationContext());
                            textView.setText(String.valueOf(i));
                            textView.setGravity(Gravity.CENTER);
                            textView.setTextSize(15);
                            textView.setTextColor(Color.parseColor("#000000"));
                            tableRow.addView(textView);
                        }
                        else if(j == 1){
                            TextView textView = new TextView(getApplicationContext());
                            int factory = 0;

                            if(snapshot.child("Factory_Place").child(String.valueOf(i)).getKey() == null){
                                continue;
                            }

                            else{
                                for (DataSnapshot dataSnapshot : snapshot.child("Factory_Place").child(String.valueOf(i)).getChildren()){
                                    factory += Integer.valueOf(String.valueOf(dataSnapshot.getValue()));
                                }
                                textView.setText(String.valueOf(factory));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                tableRow.addView(textView);

                                sum += factory;
                                factory_sum += factory;
                            }

                        }
                        else if(j == 2){
                            TextView textView = new TextView(getApplicationContext());
                            int Senior_Place = 0;

                            if(snapshot.child("Senior_Place").child(String.valueOf(i)).getKey() == null){
                                continue;
                            }

                            else{
                                for (DataSnapshot dataSnapshot : snapshot.child("Senior_Place").child(String.valueOf(i)).getChildren()){
                                    Senior_Place += Integer.valueOf(String.valueOf(dataSnapshot.getValue()));
                                }
                                textView.setText(String.valueOf(Senior_Place));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                tableRow.addView(textView);

                                sum += Senior_Place;
                                Senior_sum += Senior_Place;
                            }
                        }
                        else if(j == 3){
                            TextView textView = new TextView(getApplicationContext());
                            int Dwelling_Place = 0;

                            if(snapshot.child("Dwelling_Place").child(String.valueOf(i)).getKey() == null){
                                continue;
                            }

                            else{
                                for (DataSnapshot dataSnapshot : snapshot.child("Dwelling_Place").child(String.valueOf(i)).getChildren()){
                                    Dwelling_Place += Integer.valueOf(String.valueOf(dataSnapshot.getValue()));
                                }
                                textView.setText(String.valueOf(Dwelling_Place));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                tableRow.addView(textView);

                                sum += Dwelling_Place;
                                Dwelling_sum += Dwelling_Place;
                            }
                        }
                        else if(j == 4){
                            TextView textView = new TextView(getApplicationContext());
                            int Etc_Place = 0;

                            if(snapshot.child("Etc_Place").child(String.valueOf(i)).getKey() == null){
                                continue;
                            }

                            else{
                                for (DataSnapshot dataSnapshot : snapshot.child("Etc_Place").child(String.valueOf(i)).getChildren()){
                                    Etc_Place += Integer.valueOf(String.valueOf(dataSnapshot.getValue()));
                                }
                                textView.setText(String.valueOf(Etc_Place));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setTextSize(15);
                                tableRow.addView(textView);

                                sum += Etc_Place;
                                Etc_sum += Etc_Place;
                            }
                        }
                        else if(j == 5){
                            TextView textView = new TextView(getApplicationContext());
                            textView.setText(String.valueOf(sum));
                            textView.setGravity(Gravity.CENTER);
                            textView.setTextSize(15);
                            textView.setTextColor(Color.parseColor("#000000"));
                            textView.setBackgroundColor(Color.parseColor("#BDECB6"));
                            tableRow.addView(textView);
                        }
                        else if(j == 6){
                            if(before_years == 0){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText("0%");
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#BDECB6"));
                                tableRow.addView(textView);
                                continue;
                            }
                            else{
                                if(sum > before_years){
                                    range = ((sum-before_years)/sum)*100;
                                }
                                else{
                                    range = ((before_years-sum)/sum)*100;
                                }

                            }

                            TextView textView = new TextView(getApplicationContext());
                            textView.setText((String.format("%.2f", range)) + "%");
                            textView.setGravity(Gravity.CENTER);
                            textView.setTextColor(Color.parseColor("#000000"));
                            textView.setBackgroundColor(Color.parseColor("#BDECB6"));
                            textView.setTextSize(15);
                            tableRow.addView(textView);
                        }

                    }
                    tableLayout.addView(tableRow);

                    sum = 0;
                    all_sum = (factory_sum + Senior_sum + Dwelling_sum + Etc_sum);
                    before_years = all_sum;
                }

                for(int i = 0; i < 2; i++){
                    TableRow tableRow = new TableRow(getApplicationContext());
                    tableRow.setLayoutParams(new TableRow.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));

                    for(int j = 0; j < 7; j++){
                        if(i == 0){
                            if(j == 0){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText("합계");
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setPadding(5,5,5,5);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                            if(j == 1){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText(String.valueOf(factory_sum));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                            if(j == 2){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText(String.valueOf(Senior_sum));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                            if(j == 3){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText(String.valueOf(Dwelling_sum));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                            if(j == 4){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText(String.valueOf(Etc_sum));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                            if(j == 5){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText(String.format("%.0f", (all_sum)));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#50bcdf"));
                                textView.setTextSize(15);

                                tableRow.addView(textView);
                            }

                        }
                        if(i == 1){
                            if(j == 0){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText("분포율");
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                            if(j == 1){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText(String.format("%.2f", (factory_sum/all_sum)*100) + "%");
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                            if(j == 2){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText(String.format("%.2f", (Senior_sum/all_sum)*100) + "%");
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                            if(j == 3){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText(String.format("%.2f", (Dwelling_sum/all_sum)*100) + "%");
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                            if(j == 4){
                                TextView textView = new TextView(getApplicationContext());
                                textView.setText(String.format("%.1f", (Etc_sum/all_sum)*100) + "%");
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(15);
                                textView.setTextColor(Color.parseColor("#000000"));
                                textView.setBackgroundColor(Color.parseColor("#ffff40"));
                                tableRow.addView(textView);
                            }
                        }

                    }
                    tableLayout.addView(tableRow);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d("postSnapshot14", String.valueOf(count));

        Spinner spinner = findViewById(R.id.Place_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        databaseReference_Place = database.getReference("Statistics").child("By_Place");
        BarChart barChart = findViewById(R.id.place_barChart);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList Place = new ArrayList();
                ArrayList<BarEntry> visitors = new ArrayList<>();
                Description description = new Description();

                if(position == 0){
                    databaseReference_Place.child("Factory_Place").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int num = 0;
                            //Log.d("postSnapshot1412", String.valueOf(snapshot.getKey()));
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                Log.d("postSnapshot", String.valueOf(postSnapshot.getKey()));
                                for(DataSnapshot postSnapshot2 : postSnapshot.getChildren()){
                                    //Log.d("postSnapshot1412", String.valueOf(postSnapshot2.getValue()));
                                    Place.add(String.valueOf((postSnapshot.getKey()) + "." + postSnapshot2.getKey()));
                                    visitors.add(new BarEntry(num, Float.parseFloat(String.valueOf(postSnapshot2.getValue()))));

                                    num += 1;
                                }

                            }
                            BarDataSet barDataSet = new BarDataSet(visitors, "");
                            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            barDataSet.setValueTextColor(Color.BLACK);
                            barDataSet.setValueTextSize(24.7f);

                            BarData barData = new BarData(barDataSet);

                            barChart.setData(barData);
                            description.setText("인위적 요인");

                            barChart.setDescription(null);
                            barChart.animateY(2000);

                            XAxis xAxis = barChart.getXAxis();
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(Place));
                            xAxis.setDrawGridLines(true);
                            xAxis.setDrawAxisLine(false);
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                            xAxis.setGranularity(1f);
                            xAxis.setTextSize(12f);
                            xAxis.setLabelCount(visitors.size());
                            xAxis.setGranularityEnabled(true);

                            barChart.setDragEnabled(true);
                            barChart.setVisibleXRangeMaximum(5);

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

                else if(position == 1){
                    databaseReference_Place.child("Dwelling_Place").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int num = 0;
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                Log.d("postSnapshot", String.valueOf(postSnapshot.getKey()));
                                for(DataSnapshot postSnapshot2 : postSnapshot.getChildren()){
                                    Place.add(String.valueOf((postSnapshot.getKey()) + "." + postSnapshot2.getKey()));
                                    visitors.add(new BarEntry(num, Float.parseFloat(String.valueOf(postSnapshot2.getValue()))));

                                    num += 1;
                                }

                            }
                            BarDataSet barDataSet = new BarDataSet(visitors, "");
                            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            barDataSet.setValueTextColor(Color.BLACK);
                            barDataSet.setValueTextSize(24.7f);

                            BarData barData = new BarData(barDataSet);

                            barChart.setData(barData);


                            barChart.setDescription(null);
                            barChart.animateY(2000);

                            XAxis xAxis = barChart.getXAxis();
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(Place));
                            xAxis.setDrawGridLines(true);
                            xAxis.setDrawAxisLine(false);

                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                            xAxis.setGranularity(1f);
                            xAxis.setTextSize(12f);
                            xAxis.setLabelCount(visitors.size());
                            xAxis.setGranularityEnabled(true);

                            barChart.setDragEnabled(true);
                            barChart.setVisibleXRangeMaximum(4);

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

                else if(position == 2){
                    databaseReference_Place.child("Senior_Place").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int num = 0;
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                Log.d("postSnapshot", String.valueOf(postSnapshot.getKey()));
                                for(DataSnapshot postSnapshot2 : postSnapshot.getChildren()){
                                    Place.add(String.valueOf((postSnapshot.getKey()) + "." + postSnapshot2.getKey()));
                                    visitors.add(new BarEntry(num, Float.parseFloat(String.valueOf(postSnapshot2.getValue()))));

                                    num += 1;
                                }

                            }
                            BarDataSet barDataSet = new BarDataSet(visitors, "");
                            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            barDataSet.setValueTextColor(Color.BLACK);
                            barDataSet.setValueTextSize(24.7f);

                            BarData barData = new BarData(barDataSet);

                            barChart.setData(barData);


                            barChart.setDescription(null);
                            barChart.animateY(2000);

                            XAxis xAxis = barChart.getXAxis();
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(Place));
                            xAxis.setDrawGridLines(true);
                            xAxis.setDrawAxisLine(false);
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                            xAxis.setGranularity(1f);
                            xAxis.setTextSize(12f);
                            xAxis.setLabelCount(visitors.size());
                            xAxis.setGranularityEnabled(true);

                            barChart.setDragEnabled(true);
                            barChart.setVisibleXRangeMaximum(5);

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

                else if(position == 3){
                    databaseReference_Place.child("Etc_Place").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int num = 0;
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                Log.d("postSnapshot", String.valueOf(postSnapshot.getKey()));
                                for(DataSnapshot postSnapshot2 : postSnapshot.getChildren()){
                                    Place.add(String.valueOf((postSnapshot.getKey()) + "." + postSnapshot2.getKey()));
                                    visitors.add(new BarEntry(num, Float.parseFloat(String.valueOf(postSnapshot2.getValue()))));

                                    num += 1;
                                }

                            }
                            BarDataSet barDataSet = new BarDataSet(visitors, "");
                            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                            barDataSet.setValueTextColor(Color.BLACK);
                            barDataSet.setValueTextSize(24.7f);

                            BarData barData = new BarData(barDataSet);

                            barChart.setData(barData);


                            barChart.setDescription(null);
                            barChart.animateY(2000);

                            XAxis xAxis = barChart.getXAxis();
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(Place));
                            xAxis.setDrawGridLines(true);
                            xAxis.setDrawAxisLine(false);
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setGranularity(1f);
                            xAxis.setTextSize(12f);
                            xAxis.setLabelCount(visitors.size());
                            xAxis.setGranularityEnabled(true);

                            barChart.setDragEnabled(true);
                            barChart.setVisibleXRangeMaximum(5);

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
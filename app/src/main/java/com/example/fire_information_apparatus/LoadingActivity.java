package com.example.fire_information_apparatus;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadingActivity extends Activity {

    TextView Quote;
    List Quote_list = new ArrayList();

    int max_num_value = 9;
    int min_num_value = 0;

    Random random = new Random();

    int randomNum = random.nextInt(max_num_value - min_num_value + 1) + min_num_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_loading);

        Quote_list.add("방심 속에 화재있고\n관심 속에 예방 있다.");
        Quote_list.add("불나는 곳 따로 없고 불조심은 너나 없다.");
        Quote_list.add("크고 작은 화재사고 알고 보면 습관 때문");
        Quote_list.add("화재예방 아무리 강조해도 지나치지 않습니다.");
        Quote_list.add("점검은 미리미리 조치는 바로바로");
        Quote_list.add("생활속의 불조심 화재없는 밝은 내일");
        Quote_list.add("우리집 안전, 화재감지기 설치부터");
        Quote_list.add("설마하면 큰일 날 불 조심하면 안전한 불");
        Quote_list.add("화재 예방으로 예방은 실천으로");
        Quote_list.add("작은 불은 대비부터 큰 불에는 대피먼저");

        Quote = findViewById(R.id.Quote);

        Log.d("Quote", Quote_list.get(randomNum).toString());
        String Quote_str = Quote_list.get(randomNum).toString();
        Quote.setText(Quote_list.get(randomNum).toString());

        startLoading();
    }
    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}
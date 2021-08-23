package com.example.fire_information_apparatus;


import android.app.Activity;
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
        setContentView(R.layout.activity_loading);

        Quote_list.add("일을 즐기면\n 일의 완성도가 높아진다.\n-아리스토텔레스");
        Quote_list.add("교육은 노후를 위한\n 최상의 양식이다.\n-아리스토텔레스");
        Quote_list.add("무지를 아는 것이\n곧 앎의 시작이다.\n-소크라테스");
        Quote_list.add("친구와 적은 있어야 한다.\n 친구는 충고를, 적은 경고를 해준다.\n-소크라테스");
        Quote_list.add("질문을 이해한다는 것은\n정답의 절반이다.\n-소크라테스");
        Quote_list.add("겉모습이란 속임수이다.\n-플라톤");
        Quote_list.add("남을 행복 되게 할 수 있는 사람만이\n또한 행복을 얻는다.\n-플라톤");
        Quote_list.add("마음을 행복하게 할 수 있는\n자만이 행복을 얻는다.\n-플라톤\n");
        Quote_list.add("성공의 핵심 요소는 인내심이다.\n-빌게이츠\n");
        Quote_list.add("삶이 웃기지 않는다면\n그것은 비극이다.\n-스티브호킹\n");

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
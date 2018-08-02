package com.example.junhyuk.sjsu_client;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SelectSportsActivity extends AppCompatActivity {
    /**
     1 = Tennis
     2 = Basketball
     3 = Soccer
     4 = Workout
     5 = Badminton
     6 = Jogging
     7 = Yoga
     8 = Cycling
     9 = Baseball
     10 = Bowling
     11 = Billiard
     12 = Surfing
    * */
    boolean [] isSelected;
    ActionBar actionBar;
    private int currentApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportschioce);

        hideBar();
        getToolbar();

        isSelected = new boolean[12];
        Log.e("SelectSportsActivity","Launch");
        for(int i =0; i<12; i++)
        {
            isSelected[i] = false;
        }
        Log.e("SelectSportsActivity","Image Go");

        findViewById(R.id.tennisBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.baseballBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.soccerBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.workoutBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.badmintonBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.joggingBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.yogaBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.cyclingBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.baseballBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.bowlingBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.billiardsBt).setOnClickListener(optionSelectListener);
        findViewById(R.id.surfingBt).setOnClickListener(optionSelectListener);

        //오케이 클릭 리스너
        findViewById(R.id.okBt).setOnClickListener(isOkListener);

    }

    Button.OnClickListener optionSelectListener =
            new Button.OnClickListener() {
                public void onClick(View v) {
                    switch(v.getId())
                    {
                        case R.id.tennisBt: {
                            if (isSelected[0]) {
                                isSelected[0] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[0] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.basketballBt:{
                            if (isSelected[1]) {
                                isSelected[1] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[1] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.soccerBt: {
                            if (isSelected[2]) {
                                isSelected[2] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[2] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.workoutBt:{
                            if (isSelected[3]) {
                                isSelected[3] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[3] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.badmintonBt:{
                            if (isSelected[4]) {
                                isSelected[4] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[4] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.joggingBt:{
                            if (isSelected[5]) {
                                isSelected[5] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[5] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.yogaBt:{
                            if (isSelected[6]) {
                                isSelected[6] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[6] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.cyclingBt:{
                            if (isSelected[7]) {
                                isSelected[7] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[7] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.baseballBt:{
                            if (isSelected[8]) {
                                isSelected[8] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[8] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.bowlingBt:{
                            if (isSelected[9]) {
                                isSelected[9] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[9] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.billiardsBt:{
                            if (isSelected[10]) {
                                isSelected[10] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[10] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                        case R.id.surfingBt:{
                            if (isSelected[11]) {
                                isSelected[11] = false;
                                // 버튼 이미지 변환 부분 삽입 (원래 대로)
                            } else {
                                isSelected[11] = true;
                                // 버튼 이미지 변환 부분 삽입 (색상 변환)
                            }
                            break;
                        }
                    }
                }
            };

    /*
    Button.onClickListener scrollUp =
            new Button.OnClickListener(){
                public void onClick(View v){

                }
            };
    Button.onClickListener scrollDown =
            new Button.OnClickListener(){
                public void onClick(View v){

                }
            };
    */

    Button.OnClickListener isOkListener =
            new Button.OnClickListener(){
                public void onClick(View v) {

                    Log.e("SelectSportsActivity","Mouse Event");

                    Intent intent = new Intent(SelectSportsActivity.this, RealMainActivity.class);
                    startActivity(intent);

                    Log.e("SelectSportsActivity","Mouse Event");

                }
            };

    public void hideBar() {
        //상하단바 없애기
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hideㅁ
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
    }

    public void getToolbar(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        actionBar = getSupportActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        actionBar.setTitle(null);
    }


}

package com.example.junhyuk.sjsu_client;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isSelected = new boolean[12];

        Log.e("SelectSportsActivity","Launch");

        for(int i =0; i<12; i++)
        {
            isSelected[i] = false;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportschioce);

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

       // findViewById(R.id.okBt).setOnClickListener(isOkListener); //오케이 클릭 리스너



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

                    //isSelect 배열을 통하여 선택된것이 무엇인지를 확인할 것.
                    //선택된것이 무엇이 있는지 확인하고, 화면 전환 -> isSelect Button 이 있음

                    /**
                     * TODO: 서버로 선택된 목록 데이터 종합 및 송신
                     * 받아온 데이터들을 여기서 종합한 후에
                     * 다음페이지로 화면 전환
                     */

                    //Intent intent = new Intent(SelectSportsActivity.this, SelectRoomActivity.class);
                    //startActivity(intent);
                    //-> 다음페이지로 이동
                }
            };


}

package com.jayaram.cardmatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class GameOver extends AppCompatActivity {


    private final ArrayList<gameData> gameDataArrayList = new ArrayList<>();
    private final ArrayList<gameData> fillBlanks = new ArrayList<>();
    private double avgProgress2, allTimeProg2;
    private String bestTime2, lastTime2;
    private int avgTime2, points, incorrect;
    private int avgHintsUsed2;
    private TextView txt_data, txt2, txt3, txt4, txt5, txt6 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        setTitle("Scoreboard");
        txt_data = findViewById(R.id.txt_data);
        txt2 = findViewById(R.id.txt_2);
        txt3 = findViewById(R.id.txt_3);
        txt4 = findViewById(R.id.txt_4);
        txt5 = findViewById(R.id.txt_5);
        txt6 = findViewById(R.id.txt_6);
//        int points = getIntent().getExtras().getInt("points");
//        tvPoints = findViewById(R.id.tvPoints);
//        tvPersonalBest = findViewById(R.id.tvPersonalBest);
//        tvPoints.setText("" + points);
//        sharedPreferences = getSharedPreferences("pref", 0);
//
//        int pointsSP = sharedPreferences.getInt("pointsSP", 0);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        if(points > pointsSP){
//            pointsSP = points;
//            editor.putInt("pointsSP", pointsSP);
//            editor.apply();
//        }
//        tvPersonalBest.setText("" + pointsSP);
//    }
//
//    public void restart(View view) {
//        Intent intent = new Intent(GameOver.this, Questions.class);
//        startActivity(intent);
//        finish();
//    }
//
//    public void exit(View view)
//    {
//        finish();
//    }

        gameViewModel gameViewModel = new ViewModelProvider(this).get(com.jayaram.cardmatcher.gameViewModel.class);
        gameViewModel.getAllGameData().observe(this, new Observer<List<gameData>>() {
            @Override
            public void onChanged(List<gameData> gameData) {
                gameDataArrayList.addAll(gameData);
                for(int i=0;i<gameDataArrayList.size();i++)
                {
                    if(gameDataArrayList.get(i).getGame().equals("Fill Blanks"))
                    fillBlanks.add(gameDataArrayList.get(i));
                }
                populate();






                // Toast.makeText(getApplicationContext(), "Hints :"+ cardGameArray.get(0).getHintsUsed(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent home = new Intent(getApplicationContext(),Home.class);
        startActivity(home);
    }
    private void populate() {


        if(fillBlanks.size()==0)
        {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
        else {
            findAverageData2();
            getOtherData2();
            setValues2();
        }

    }
    private void setValues2()

    {
        txt_data.setText("Best time : "+bestTime2);
        txt2.setText("Previous time : "+lastTime2 + " Average time : "+avgTime2);
        txt3.setText(" Score : "+points + "  Progress : " +avgProgress2);
        txt4.setText("Average incorrect answer : "+incorrect);
        txt5.setText("All time progress : "+allTimeProg2);
        txt6.setText("Average Hints : "+avgHintsUsed2);
    }

    private void getOtherData2()
    {
        allTimeProg2 = fillBlanks.get(0).getNetImprovement();
        bestTime2 = fillBlanks.get(0).getBestTime();
        lastTime2 = fillBlanks.get(0).getTime();
        points = fillBlanks.get(0).getScore();
    }


    private void findAverageData2()
    {
        int time=0, hints=0,wrong=0;
        double prog=0.0;
        for (int i = 0; i< fillBlanks.size(); i++)
        {
            time=time+calTime(fillBlanks.get(i).getTime());
            hints = hints + fillBlanks.get(i).getHintsUsed();
            prog = prog+ fillBlanks.get(i).getProgress();
            incorrect=wrong+fillBlanks.get(i).getIncorrectAns();
        }
        avgTime2 = time/ fillBlanks.size();
        avgProgress2 = prog/ fillBlanks.size();
        avgHintsUsed2 = hints/ fillBlanks.size();
        incorrect=incorrect/fillBlanks.size();
    }

    private int calTime(String time)
    {
        String a = "";
        String b = "";
        a = a+time.charAt(0);
        a = a+time.charAt(1);

        b = b+time.charAt(3);
        b = b+time.charAt(4);

        int min = Integer.parseInt(a);
        int sec = Integer.parseInt(b);
        return min*60+sec;
    }




}


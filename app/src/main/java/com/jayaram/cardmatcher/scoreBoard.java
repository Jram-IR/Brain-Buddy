package com.jayaram.cardmatcher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class scoreBoard extends AppCompatActivity {

    private TextView txt_data, txt2, txt3, txt4, txt5, txt6 ;
    private double avgProgress1, allTimeProg1;
    private int maximum, prevScore;
    private  int avgScore;
    private int avgHintsUsed1;
    private final ArrayList<gameData> simonGameArray = new ArrayList<>();
    private final ArrayList<gameData> gameDataArrayList = new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        setTitle("Scoreboard");
        int score;
        boolean max;


        Intent i = getIntent();
        txt_data = findViewById(R.id.txt_data);
        txt2 = findViewById(R.id.txt_2);
        txt3 = findViewById(R.id.txt_3);
        txt4 = findViewById(R.id.txt_4);
        txt5 = findViewById(R.id.txt_5);
        txt6 = findViewById(R.id.txt_6);
        gameViewModel gameViewModel = new ViewModelProvider(this).get(com.jayaram.cardmatcher.gameViewModel.class);
        gameViewModel.getAllGameData().observe(this, new Observer<List<gameData>>() {
            @Override
            public void onChanged(List<gameData> gameData) {
                gameDataArrayList.addAll(gameData);

                for(int i=0;i<gameDataArrayList.size();i++)
                {
                    if(gameDataArrayList.get(i).getGame().equals("Simon Says"))
                    simonGameArray.add(gameDataArrayList.get(i));
                }
                populateSimonSays();





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


    private void populateSimonSays() {

        if (simonGameArray.size() == 0) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        } else {
            getOtherData1();
            findAverageData1();
            setValues();
        }
    }
        private void setValues () {
//        String result="";
//        result = result+"Best Score: "+maximum+" Previous Score :"+prevScore +" Average Score :"+avgScore+" Average Progress :"+avgProgress1+
//                " All time progress : "+allTimeProg1+" Average Hints used "+avgHintsUsed1;
//        txt_data.setText(result);

            txt_data.setText("Best Score : " + maximum);
            txt2.setText("Previous Score : " + prevScore);
            txt3.setText("Average Score : " + avgScore);
            txt4.setText("Average Progress : " + avgProgress1);
            txt5.setText("All time progress : " + allTimeProg1);
            txt6.setText("Average Hints : " + avgHintsUsed1);

        }


    private void getOtherData1()
    {
        allTimeProg1 = simonGameArray.get(0).getNetImprovement();
        maximum = simonGameArray.get(0).getHighScore();
        prevScore = simonGameArray.get(0).getScore();
    }

    private void findAverageData1()
    {
        int score=0, hints=0;
        double prog=0.0;
        for (int i = 0; i< simonGameArray.size(); i++)
        {
            score=score+simonGameArray.get(i).getScore();
            hints = hints + simonGameArray.get(i).getHintsUsed();
            prog = prog+ simonGameArray.get(i).getProgress();
        }
        avgScore = score/ simonGameArray.size();
        avgProgress1 = prog/ simonGameArray.size();
        avgHintsUsed1 = hints/ simonGameArray.size();

    }

}
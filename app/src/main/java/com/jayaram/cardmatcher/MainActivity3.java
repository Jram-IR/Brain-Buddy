package com.jayaram.cardmatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private final ArrayList<gameData> gameDataArrayList = new ArrayList<>();
    private final ArrayList<gameData> cardGameArray = new ArrayList<>();
    private double avgProgress, allTimeProg;
    private String bestTime, lastTime;
    private  int avgTime;
    private int avgHintsUsed;
    private TextView txt_data, txt2, txt3, txt4, txt5, txt6 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("Scoreboard");
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
                    if(gameDataArrayList.get(i).getGame().equals("Card Matcher"))
                        cardGameArray.add(gameDataArrayList.get(i));
                }
                populateViewsCardGame();






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


    private void populateViewsCardGame() {


        if(cardGameArray.size()==0)
        {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
        else {
            findAverageData();
            getOtherData();
            setValues1();
        }

    }
    private void setValues1() {

//        String result = "";
//        result = result + "Best time: " + bestTime + " Last time :" + lastTime + " Average time :" + avgTime + " Average Progress :" + avgProgress +
//                " All time progress : " + allTimeProg + " Average Hints used " + avgHintsUsed;
//        txt_data.setText(result);
        txt_data.setText("Best time : "+bestTime);
        txt2.setText(" Previous time : "+lastTime);
        txt3.setText(" Average time : "+avgTime+" sec ") ;
        txt4.setText(" Progress : " +avgProgress);
        txt5.setText(" All time progress : "+allTimeProg);
        txt6.setText(" Average Hints : "+avgHintsUsed);

    }

    private void getOtherData() {
        allTimeProg = cardGameArray.get(0).getNetImprovement();
        bestTime = cardGameArray.get(0).getBestTime();
        lastTime = cardGameArray.get(0).getTime();
    }

    private void findAverageData() {
        int time=0, hints=0;
        double prog=0.0;
        for (int i = 0; i< cardGameArray.size(); i++)
        {
            time=time+calTime(cardGameArray.get(i).getTime());
            hints = hints + cardGameArray.get(i).getHintsUsed();
            prog = prog+ cardGameArray.get(i).getProgress();
        }
        avgTime = time/ cardGameArray.size();
        avgProgress = prog/ cardGameArray.size();
        avgHintsUsed = hints/ cardGameArray.size();

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
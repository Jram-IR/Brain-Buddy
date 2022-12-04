package com.jayaram.cardmatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class statsActivity extends AppCompatActivity {

  private Button bt1,bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        bt1 = findViewById(R.id.game_1);
        bt2 = findViewById(R.id.game_2);
        bt3 = findViewById(R.id.game_3);
       setTitle("Statistics");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() ,MainActivity3.class);
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() ,scoreBoard.class);
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() ,GameOver.class);
                startActivity(intent);
            }
        });

    }
//
//    private void loadGameData() {
//        for(int i=0;i<gameDataArrayList.size();i++)
//        {
//            if(gameDataArrayList.get(i).getGame().equals(gameType))
//            {
//                cardGameArray.add(gameDataArrayList.get(i));
//
//
//            }
//           else if(gameDataArrayList.get(i).getGame().equals(gameType))
//            {
//                simonGameArray.add(gameDataArrayList.get(i));
//
//
//            }
//            else if(gameDataArrayList.get(i).getGame().equals(gameType))
//            {
//                fillBlanks.add(gameDataArrayList.get(i));
//
//
//            }
//
//           populateViewsCardGame();
//            populateSimonSays();
//            populateFillBlanks();
//            if(gameType=="Card Matcher")
//            setValues1();
//            else if(gameType=="Simon Says")
//            setValues();
//            else if(gameType=="Fill Blanks")
//            setValues2();
//
//        }
//
//
//    }
//
//    private void populateViewsCardGame() {
//
//
//        if(cardGameArray.size()==0)
//        {
//            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            findAverageData();
//            getOtherData();
//        }
//
//    }
//
//    private void setValues1() {
//
////        String result = "";
////        result = result + "Best time: " + bestTime + " Last time :" + lastTime + " Average time :" + avgTime + " Average Progress :" + avgProgress +
////                " All time progress : " + allTimeProg + " Average Hints used " + avgHintsUsed;
////        txt_data.setText(result);
//        txt_data.setText("Best time : "+bestTime);
//        txt2.setText("Previous time : "+lastTime );
//        txt3.setText(" Average time : "+avgTime) ;
//        txt4.setText("Progress : " +avgProgress);
//        txt5.setText("All time progress : "+allTimeProg);
//        txt6.setText("Average Hints : "+avgHintsUsed);
//    }
//
//    private void getOtherData() {
//        allTimeProg = cardGameArray.get(0).getNetImprovement();
//        bestTime = cardGameArray.get(0).getBestTime();
//        lastTime = cardGameArray.get(0).getTime();
//    }
//
//    private void findAverageData() {
//        int time=0, hints=0;
//        double prog=0.0;
//        for (int i = 0; i< cardGameArray.size(); i++)
//        {
//            time=time+calTime(cardGameArray.get(i).getTime());
//            hints = hints + cardGameArray.get(i).getHintsUsed();
//            prog = prog+ cardGameArray.get(i).getProgress();
//        }
//        avgTime = time/ cardGameArray.size();
//        avgProgress = prog/ cardGameArray.size();
//        avgHintsUsed = hints/ cardGameArray.size();
//
//    }
//
//    private int calTime(String time)
//    {
//        String a = "";
//        String b = "";
//        a = a+time.charAt(0);
//        a = a+time.charAt(1);
//
//        b = b+time.charAt(3);
//        b = b+time.charAt(4);
//
//        int min = Integer.parseInt(a);
//        int sec = Integer.parseInt(b);
//        return min*60+sec;
//    }
//
//    private void populateSimonSays()
//    {
//
//      if(simonGameArray.size()==0)
//      {
//          Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
//      }
//      else
//      {
//          getOtherData1();
//          findAverageData1();
//      }
//
//
//
//    }
//
//    private void setValues() {
////        String result="";
////        result = result+"Best Score: "+maximum+" Previous Score :"+prevScore +" Average Score :"+avgScore+" Average Progress :"+avgProgress1+
////                " All time progress : "+allTimeProg1+" Average Hints used "+avgHintsUsed1;
////        txt_data.setText(result);
//
//        txt_data.setText("Best Score : "+maximum);
//        txt2.setText("Previous Score : "+prevScore);
//        txt3.setText("Average Score : "+avgScore);
//        txt4.setText("Average Progress : "+avgProgress1);
//        txt5.setText("All time progress : "+allTimeProg1);
//        txt6.setText("Average Hints : "+avgHintsUsed1);
//
//    }
//
//    private void getOtherData1()
//    {
//        allTimeProg1 = simonGameArray.get(0).getNetImprovement();
//        maximum = simonGameArray.get(0).getHighScore();
//        prevScore = simonGameArray.get(0).getScore();
//    }
//
//    private void findAverageData1()
//    {
//        int score=0, hints=0;
//        double prog=0.0;
//        for (int i = 0; i< simonGameArray.size(); i++)
//        {
//            score=score+simonGameArray.get(i).getScore();
//            hints = hints + simonGameArray.get(i).getHintsUsed();
//            prog = prog+ simonGameArray.get(i).getProgress();
//        }
//        avgScore = score/ simonGameArray.size();
//        avgProgress1 = prog/ simonGameArray.size();
//        avgHintsUsed1 = hints/ simonGameArray.size();
//
//    }
//
//    private void populateFillBlanks()
//    {
//
//
//        if(fillBlanks.size()==0)
//        {
//            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
//        }
//        else {
//
//            getOtherData2();
//            findAverageData2();
//        }
//
//    }
//
//
//    private void setValues2()
//
//    {
//        txt_data.setText("Best time : "+bestTime2);
//        txt2.setText("Previous time : "+lastTime2 + " Average time : "+avgTime2);
//        txt3.setText(" Score : "+points + "Progress : " +avgProgress2);
//        txt4.setText("Average incorrect ans : "+incorrect);
//        txt5.setText("All time progress : "+allTimeProg2);
//        txt6.setText("Average Hints : "+avgHintsUsed2);
//    }
//
//    private void getOtherData2()
//    {
//        allTimeProg2 = fillBlanks.get(0).getNetImprovement();
//        bestTime2 = fillBlanks.get(0).getBestTime();
//        lastTime2 = fillBlanks.get(0).getTime();
//        points = fillBlanks.get(0).getScore();
//    }
//
//
//    private void findAverageData2()
//    {
//        int time=0, hints=0,wrong=0;
//        double prog=0.0;
//        for (int i = 0; i< fillBlanks.size(); i++)
//        {
//            time=time+calTime(fillBlanks.get(i).getTime());
//            hints = hints + fillBlanks.get(i).getHintsUsed();
//            prog = prog+ fillBlanks.get(i).getProgress();
//            incorrect=wrong+fillBlanks.get(i).getIncorrectAns();
//        }
//        avgTime2 = time/ fillBlanks.size();
//        avgProgress2 = prog/ fillBlanks.size();
//        avgHintsUsed2 = hints/ fillBlanks.size();
//        incorrect=incorrect/fillBlanks.size();
//    }



}
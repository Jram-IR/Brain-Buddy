package com.jayaram.cardmatcher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Collections;

public class Questions extends AppCompatActivity
{
    TextView tvQuestion;
    EditText et_answer;
    Button bt_next, btn_hint;
    ImageView ivShowImage;
    private   double progress=0, allTimeProg = 0;
    private int incrorrect = 0,hints=0;
    private String bestTime = "00:00", currentTime = "00:00", prevTime = "00:00", firstTime="00:00";

    //    HashMap<String, Integer> map = new HashMap<>();//Hash map for storing Answer name and corresponding image resource ids
//    HashMap<String, String> map1 = new HashMap<>();//Hash map for storing Question and corresponding Answer name
//    ArrayList<String> answerList = new ArrayList<>();//Array list for storing answer name
//    ArrayList<String> questionList = new ArrayList<>();//Array list for storing questions
    int index;// to identify the correct question number
    TextView tvPoints;
    Chronometer chronometer;
    int points=0;
    ArrayList<QandA> qSet = new ArrayList<>();
    ArrayList<QandA> rSet = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        setTitle("Identify!");
        tvQuestion = findViewById(R.id.tvQuestion);
        chronometer =findViewById(R.id.timer_fb);
        ivShowImage = findViewById(R.id.ivShowImage);
        et_answer = findViewById(R.id.et_answer);
        bt_next = findViewById(R.id.bt_next);
        btn_hint = findViewById(R.id.button_hint);
        tvPoints = findViewById(R.id.tvPoints);
        index = 0;
        loadDetails();
        chronometer.start();
        btn_hint.setOnClickListener(view -> {
            hints++;
            Toast.makeText(getApplicationContext(), rSet.get(index).getAnswer(), Toast.LENGTH_SHORT).show();
        });
//        answerList.add("Cat");
//        answerList.add("Lion");
//        answerList.add("Peacock");
//        answerList.add("Dog");
//        answerList.add("Bird");
//        answerList.add("Parrot");
//        answerList.add("Cheetah");
//        answerList.add("Zebra");
//        answerList.add("Penguin");
//        questionList.add("The____drinks milk");
//        questionList.add("The____is the King of jungle");
//        questionList.add("The____dances in rain");
//        questionList.add("___is man's beast friend");
//        questionList.add("A____flies in the sky");
//        questionList.add("A____is green in color");
//        questionList.add("____is the fastest animal");
//        questionList.add("______has black and white stripes");
//        questionList.add("A____is a bird which cannot fly");
//        map1.put(questionList.get(0), answerList.get(0));
//        map1.put(questionList.get(1), answerList.get(1));
//        map1.put(questionList.get(2), answerList.get(2));
//        map1.put(questionList.get(3), answerList.get(3));
//        map1.put(questionList.get(4), answerList.get(4));
//        map1.put(questionList.get(5), answerList.get(5));
//        map1.put(questionList.get(6), answerList.get(6));
//        map1.put(questionList.get(7), answerList.get(7));
//        map1.put(questionList.get(8), answerList.get(8));
//        map.put(answerList.get(0), R.drawable.ic_cat);
//        map.put(answerList.get(1), R.drawable.ic_lion);
//        map.put(answerList.get(2), R.drawable.ic_peacock);
//        map.put(answerList.get(3), R.drawable.ic_dog);
//        map.put(answerList.get(4), R.drawable.ic_bird);
//        map.put(answerList.get(5), R.drawable.ic_parrot);
//        map.put(answerList.get(6), R.drawable.ic_cheetah);
//        map.put(answerList.get(7), R.drawable.ic_zebra);
//        map.put(answerList.get(8), R.drawable.ic_penguin);
//        Collections.shuffle(answerList);
//        Collections.shuffle(questionList);
//        points = 0;
//        startGame(); =
        qSet.add(new QandA("Cat","The____drinks milk",R.drawable.ic_cat));
        qSet.add(new QandA("Lion","The____is the King of jungle",R.drawable.ic_lion));
        qSet.add(new QandA("Peacock","The____dances in rain",R.drawable.ic_peacock));
        qSet.add(new QandA("Dog","___is man's best friend",R.drawable.ic_dog));
        qSet.add(new QandA("Jellyfish","A____is colorful but dangerous",R.drawable.ic_jellyfish));
        qSet.add(new QandA("Parrot","A____is green in color",R.drawable.ic_parrot));
        qSet.add(new QandA("Cheetah","____is the fastest animal",R.drawable.ic_cheetah));
        qSet.add( new QandA("Zebra","______has black and white stripes",R.drawable.ic_zebra));
        qSet.add(new QandA("Penguin","A____is a bird which cannot fly",R.drawable.ic_penguin));

        Shuffler shuffler = new Shuffler(qSet);
        shuffler.shuffleTheArray();
        rSet.addAll(shuffler.getShuffledArray());
        //Collections.shuffle(rSet);


        startGame();
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isEnd())
                {
                    Intent intent = new Intent(Questions.this, GameOver.class);
                    intent.putExtra("points",points);
                    Questions.this.startActivity(intent);
                    Questions.this.finish();

                    bt_next.setVisibility(View.INVISIBLE);
                }
                else
                {
                    checkAnswer(et_answer.getText().toString());
                    et_answer.setText("");
                }
                // Toast.makeText(Questions.this, "idx "+index, Toast.LENGTH_SHORT).show();
            }
        });


        startGame();
    }


    //        private void startGame()
//        {
//
//          generateQuestions(index);
//
//        }
//
//    private void generateQuestions(int index)
//    {
//        tvQuestion.setText(map1.get(questionList.get(index)));
//        ArrayList<String>  answerListTemp = (ArrayList<String>) answerList.clone();
//        String correctAnswer = answerList.get(index);
//        ivShowImage.setImageResource(map.get(answerList.get(index)));
//    }
//
//    public void answerEntered (View view)
//    {
//        String correctAnswer = answerList.get(index);
//        if(et_answer.equals(correctAnswer))
//        {
//            points++;
//            tvPoints.setText(points + " / " + answerList.size());//updating text view
//            tvResult.setText("CORRECT");                        //for points and results
//        }
//        else
//        {
//            tvResult.setText("WRONG");
//        }
//    }
//
//    public void nextQuestion(View view)
//    {
//        index++;
//        if(index > answerList.size() - 1)
//        {
//            ivShowImage.setVisibility(View.GONE);
//            Intent intent = new Intent(Questions.this, GameOver.class);
//            intent.putExtra("points",points);
//            startActivity(intent);
//            finish();
//        }
//        else
//        {
//            checkEnd();
//        }
//    }
//    private void checkEnd()
//    {
//        index++;
//        if(index > answerList.size() - 1)
//        {
//            ivShowImage.setVisibility(View.GONE);
//            Intent intent = new Intent(Questions.this,GameOver.class);
//            intent.putExtra("points",points);
//            startActivity(intent);
//            finish();
//        }
//        else
//        {
//            startGame();
//        }
//    }


    private void startGame() {
        tvQuestion.setText(rSet.get(index).getQuestion());
        ivShowImage.setImageResource(rSet.get(index).getImg());

    }

    private void populateQuestions() {

        index++;
        tvQuestion.setText(rSet.get(index).getQuestion());
        ivShowImage.setImageResource(rSet.get(index).getImg());

    }

    private void checkAnswer(String s) {


        if(s.equals(rSet.get(index).getAnswer()))
        {
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
            populateQuestions();
            points++;
            tvPoints.setText("Points : "+points);
        }
        else {
            incrorrect++;
            Toast.makeText(getApplicationContext(), "Try Again !", Toast.LENGTH_SHORT).show();

        }

    }

    private boolean isEnd()
    {
        int i = index;
        if(i==8)
        {
            finishGame();
            chronometer.stop();
            return  true;

        }
        else return false;
    }


    private void finishGame() {
        if (bestTime.equals("00:00")) {
            firstTime = bestTime = currentTime = chronometer.getText().toString();
            saveDetails();
        } else {
            currentTime = chronometer.getText().toString();
            isBest();

            progress = progress(prevTime);
            allTimeProg = progress(firstTime);
        }
        prevTime=currentTime;
        saveDetails();
        gameViewModel gameViewModel = new ViewModelProvider(this).get(com.jayaram.cardmatcher.gameViewModel.class);
        gameData gameData = new gameData("Fill Blanks",currentTime,bestTime,hints,progress,points,0,allTimeProg,incrorrect);
        gameViewModel.insert(gameData);
    }





    private double progress(String prevTime) {

        int x,y;
        x = calTime(prevTime);
        y = calTime(currentTime);
        int diff = x-y;
        double temp = (double) diff/x;
        temp=temp*100;
        Toast.makeText(getApplicationContext(), "prog "+temp, Toast.LENGTH_SHORT).show();
        return temp;

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


    private void saveDetails() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString("BEST_TIME", bestTime);
        ed.putString("PREV_TIME", currentTime);
        ed.putString("FIRST_TIME", firstTime);
        ed.apply();

    }


    private void loadDetails() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        bestTime = sharedPreferences.getString("BEST_TIME", "00:00");
        prevTime = sharedPreferences.getString("PREV_TIME", "00:00");
        firstTime = sharedPreferences.getString("FIRST_TIME", "00:00");


    }

    private void isBest() {
        int max = currentTime.compareTo(bestTime);
        if (max < 0) {
            bestTime = currentTime;
            saveDetails();
            loadDetails();
        }


    }


}

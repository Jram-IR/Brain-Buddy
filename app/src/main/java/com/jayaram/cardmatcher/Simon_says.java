package com.jayaram.cardmatcher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Random;

public class Simon_says extends AppCompatActivity implements View.OnClickListener {

    private ImageView seaHorse, oyster, turtle, starfish;
    private Button btn_hint;
    private final ArrayList<Integer> correctTaps = new ArrayList<>();
    private final Random r = new Random();
    private final LCG mRand = new LCG(0,4);
    private TextView pts;
    private boolean max=false,dismiss = true;
    private static  int sequenceIdx = 0, count=2, points=0,  end, i=0, hints=0;
    private  int firstPoints=-1,
            prevPoints=0,maximum=0;
    private int progress=0, netProgress=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Simon Says");
        setContentView(R.layout.activity_simon_says);
        load();
        btn_hint = findViewById(R.id.bt_Hint);
        pts = findViewById(R.id.txt_pts);
        seaHorse = findViewById(R.id.img_seaHorse);
        oyster = findViewById(R.id.img_oyster);
        turtle = findViewById(R.id.img_turtle);
        starfish = findViewById(R.id.img_starfish);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.brown));
        disableTheImages();
        startRound();

    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    @Override
    protected void onPause() {
        super.onPause();
        count = 2;
        points = 0;
        correctTaps.clear();

    }

    public void  startRound()
    {
        int c = count;
        correctTaps.clear();
        sequenceIdx=0;
        end =0;
         new CountDownTimer(c*1000L,1500) {
            @Override
            public void onTick(long l) {
                end++;
//                 int rand = r.nextInt(4);
//                correctTaps.add(rand);
                  int rand = mRand.getRandomNum();
                  highlight(rand);
                  correctTaps.add(rand);
            }

            @Override
            public void onFinish() {
                enableTheImages();
                Toast.makeText(getApplicationContext(), "GO!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    public void highlight(int rand) {
        switch (rand) {
            case 0:
                seaHorse.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake_anim));

                break;
            case 1:
                oyster.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake_anim));
                break;
            case 2:
                turtle.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake_anim));
                break;
            case 3:
                starfish.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake_anim));

                break;
        }
    }


    public void clickAnim(int img) {
        switch (img) {
            case 0:
                seaHorse.startAnimation(AnimationUtils.loadAnimation(this,R.anim.float_anim));
                break;
            case 1:
                oyster.startAnimation(AnimationUtils.loadAnimation(this,R.anim.float_anim));
                break;
            case 2:
                turtle.startAnimation(AnimationUtils.loadAnimation(this,R.anim.float_anim));
                break;
            case 3:
                starfish.startAnimation(AnimationUtils.loadAnimation(this,R.anim.float_anim));
                break;
        }
    }

    @Override
    public void onClick(View view) {
       if(view.getId()==R.id.img_seaHorse)
       {
           checkIsCorrect(0);
           clickAnim(0);

       }
       else if(view.getId()==R.id.img_oyster)
       {
           checkIsCorrect(1);
           clickAnim(1);

       }
       else if(view.getId()==R.id.img_turtle)
       {
           checkIsCorrect(2);
           clickAnim(2);

       }
       else if(view.getId()==R.id.img_starfish)
       {
           checkIsCorrect(3);
           clickAnim(3);

       }

       else if(view.getId() == R.id.bt_Hint)
       {

          redoSequence();

       }



    }

    private void redoSequence() {
        disableTheImages();
        int c = count;
        hints++;
        new CountDownTimer(c*1000L,1500) {
            @Override
            public void onTick(long l) {
                highlight(correctTaps.get(i));
                i++;
            }

            @Override
            public void onFinish() {
                i=0;
                enableTheImages();
            }
        }.start();
    }

    public void checkIsCorrect(int tap)
    {
        if(tap != correctTaps.get(sequenceIdx)) {
            GameOverPopUp();
            disableTheImages();
        }
        else checkSequence();
    }

    public void checkSequence()
    {
        if(sequenceIdx==end-1)
        {
            count++;
            end = 0;
            disableTheImages();
            points++;
            pts.setText("Points : "+points);
           successPopUp();
        }
        else sequenceIdx++;
    }

    public void enableTheImages()
    {
        seaHorse.setOnClickListener(this);
        oyster.setOnClickListener(this);
        turtle.setOnClickListener(this);
        starfish.setOnClickListener(this);
        btn_hint.setOnClickListener(this);

    }

    public void disableTheImages()
    {
        starfish.setOnClickListener(null);
        seaHorse.setOnClickListener(null);
        turtle.setOnClickListener(null);
        oyster.setOnClickListener(null);
        btn_hint.setOnClickListener(null);
    }

    public  void successPopUp()
    {


        new AlertDialog.Builder(this)
        .setTitle("Great work !")
                .setMessage("You got the right sequence !")
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                    dismiss=false;
                    startRound();
                })
                .setNegativeButton(android.R.string.no, (dialogInterface, i) -> { })
                .setOnDismissListener(dialogInterface -> {
                    if(dismiss)
                    {
                        finishGame();
                        openScoreBoard();
                    }
                })
                .show();
        dismiss = true;

    }

    @SuppressLint("SetTextI18n")
    public void GameOverPopUp()
    {


         new AlertDialog.Builder(this)
                .setTitle("Game Over"+"Points : "+points)
                .setMessage("Do you want to try again ?")
                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                    dismiss = false;
                    count = 2;
                    points = 0;
                    pts.setText("Points : 0");
                    startRound();

                })
                .setNegativeButton(android.R.string.no, (dialogInterface, i) -> {})
                 .setOnDismissListener(dialogInterface ->
                 {
                     if(dismiss) {

                         finishGame();
                         openScoreBoard();

                     }
                 })
                .show();
         dismiss=true;

    }

    private void finishGame() {

        if(firstPoints==-1)
        {
            firstPoints=points;
            maximum=points;

        }
        else isMax();
        calProgress();
        prevPoints=points;
        saveData();
    }

    private void calProgress() {
        progress = points-prevPoints;
        netProgress = points-firstPoints;

        createGameObj();
    }
    public void createGameObj()
    {
        double p = (double) progress;
        double q = (double) netProgress;
        gameViewModel gameViewModel = new ViewModelProvider(this).get(com.jayaram.cardmatcher.gameViewModel.class);
        gameData gameData = new gameData("Simon Says","0","0",hints,p,points,maximum,q,0);
        gameViewModel.insert(gameData);

    }

    public void openScoreBoard()
    {
        Intent intent = new Intent(getApplicationContext(),scoreBoard.class);
        intent.putExtra("POINTS",points);
        intent.putExtra("MAX",max);
        intent.putExtra("HIGH SCORE",maximum);
        startActivity(intent);

    }

    public void saveData()
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed=sharedPreferences.edit();
        ed.putInt("HIGH_SCORE",maximum);
        ed.putInt("FIRST_SCORE",firstPoints);
        ed.putInt("PREV_SCORE",points);
        ed.apply();

    }

    public void load()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        maximum = sharedPreferences.getInt("HIGH_SCORE",0);
        prevPoints = sharedPreferences.getInt("PREV_SCORE",0);
        firstPoints = sharedPreferences.getInt("FIRST_SCORE",-1);
    }

    public void isMax()
    {
        if(points>maximum)
        {
            maximum=points;
            saveData();
            load();
            max = true;
        }
    }


}



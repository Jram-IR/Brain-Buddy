package com.jayaram.cardmatcher;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageView iv_11, iv_12, iv_13, iv_14, iv_21, iv_22, iv_23, iv_24, iv_31, iv_32, iv_33, iv_34;
    Chronometer chronometer;
    Button hint;
    TextView best;

    private int hints = 0;
    private String bestTime = "00:00", currentTime = "00:00", prevTime = "00:00", firstTime="00:00";

    //array for the images
    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};

    //actual images
    int image101, image102, image103, image104, image105, image106,
            image201, image202, image203, image204, image205, image206;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
   double progress=0, allTimeProg = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDetails();
        setTitle("Card Matcher");
        chronometer = findViewById(R.id.timer);
        hint = findViewById(R.id.btn_hint);
        best = findViewById(R.id.txt_best);

        best.setText("");




        final Handler handler = new Handler(Looper.getMainLooper());
        hint.setOnClickListener(view -> {
            hints++;
            revealAllCards();
            handler.postDelayed(this::faceDown, 3000);
        });
        chronometer.start();


        iv_11 = findViewById(R.id.iv_11);
        iv_12 = findViewById(R.id.iv_12);
        iv_13 = findViewById(R.id.iv_13);
        iv_14 = findViewById(R.id.iv_14);

        iv_21 = findViewById(R.id.iv_21);
        iv_22 = findViewById(R.id.iv_22);
        iv_23 = findViewById(R.id.iv_23);
        iv_24 = findViewById(R.id.iv_24);

        iv_31 = findViewById(R.id.iv_31);
        iv_32 = findViewById(R.id.iv_32);
        iv_33 = findViewById(R.id.iv_33);
        iv_34 = findViewById(R.id.iv_34);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");

        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");

        iv_31.setTag("8");
        iv_32.setTag("9");
        iv_33.setTag("10");
        iv_34.setTag("11");

        //load the card images
        frontOfCardsResources();

        //shuffle the images
//        Collections.shuffle(Arrays.asList((cardsArray)));
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(cardsArray));
        Shuffler shuffler = new Shuffler();
        shuffler.setShuffleCards(arrayList);
        shuffler.shuffleTheCards();
        cardsArray =shuffler.getShuffleCards().toArray(new Integer[0]);

        iv_11.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_11, theCard);
        });

        iv_12.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_12, theCard);
        });

        iv_13.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_13, theCard);
        });

        iv_14.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_14, theCard);
        });

        iv_21.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_21, theCard);
        });

        iv_22.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_22, theCard);
        });

        iv_23.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_23, theCard);
        });

        iv_24.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_24, theCard);
        });

        iv_31.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_31, theCard);
        });

        iv_32.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_32, theCard);
        });

        iv_33.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_33, theCard);
        });

        iv_34.setOnClickListener(view -> {
            int theCard = Integer.parseInt((String) view.getTag());
            doStuff(iv_34, theCard);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadDetails();
    }

    //sets the correct image to the image view
    private void doStuff(ImageView iv, int theCard) {
        if (cardsArray[theCard] == 101) {
            iv.setImageResource(image101);
        } else if (cardsArray[theCard] == 102) {
            iv.setImageResource(image102);
        } else if (cardsArray[theCard] == 103) {
            iv.setImageResource(image103);
        } else if (cardsArray[theCard] == 104) {
            iv.setImageResource(image104);
        } else if (cardsArray[theCard] == 105) {
            iv.setImageResource(image105);
        } else if (cardsArray[theCard] == 106) {
            iv.setImageResource(image106);
        } else if (cardsArray[theCard] == 201) {
            iv.setImageResource(image201);
        } else if (cardsArray[theCard] == 202) {
            iv.setImageResource(image202);
        } else if (cardsArray[theCard] == 203) {
            iv.setImageResource(image203);
        } else if (cardsArray[theCard] == 204) {
            iv.setImageResource(image204);
        } else if (cardsArray[theCard] == 205) {
            iv.setImageResource(image205);
        } else if (cardsArray[theCard] == 206) {
            iv.setImageResource(image206);
        }
// checks which image is selected and saves it to temporary variable
        if (cardNumber == 1) {
            firstCard = cardsArray[theCard];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = theCard;

            iv.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = cardsArray[theCard];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = theCard;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);

            Handler handler = new Handler();
            //checks if the selected images are equal
            handler.postDelayed(this::calculate, 1000);

        }
    }

    private void calculate() {
        //if images are equal it removes them and then adds points
        if (firstCard == secondCard) {
            if (clickedFirst == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 4) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 5) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 6) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 7) {
                iv_24.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 8) {
                iv_31.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 9) {
                iv_32.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 10) {
                iv_33.setVisibility(View.INVISIBLE);
            } else if (clickedFirst == 11) {
                iv_34.setVisibility(View.INVISIBLE);
            }


            if (clickedSecond == 0) {
                iv_11.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 1) {
                iv_12.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 2) {
                iv_13.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 3) {
                iv_14.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 4) {
                iv_21.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 5) {
                iv_22.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 6) {
                iv_23.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 7) {
                iv_24.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 8) {
                iv_31.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 9) {
                iv_32.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 10) {
                iv_33.setVisibility(View.INVISIBLE);
            } else if (clickedSecond == 11) {
                iv_34.setVisibility(View.INVISIBLE);
            }

        } else {
            faceDown();


        }

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);

        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);

        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);

        //checks if the game is over
        checkEnd();
    }

    private void faceDown() {
        iv_11.setImageResource(R.drawable.q);
        iv_12.setImageResource(R.drawable.q);
        iv_13.setImageResource(R.drawable.q);
        iv_14.setImageResource(R.drawable.q);

        iv_21.setImageResource(R.drawable.q);
        iv_22.setImageResource(R.drawable.q);
        iv_23.setImageResource(R.drawable.q);
        iv_24.setImageResource(R.drawable.q);

        iv_31.setImageResource(R.drawable.q);
        iv_32.setImageResource(R.drawable.q);
        iv_33.setImageResource(R.drawable.q);
        iv_34.setImageResource(R.drawable.q);
    }

    private void checkEnd() {
        if (iv_11.getVisibility() == View.INVISIBLE &&
                iv_12.getVisibility() == View.INVISIBLE &&
                iv_13.getVisibility() == View.INVISIBLE &&
                iv_14.getVisibility() == View.INVISIBLE &&
                iv_21.getVisibility() == View.INVISIBLE &&
                iv_22.getVisibility() == View.INVISIBLE &&
                iv_23.getVisibility() == View.INVISIBLE &&
                iv_24.getVisibility() == View.INVISIBLE &&
                iv_31.getVisibility() == View.INVISIBLE &&
                iv_32.getVisibility() == View.INVISIBLE &&
                iv_33.getVisibility() == View.INVISIBLE &&
                iv_34.getVisibility() == View.INVISIBLE) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder
                    .setTitle("Game Over")
                    .setMessage("Play again ?")
                    .setCancelable(false)
                    .setPositiveButton("NEW", (dialogInterface, i) -> {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("EXIT", (dialogInterface, i) -> finish());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            chronometer.stop();
            finishGame();


        }
    }

    private void finishGame() {
        if (bestTime.equals("00:00")) {
            firstTime = bestTime = currentTime = chronometer.getText().toString();
            saveDetails();
        }
        else {
            currentTime = chronometer.getText().toString();
            isBest();

            progress=progress(prevTime);
            allTimeProg = progress(firstTime);
        }
        prevTime=currentTime;
        saveDetails();
        gameViewModel gameViewModel = new ViewModelProvider(this).get(com.jayaram.cardmatcher.gameViewModel.class);
        gameData gameData = new gameData("Card Matcher",currentTime,bestTime,hints,progress,0,0,allTimeProg,0);
        gameViewModel.insert(gameData);
        Intent i =new Intent(MainActivity.this, MainActivity3.class);
        startActivity(i);
    }

    private void frontOfCardsResources() {
        image101 = R.drawable.image101;
        image102 = R.drawable.image102;
        image103 = R.drawable.image103;
        image104 = R.drawable.image104;
        image105 = R.drawable.image105;
        image106 = R.drawable.image106;
        image201 = R.drawable.image101;
        image202 = R.drawable.image102;
        image203 = R.drawable.image103;
        image204 = R.drawable.image104;
        image205 = R.drawable.image105;
        image206 = R.drawable.image106;
    }

    private void revealAllCards() {
        doStuff(iv_11, Integer.parseInt((String) iv_11.getTag()));
        doStuff(iv_12, Integer.parseInt((String) iv_12.getTag()));
        doStuff(iv_13, Integer.parseInt((String) iv_13.getTag()));
        doStuff(iv_14, Integer.parseInt((String) iv_14.getTag()));


        doStuff(iv_21, Integer.parseInt((String) iv_21.getTag()));
        doStuff(iv_22, Integer.parseInt((String) iv_22.getTag()));
        doStuff(iv_23, Integer.parseInt((String) iv_23.getTag()));
        doStuff(iv_24, Integer.parseInt((String) iv_24.getTag()));


        doStuff(iv_31, Integer.parseInt((String) iv_31.getTag()));
        doStuff(iv_32, Integer.parseInt((String) iv_32.getTag()));
        doStuff(iv_33, Integer.parseInt((String) iv_33.getTag()));
        doStuff(iv_34, Integer.parseInt((String) iv_34.getTag()));
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
}

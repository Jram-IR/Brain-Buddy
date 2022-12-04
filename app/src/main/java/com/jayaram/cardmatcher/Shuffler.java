package com.jayaram.cardmatcher;

import java.util.ArrayList;

public class Shuffler {
    ArrayList<QandA> shuffleArrayQandA;
    ArrayList<Integer> shuffleCards;

    Shuffler() {
    }

    Shuffler(ArrayList<QandA> randArray) {
        shuffleArrayQandA = randArray;
    }

    public void setShuffleCards(ArrayList<Integer> randArray) {
        shuffleCards = randArray;
    }


    public void shuffleTheArray() {
        int last_idx = shuffleArrayQandA.size() - 1;
        LCG lcg = new LCG(0, last_idx);
        while (last_idx > 0) {
            lcg.setBound(0, last_idx);
            lcg.generateRandom();
            int randIdx = lcg.getRandomNum();
            QandA temp = shuffleArrayQandA.get(last_idx);
            shuffleArrayQandA.set(last_idx, shuffleArrayQandA.get(randIdx));
            shuffleArrayQandA.set(randIdx, temp);
            last_idx--;
        }


    }

    public void shuffleTheCards() {
        int last_idx = shuffleCards.size() - 1;
        LCG lcg = new LCG(0, last_idx);
        while (last_idx > 0) {
            lcg.setBound(0, last_idx);
            lcg.generateRandom();
            int randIdx = lcg.getRandomNum();
            int temp = shuffleCards.get(last_idx);
            shuffleCards.set(last_idx, shuffleCards.get(randIdx));
            shuffleCards.set(randIdx, temp);
            last_idx--;
        }


    }


    public ArrayList<QandA> getShuffledArray() {
        return shuffleArrayQandA;
    }

    public ArrayList<Integer> getShuffleCards() {
        return shuffleCards;
    }


}

    //    public void shuffleRand()
//    {
//        Random r = new Random();
//        int last_idx = shuffleArrayQandA.size()-1;
//        while(last_idx>0)
//        {
//
//            int randIdx = r.nextInt(last_idx+1);
//            QandA temp = shuffleArrayQandA.get(last_idx);
//            shuffleArrayQandA.set(last_idx, shuffleArrayQandA.get(randIdx)) ;
//            shuffleArrayQandA.set(randIdx,temp);
//            last_idx--;
//        }
//
//
//    }
//
//    public String getShuffledArray()
//    {
//        StringBuilder str = new StringBuilder();
//        for (int i = 0; i< shuffleArrayQandA.size();i++)
//        {
//            str.append(shuffleArrayQandA.get(i));
//            Log.d("test", "getShuffledTheArray: "+shuffleArrayQandA.get(i));
//        }
//
//        return String.valueOf(str);
//    }


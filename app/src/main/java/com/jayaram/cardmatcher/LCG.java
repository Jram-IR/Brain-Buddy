package com.jayaram.cardmatcher;

import android.util.Log;


public class LCG {

    private long m = 1;
    int lb, ub;
    private long x0 = System.currentTimeMillis();
    private int randomNum;

    LCG(int lb, int ub) {
        this.lb = lb;
        this.ub = ub;
        initializeMod();
    }


    public void setBound(int lb, int ub)
    {
        this.lb = lb;
        this.ub = ub;
    }

    public void generateRandom() {

        // initialing m = 2^32
        long c = 1,a = 22695477,xn;
        xn = (long) ((long) ((a * x0) + c) % m);
        x0 = xn;
        normalize(xn, lb, ub + 1);
        Log.d("mTAG", "generateRandom: "+ xn);
    }

    private void initializeMod()
    {
        for (int i = 0; i < 31; i++) {
            m = m * 2;
        }
    }
    public void normalize(long xn, int lb, int ub) {
        int diff = ub - lb;
        randomNum = (int) (diff * (float) xn / m + lb);
    }

    public int getRandomNum() {
        generateRandom();
        return randomNum;
    }
}

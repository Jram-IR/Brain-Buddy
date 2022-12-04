package com.jayaram.cardmatcher;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gameData_table")
public class gameData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String game;

    private final String  time, bestTime;
         private final int   incorrectAns;

    private final int hintsUsed,score, highScore;

    private final double progress,  netImprovement;

    public gameData(String game, String time, String bestTime,
                     int hintsUsed, double progress,
                     int score, int highScore, double netImprovement,int incorrectAns)
    {
        this.game = game;
        this.time = time;
        this.bestTime = bestTime;
        this.hintsUsed = hintsUsed;
        this.progress = progress;
        this.score = score;
        this.highScore = highScore;
        this.netImprovement = netImprovement;
        this.incorrectAns = incorrectAns;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getBestTime() {
        return bestTime;
    }

    public int getHintsUsed() {
        return hintsUsed;
    }

    public double getProgress() {
        return progress;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public double getNetImprovement() {
        return netImprovement;
    }

    public int getIncorrectAns() {
        return incorrectAns;
    }

    public String getGame() {
        return game; }

    @NonNull
    @Override
    public String toString() {
        return "gameData{" +
                "id=" + id +
                ", game='" + game + '\'' +
                ", time='" + time + '\'' +
                ", bestTime='" + bestTime + '\'' +
                ", hintsUsed=" + hintsUsed +
                ", progress=" + progress +
                ", score=" + score +
                ", highScore=" + highScore +
                ", netImprovement=" + netImprovement +
                '}';
    }
}

package com.jayaram.cardmatcher;




public class QandA {

    private final String question;
    private final String answer;
    private final int img;

    public QandA(String answer, String question, int img) {
        this.question = question;
        this.answer = answer;
        this.img = img;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getImg() {
        return img;
    }
}


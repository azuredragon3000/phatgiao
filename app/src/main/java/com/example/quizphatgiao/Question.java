package com.example.quizphatgiao;

public class Question {
    String question;
    String ans1;
    String ans2;
    String ans3;
    String ans4;
    int result;

    public Question(String question, String ans1, String ans2, String ans3, String ans4 , int result){
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.result = result;
    }

    public Question() {
        this.question = "";
        this.ans1 = "";
        this.ans2 = "";
        this.ans3 = "";
        this.ans4 = "";
        this.result = 0;
    }
}

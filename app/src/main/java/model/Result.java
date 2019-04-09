package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Result {
    private ArrayList<Answer> answers;
    private int correctAnswer = 0;
    private int wrongAnswer = 0;
    private double percentageCorrectAnswer = 0;
    private double percentageWrongAnswer = 0;
    private String questionResume = "";

    public Result(ArrayList<Answer> answers) {
        this.answers = answers;
    }
    DecimalFormat df = new DecimalFormat("#.0");


    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public String getQuestionResume() {
        return questionResume;
    }

    public void getScore(){
        for(int x = 0; x < answers.size();x++ ){
            questionResume += answers.get(x).toString();
            if (answers.get(x).getPoint()){
                correctAnswer++;
            }else{
                wrongAnswer++;
            }
        }
        percentageCorrectAnswer = ((double)correctAnswer / (double)answers.size()) * (double)100;
        percentageWrongAnswer = (double)100 - percentageCorrectAnswer;
    }


    @Override
    public String toString() {
        return "Correct Answers   =  " + df.format(percentageCorrectAnswer) + " %\n\n" +
                "Wrong Answers   =  " + df.format(percentageWrongAnswer) + " %";

    }
}

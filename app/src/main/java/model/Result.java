package model;

import android.util.Log;
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
            Log.i("Answer was       :", Boolean.toString(answers.get(x).getPoint()));
            if (answers.get(x).getPoint()){
                correctAnswer++;
            }else{
                wrongAnswer++;
            }
        }
        Log.i("Correct       :", Integer.toString(correctAnswer));
        Log.i("array size       :", Integer.toString(answers.size()));
        percentageCorrectAnswer = ((double)correctAnswer / (double)answers.size()) * (double)100;
        Log.i("Correct       :", Double.toString(percentageCorrectAnswer));
        percentageWrongAnswer = (double)100 - percentageCorrectAnswer;
    }


    @Override
    public String toString() {
        return "correct Answers   =  " + df.format(percentageCorrectAnswer) + " %\n\n" +
                "wrong Answers   =  " + df.format(percentageWrongAnswer)+ " %";

    }
}

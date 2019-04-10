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
    private int totalAnsweredQuestions = 0;
    private int totalSeconds = 0;
    private int totalDuration = 0;
    private int elapsedTime = 0;
    private float speed = 0;

//    public Result(ArrayList<Answer> answers) {
//        this.answers = answers;
//    }

    public Result() {

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
//        for(int x = 0; x < answers.size();x++ ){
        for(int x = 0; x < Answer.listAnswers.size();x++ ){
//            questionResume += answers.get(x).toString();
//            questionResume += Answer.listAnswers.get(x).toString();
            elapsedTime += Answer.listAnswers.get(x).getSeconds();
            if (Answer.listAnswers.get(x).getPoint()){
                correctAnswer++;
            }else{
                wrongAnswer++;
            }

            if (Answer.listAnswers.get(x).getSeconds() != 10){
                totalAnsweredQuestions++;
            }


        }
        percentageCorrectAnswer = ((double)correctAnswer / (double)Answer.listAnswers.size()) * (double)100;
        percentageWrongAnswer = (double)100 - percentageCorrectAnswer;

        totalDuration = 10 * Answer.listAnswers.size();
        speed = (float) elapsedTime / (float) totalDuration;
    }


    @Override
    public String toString() {
        return "Total Questions   =  " + Answer.listAnswers.size() + " \n" +
                "Total Answers   =  " + totalAnsweredQuestions + " \n" +
                "Test time   =  " + totalDuration + " Seconds\n" +
                "Elapsed time   =  " + elapsedTime + " Seconds\n" +
                "Correct Answers   =  " + df.format(percentageCorrectAnswer) + " %\n" +
                "Wrong Answers   =  " + df.format(percentageWrongAnswer) + " % \n" +
                "Answer Speed  =  " + speed + " Seconds";

    }
}

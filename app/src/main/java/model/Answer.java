package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Answer implements Serializable {
    private Validate answers;
    private String question;
    private boolean point;
    private int seconds;
    private final String LINE = "\n----------------------------------------------------------------\n";
    public static ArrayList<Answer> listAnswers = new ArrayList<>();


    public Answer(Validate answers, String question, boolean point, int seconds) {
        this.answers = answers;
        this.question = question;
        this.point = point;
        this.seconds = seconds;
        listAnswers.add(this);
    }


    public Validate getAnswers() {
        return answers;
    }

    public void setAnswers(Validate answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getPoint() {
        return point;
    }

    public void setPoint(boolean point) {
        this.point = point;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

//    @Override
//    public String toString() {
//        return question + "\n" + answers.toString() + " : "+ point + "\n" + "Answer Time = " + seconds + "sec." + "\n\n";
//
//    }

    @Override
    public String toString() {
        return question + "  ||  " + answers.toString() + " : " + point + "  ||  " + "Time = " + seconds + " Sec." + LINE;

    }
}

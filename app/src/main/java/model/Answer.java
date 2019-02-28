package model;

import java.io.Serializable;

public class Answer implements Serializable {
    private Validate answers;
    private String question;
    private boolean point;


    public Answer(Validate answers, String question, boolean point) {
        this.answers = answers;
        this.question = question;
        this.point = point;
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

    @Override
    public String toString() {
        return question + "\n " + answers.toString() + " : "+ point + "\n\n";

    }
}

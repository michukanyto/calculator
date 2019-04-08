package com.appsmontreal.calc;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

public class Animate {

    public enum Points{
        POINT,
        FAILURE
    }

    private boolean answer;
    private int counter;

    public Animate(boolean answer) {
        this.answer = answer;
    }


    public void displayPoints(TextView textViewPoints){
        if(answer){
            textViewPoints.setText(Points.POINT.name());
            textViewPoints.setTextColor(Color.GREEN);
            Log.i("----------","true answer");
        }else{
            textViewPoints.setText(Points.FAILURE.name());
            textViewPoints.setTextColor(Color.RED);
        }
        textViewPoints.animate().rotationX(720).setDuration(2000);

    }

}

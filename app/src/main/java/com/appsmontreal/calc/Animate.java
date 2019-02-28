package com.appsmontreal.calc;

import android.graphics.Color;
import android.widget.TextView;

public class Animate {

    public enum Points{
        POINT,
        FAILURE
    }

    private boolean answer;

    public Animate(boolean answer) {
        this.answer = answer;
    }


    public void displayPoints(TextView textViewPoints){
        if(answer){
            textViewPoints.setText(Points.POINT.name());
            textViewPoints.setTextColor(Color.GREEN);
        }else{
            textViewPoints.setText(Points.FAILURE.name());
            textViewPoints.setTextColor(Color.RED);
        }
        textViewPoints.animate().rotationX(720).setDuration(2000);
//        textViewPoints.animate().setListener(null);
//        textViewPoints.getAnimation().cancel();

    }

}

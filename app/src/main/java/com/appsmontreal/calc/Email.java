package com.appsmontreal.calc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appsmontreal.calc.ResultsActivity;
import com.jjoe64.graphview.GraphView;

import model.FileResultsManagement;

public class Email  {

    private Context context;
    private TextView percentage;
    private String answerResume;
    private GraphView graph;


    public Email(Context context, TextView percentage, String answerResume, GraphView graph) {
        this.context = context;
        this.percentage = percentage;
        this.answerResume = answerResume;
        this.graph = graph;
    }

    public void sendEmail(Activity caller){
        Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
        intentEmail.setType("message/rfc822");
        intentEmail.setData(Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL  , new String[]{"escobar.marlon@gmail.com"});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Calculator Score");
//        intentEmail.putExtra(Intent.)
        intentEmail.putExtra(Intent.EXTRA_TEXT   , "CALCULATOR SCORE\n" +
                "=======================\n" +
                percentage.getText().toString() + "\n\n" +
                answerResume.toString() + "\n\n");

        try {
            caller.startActivity(Intent.createChooser(intentEmail, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(caller, "Email service was not found in this phone.", Toast.LENGTH_SHORT).show();
        }
    }
}

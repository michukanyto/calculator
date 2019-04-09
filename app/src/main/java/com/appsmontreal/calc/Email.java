package com.appsmontreal.calc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.appsmontreal.calc.ResultsActivity;
import com.jjoe64.graphview.GraphView;

import java.io.File;
import java.util.ArrayList;

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
        FileResultsManagement fileResultsManagement = new FileResultsManagement(context);

        ArrayList<String> data = fileResultsManagement.readFromResultFile();
        File fileLocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), FileResultsManagement.FILENAME);
        Uri path = Uri.fromFile(fileLocation);
        Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
        intentEmail.setType("message/rfc822");
        intentEmail.setData(Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL  , new String[]{"destination@gmail.com"});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Calculator Score");
        intentEmail.putExtra(Intent.EXTRA_STREAM, path);
        intentEmail.putExtra(Intent.EXTRA_TEXT   , "CALCULATOR SCORE\n" +
                "=======================\n" +
                percentage.getText().toString() + "\n\n" +
//                answerResume.toString() + "\n\n");
                data + "\n\n");

        try {
            caller.startActivity(Intent.createChooser(intentEmail, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(caller, "Email service was not found in this phone.", Toast.LENGTH_SHORT).show();
        }
    }
}

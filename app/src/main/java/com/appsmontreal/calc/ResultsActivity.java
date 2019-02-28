package com.appsmontreal.calc;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;


import java.util.ArrayList;

import model.Answer;
import model.Result;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Answer> answers;
    Result result;
    GraphView graph;
    Email email;
    Sound play;
    TextView textViewPercentage;
    EditText editTextResult;
    Button buttonBack;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        answers = (ArrayList<Answer>) getIntent().getExtras().getSerializable(MainActivity.KEY);
        initialize();
    }


    private void initialize() {
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonBack.setOnClickListener(this);
        buttonSend.setOnClickListener(this);
        textViewPercentage = (TextView) findViewById(R.id.textViewPercentage);
        editTextResult = (EditText) findViewById(R.id.editTextResult);
        graph = (GraphView) findViewById(R.id.graph);
        result = new Result(answers);
        printInfo();
        showGraph();
        play = new Sound(this);
//        email = new Email(this,textViewPercentage,editTextResult,graph);

    }


    private void printInfo() {
        result.getScore();
        textViewPercentage.setText(result.toString());
        editTextResult.setText(result.getQuestionResume());

    }


    private void showGraph() {
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, result.getCorrectAnswer()),
                new DataPoint(3, result.getWrongAnswer()),
        });
        graph.addSeries(series);

        // styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 50);
            }
        });

        series.setSpacing(1);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Correct Answers", "", "Wrong Answers"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonBack: finish(); play.soundGoBack(); break;
            case R.id.buttonSend: sendEmail(); play.soundGoForward(); break;
            default: break;
        }

    }


    public void sendEmail(){
        Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
        intentEmail.setType("message/rfc822");
        intentEmail.setData(Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL  , new String[]{""});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Calculator Score");
        intentEmail.putExtra(Intent.EXTRA_TEXT   , "CALCULATOR SCORE\n" +
                "=======================" +
                textViewPercentage.getText().toString() + "\n\n" +
                editTextResult.getText().toString() + "\n\n");

        try {
            startActivity(Intent.createChooser(intentEmail, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Email service was not found in this phone.", Toast.LENGTH_SHORT).show();
        }
    }
}

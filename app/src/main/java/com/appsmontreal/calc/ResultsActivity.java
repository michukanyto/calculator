package com.appsmontreal.calc;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;


import java.util.ArrayList;

import model.Answer;
import model.FileResultsManagement;
import model.Result;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {
    Result result;
    GraphView graph;
    Email email;
    Sound play;
    TextView textViewPercentage;
    Spinner spinnerResult;
    Button buttonBack;
    Button buttonSend;
    ArrayAdapter arrayAdapter;
    FileResultsManagement fileResultsManagement;
    ArrayList<String> listAnswers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        initialize();
    }


    private void initialize() {
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonBack.setOnClickListener(this);
        buttonSend.setOnClickListener(this);
        textViewPercentage = (TextView) findViewById(R.id.textViewPercentage);
        spinnerResult = (Spinner) findViewById(R.id.spinnerResult);
        graph = (GraphView) findViewById(R.id.graph);
        result = new Result();
        fileResultsManagement = new FileResultsManagement(this);
        listAnswers = fileResultsManagement.readFromResultFile();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listAnswers);
        printInfo();
        showGraph();
        play = new Sound(this);
        email = new Email(this,textViewPercentage,Answer.listAnswers.toString(),graph);


    }


    private void printInfo() {
        result.getScore();
        textViewPercentage.setText(result.toString());
        spinnerResult.setAdapter(arrayAdapter);

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
            case R.id.buttonBack:
                finish();
                play.soundGoBack();
                break;
            case R.id.buttonSend:
                email.sendEmail(this);
                play.soundGoForward();
                break;
            default: break;
        }

    }
}

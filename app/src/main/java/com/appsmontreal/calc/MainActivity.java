package com.appsmontreal.calc;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Answer;
import model.RandomOperation;
import model.Validate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY = "OK";
    TextView textViewResult;
    TextView textViewOperation;
    Intent myIntent;
    RandomOperation operation;
    Validate validate;
    ArrayList<Answer> answers;
    Sound play;
    Button[] buttons = new Button[17];
    int buttonsWidgets[] = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,
                    R.id.buttonClear,R.id.buttonDot,R.id.buttonEqual,R.id.buttonGenerate,R.id.buttonLess,R.id.buttonQuit,R.id.buttonResults};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    private void initialize() {
        textViewResult = findViewById(R.id.textViewResult);
        for (int x = 0; x < buttons.length; x++){
            buttons[x] = findViewById(buttonsWidgets[x]);
            buttons[x].setOnClickListener(this);
        }
        operation = new RandomOperation();
        textViewOperation = findViewById(R.id.textViewOperation);
        answers = new ArrayList<Answer>();
        play = new Sound(this);
        buttons[12].setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button0: textViewResult.append("0"); break;
            case R.id.button1: textViewResult.append("1"); break;
            case R.id.button2: textViewResult.append("2"); break;
            case R.id.button3: textViewResult.append("3"); break;
            case R.id.button4: textViewResult.append("4"); break;
            case R.id.button5: textViewResult.append("5"); break;
            case R.id.button6: textViewResult.append("6"); break;
            case R.id.button7: textViewResult.append("7"); break;
            case R.id.button8: textViewResult.append("8"); break;
            case R.id.button9: textViewResult.append("9"); break;
            case R.id.buttonClear: textViewResult.setText(""); break;
            case R.id.buttonDot: textViewResult.append("."); break;

            case R.id.buttonEqual:
                validate = new Validate(Double.parseDouble(textViewResult.getText().toString()),operation.operationResult());
                checkAnswer();
                Toast.makeText(this,Boolean.toString(validate.validateOperation()) + "    " + operation.operationResult(),Toast.LENGTH_LONG).show();
                answers.add(new Answer(validate,operation.toString(),validate.validateOperation()));
                buttons[13].setVisibility(View.VISIBLE);
                break;

            case R.id.buttonGenerate:
                operation.launchOperation();
                textViewOperation.setText(operation.toString());
                textViewResult.setText("");
                buttons[12].setVisibility(View.VISIBLE);
                buttons[13].setVisibility(View.INVISIBLE);
                break;

            case R.id.buttonLess: textViewResult.append("-"); break;
            case R.id.buttonQuit: finish(); play.soundExit(); break;

            case R.id.buttonResults:
                play.soundGoForward();
                myIntent = new Intent(this,ResultsActivity.class);
                myIntent.putExtra(KEY,(ArrayList<Answer>) answers);
                startActivity(myIntent);
                break;

             default: break;
        }
    }

    public void checkAnswer(){
        if (validate.validateOperation()){
            play.soundOkAnswer();
        }
        else{
            play.soundWrongAnswer();
        }
        buttons[12].setVisibility(View.INVISIBLE);
    }

}

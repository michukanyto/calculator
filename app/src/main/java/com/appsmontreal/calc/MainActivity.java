package com.appsmontreal.calc;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
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
    TextView textViewCounter;
    Intent myIntent;
    RandomOperation operation;
    Validate validate;
    ArrayList<Answer> answers;
    Sound play;
    Animate animate;
    CountDownTimer countDownTimer;
    int counter;
    boolean activeButtonPlay;
    Button[] buttons = new Button[18];
    int buttonsWidgets[] = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,
                    R.id.buttonClear,R.id.buttonDot,R.id.buttonEqual,R.id.buttonPlay,R.id.buttonLess,R.id.buttonQuit,R.id.buttonResults,R.id.buttonSave};

    public enum buttonPlayState{
        PLAY,
        STOP
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    private void initialize() {
        textViewResult =(TextView) findViewById(R.id.textViewResult);
        for (int x = 0; x < buttons.length; x++){
            buttons[x] = findViewById(buttonsWidgets[x]);
            buttons[x].setOnClickListener(this);
        }
        operation = new RandomOperation();
        textViewOperation =(TextView) findViewById(R.id.textViewOperation);
        textViewCounter = (TextView) findViewById(R.id.textViewCounter);
        answers = new ArrayList<Answer>();
        play = new Sound(this);
        buttons[12].setEnabled(false);
        counter = 10;
        activeButtonPlay = false;
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button0:
                textViewResult.append("0");
                break;
            case R.id.button1:
                textViewResult.append("1");
                break;
            case R.id.button2:
                textViewResult.append("2");
                break;
            case R.id.button3:
                textViewResult.append("3");
                break;
            case R.id.button4:
                textViewResult.append("4"); break;
            case R.id.button5: textViewResult.append("5");
                break;
            case R.id.button6:
                textViewResult.append("6");
                break;
            case R.id.button7:
                textViewResult.append("7");
                break;
            case R.id.button8:
                textViewResult.append("8");
                break;
            case R.id.button9:
                textViewResult.append("9");
                break;
            case R.id.buttonClear:
                textViewResult.setText("");
                break;
            case R.id.buttonDot: textViewResult.append(".");
            break;
            case R.id.buttonEqual:
                try {
                    validate = new Validate(Double.parseDouble(textViewResult.getText().toString()), operation.operationResult());
                    checkAnswer();
                    Toast.makeText(this, Boolean.toString(validate.validateOperation()) + "    " + operation.operationResult(), Toast.LENGTH_LONG).show();
                    answers.add(new Answer(validate, operation.toString(), validate.validateOperation(),(9 - counter)));
//                    buttons[13].setEnabled(true);
                    animate = new Animate(validate.validateOperation());
                    animate.displayPoints(textViewResult);
                    countDownTimer.cancel();
                }catch (Exception e){
                    Toast.makeText(this,"Please enter a result",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.buttonPlay:

//                    operation.launchOperation();
//                    textViewOperation.setText(operation.toString());
//                    textViewResult.setText("");
//                    buttons[12].setEnabled(true);
//                    countDownDisplay();

//                    buttons[13].setEnabled(false);

                if(activeButtonPlay){
                    buttons[13].setText(buttonPlayState.PLAY.name());
                    buttons[13].setTextColor(Color.GREEN);
                    activeButtonPlay = false;
                    countDownTimer.cancel();
                }else {
                    operation.launchOperation();
                    textViewOperation.setText(operation.toString());
                    textViewResult.setText("");
                    buttons[12].setEnabled(true);
//                    buttons[13].setEnabled(false);
                    textViewResult.setTextColor(Color.GRAY);
                    textViewCounter.setVisibility(View.VISIBLE);
                    buttons[13].setText(buttonPlayState.STOP.name());
                    buttons[13].setTextColor(Color.RED);
                    countDownDisplay();
                    activeButtonPlay = true;
                }
                break;
            case R.id.buttonLess:
                textViewResult.append("-");
                break;
            case R.id.buttonQuit:
                finish();
                play.soundExit();
                break;
            case R.id.buttonResults:
                play.soundGoForward();
                myIntent = new Intent(this,ResultsActivity.class);
                myIntent.putExtra(KEY,(ArrayList<Answer>) answers);
                startActivity(myIntent);
                break;
            case R.id.buttonSave:
                Log.i("Save Button", ": you're here");
                play.soundExit();
                break;
             default:
                 break;
        }
    }


    public void checkAnswer(){
        if (validate.validateOperation()){
            play.soundOkAnswer();
        }
        else{
            play.soundWrongAnswer();
        }
        buttons[12].setEnabled(false);
    }



    public void countDownDisplay(){
        countDownTimer = new CountDownTimer(9000, 1000){
            public void onTick(long millisUntilFinished){
                textViewCounter.setText(String.valueOf(counter--));
                if (counter == 2){
                    textViewCounter.setTextColor(Color.RED);
                }
                else if(counter == 0){
                    buttons[12].setEnabled(false);
                    buttons[13].setEnabled(true);
                    validate = new Validate(Double.parseDouble("-10"), operation.operationResult());//-10 always will be a wrong answer
                    checkAnswer();
                    answers.add(new Answer(validate, operation.toString(), validate.validateOperation(),10));
                }

            }
            public  void onFinish(){
                textViewCounter.setText("0");
            }
        }.start();

            counter = 10;
            textViewCounter.setTextColor(Color.WHITE);

    }

}

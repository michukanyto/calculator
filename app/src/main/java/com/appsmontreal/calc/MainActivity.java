package com.appsmontreal.calc;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.Answer;
import model.FileResultsManagement;
import model.RandomOperation;
import model.Validate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY = "OK";
    public final String STOPMESSAGE = "Calc Stopped!";
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
    FileResultsManagement fileResultsManagement;
    int counter;
    boolean activeButtonPlay;
    Button[] buttons = new Button[18];
    int buttonsWidgets[] = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9,
                    R.id.buttonLess,R.id.buttonDot,R.id.buttonEqual,R.id.buttonClear,R.id.buttonPlay,R.id.buttonQuit,R.id.buttonResults,R.id.buttonSave};

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
        buttons[17].setEnabled(false);//Save
        counter = 10;
        activeButtonPlay = false;
        fileResultsManagement = new FileResultsManagement(this);
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
//                Toast.makeText(this,fileResultsManagement.readFromResultFile(),Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonDot: textViewResult.append(".");
            break;
            case R.id.buttonEqual:
                try {
                    validate = new Validate(Double.parseDouble(textViewResult.getText().toString()), operation.operationResult());
                    checkAnswer();
                    answers.add(new Answer(validate, operation.toString(), validate.validateOperation(),(9 - counter)));
                    animate = new Animate(validate.validateOperation());
                    animate.displayPoints(textViewResult);/////////message on display result
                    countDownTimer.cancel();
                    countDownDisplay();

                }catch (Exception e){
                    Toast.makeText(this,"Please enter a result",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.buttonPlay:
                if(activeButtonPlay){
                    buttons[14].setText(buttonPlayState.PLAY.name());
                    buttons[14].setTextColor(Color.GREEN);
                    buttons[17].setEnabled(true);//Save
                    textViewOperation.setText(STOPMESSAGE);

                    activeButtonPlay = false;
                    countDownTimer.cancel();
                    enableNumberButtons(false);
                }else {
                    textViewResult.setText("");
                    textViewResult.setTextColor(Color.GRAY);
                    textViewCounter.setVisibility(View.VISIBLE);
                    buttons[14].setText(buttonPlayState.STOP.name());
                    buttons[14].setTextColor(Color.RED);
                    buttons[17].setEnabled(false);//Save
                    enableNumberButtons(true);
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
                fileResultsManagement.writeResultFile(answers);
                play.soundSave();
                break;
             default:
                 break;
        }
    }


    public void enableNumberButtons(boolean decision){
        for(int x = 0; x < 14; x++){
            buttons[x].setEnabled(decision);
        }
    }


    public void checkAnswer(){
        if (validate.validateOperation()){
            play.soundOkAnswer();
        }
        else{
            play.soundWrongAnswer();
        }
    }



    public void countDownDisplay(){
        counter = 10;
        textViewCounter.setTextColor(Color.WHITE);
        operation.launchOperation();
        textViewOperation.setText(operation.toString());

        countDownTimer = new CountDownTimer(10000, 1000){
            public void onTick(long millisUntilFinished){
                textViewCounter.setText(String.valueOf(counter--));

                if(counter == 8){
                    textViewResult.setText("");
                    textViewResult.setTextColor(Color.GRAY);
                }
                else if (counter == 2){
                    textViewCounter.setTextColor(Color.RED);
                }
                else if(counter == 0){
                    buttons[14].setEnabled(true);
                    validate = new Validate(Double.parseDouble("-10"), operation.operationResult());//-10 always will be a wrong answer
                    checkAnswer();
                    answers.add(new Answer(validate, operation.toString(), validate.validateOperation(),10));
                }
            }
            public  void onFinish(){
                textViewCounter.setTextColor(Color.WHITE);
                textViewCounter.setText("0");
                operation.launchOperation();
                textViewOperation.setText(operation.toString());
                countDownDisplay();
            }
        }.start();

    }



//    public void writeResultFile(String fileName){
//        AssetManager assetManager = this.getResources().getAssets();
//
//        try {
//            String path = MainActivity.this.getFilesDir().getAbsolutePath();
//            File f = new File(getFilesDir(), fileName);
//
//
//
//
//            File file = this.getFileStreamPath(fileName);
//            FileWriter fileWriter = new FileWriter(file);
//            fileWriter.write("hello\nword");
//            Toast.makeText(this,"save to !" + f  ,Toast.LENGTH_SHORT).show(); //solution1
//            fileWriter.close();
//
//
////            FileOutputStream fileout = context.openFileOutput(fileName, context.MODE_PRIVATE);
////            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
////            outputWriter.write("write this string to file.");
////            outputWriter.close();//solution2
//
////            File path = context.getFilesDir();
//
////            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
////            outputStreamWriter.write("hello");
////            Toast.makeText(context,"You're writing file : " + path,Toast.LENGTH_SHORT).show();
////            outputStreamWriter.close();//solution 3
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//    }






}

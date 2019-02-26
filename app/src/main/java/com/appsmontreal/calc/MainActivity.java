package com.appsmontreal.calc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.RandomOperation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewResult;
    TextView textViewOperation;
    Intent myIntent;
    RandomOperation operation;
    Button[] buttons = new Button[16];
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
                textViewResult.append("4");
                break;
            case R.id.button5:
                textViewResult.append("5");
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
            case R.id.buttonDot:
                textViewResult.append(".");
                break;
            case R.id.buttonEqual:
                textViewResult.append("9");
                break;
            case R.id.buttonGenerate:
                operation.launchOperation();
                textViewOperation.setText(operation.toString());
                Toast.makeText(this,operation.toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonLess:
                textViewResult.append("-");
                break;
            case R.id.buttonQuit:
                finish();
                break;
            case R.id.buttonResults:
                myIntent = new Intent(this,ResultsActivity.class);
                startActivity(myIntent);
                break;
             default:
                 break;

        }

    }
}

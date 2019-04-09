package model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;



public class FileResultsManagement {

    private Context context;
    public static final String FILENAME = "CalcResults.txt";

    public FileResultsManagement(Context context) {
        this.context = context;
    }

    public void writeResultFile(ArrayList<Answer> answers){
        try {
            File path = new File(context.getFilesDir(), FILENAME);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(FILENAME, Context.MODE_APPEND));

            for (Answer answer : answers){
                outputStreamWriter.write(answer.toString());
            }

            Toast.makeText(context,"save in => " + path ,Toast.LENGTH_SHORT).show();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFromResultFile() {
        ArrayList<String> listOfAnswers = new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput(FILENAME);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    listOfAnswers.add(receiveString + "\n");
                }

                inputStream.close();
                bufferedReader.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Error : ", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Error : ", "Can not read file: " + e.toString());
        }

        return listOfAnswers;
    }

//    public String readFromResultFile() {
//        String data = "";
//
//        try {
//            InputStream inputStream = context.openFileInput(FILENAME);
//            if ( inputStream != null ) {
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String receiveString = "";
//                StringBuilder stringBuilder = new StringBuilder();
//
//                while ( (receiveString = bufferedReader.readLine()) != null ) {
//                    stringBuilder.append(receiveString);
//                    stringBuilder.append("\n\n");// to control break line in file
//                }
//
//                inputStream.close();
//                data = stringBuilder.toString();
//            }
//        }
//        catch (FileNotFoundException e) {
//            Log.e("Error : ", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("Error : ", "Can not read file: " + e.toString());
//        }
//
//        return data;
//    }

//


}


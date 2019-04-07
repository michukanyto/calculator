package model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Formatter;




public class FileResultsManagement {

//    private static Formatter file;


    public static void OpenResultFile(Context context,String fileName){
        AssetManager assetManager = context.getResources().getAssets();

        try {
//            String path = context.getFilesDir().getAbsolutePath();
            File f = new File(context.getFilesDir(), fileName);





//            File file = context.getFileStreamPath(fileName);
//            FileWriter fileWriter = new FileWriter(file);
//            fileWriter.write("hello\nword");
//            Toast.makeText(context,"save to !" + f ,Toast.LENGTH_SHORT).show(); //solution1
//            fileWriter.close();

//
//            FileOutputStream fileOut = context.openFileOutput(fileName, context.MODE_PRIVATE);
//            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
//            Toast.makeText(context,"save in => " + f ,Toast.LENGTH_LONG).show(); //solution1
//            outputWriter.write("write this string to file.");
//            outputWriter.close();//solution2

//            File path = context.getFilesDir();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write("hello\nworld");
            Toast.makeText(context,"save in => " + f ,Toast.LENGTH_LONG).show();
            outputStreamWriter.close();//solution 3
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public  static String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("CalcResults.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }



}


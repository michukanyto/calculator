package model;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Formatter;




public class FileResultsManagement {

//    private static Formatter file;


    public static void OpenResultFile(Context context,String fileName){
        AssetManager assetManager = context.getResources().getAssets();

        try {
//            File file = context.getFileStreamPath(fileName);
//            FileWriter fw = new FileWriter(file);
//            fw.write("hello\nword");
//            Toast.makeText(context,"save to !",Toast.LENGTH_SHORT).show(); //solution1
//            fw.close();


//            FileOutputStream fileout = context.openFileOutput(fileName, context.MODE_PRIVATE);
//            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
//            outputWriter.write("write this string to file.");
//            outputWriter.close();//solution2

            File path = context.getFilesDir();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write("hello");
            Toast.makeText(context,"You're writing file : " + path,Toast.LENGTH_SHORT).show();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//    public static void writeInResultFile(Context context){
//        file.format("%s%s%s%s","hello","world","how is it","going");
//        Toast.makeText(context,"You're writing file",Toast.LENGTH_SHORT).show();
//        file.close();
//    }
}

//
//    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_PRIVATE));
//            outputStreamWriter.write(data);
//                    outputStreamWriter.close();
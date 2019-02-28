package com.appsmontreal.calc;
import android.content.Context;
import android.media.MediaPlayer;

public class Sound {

    private Context context;
    MediaPlayer mediaPlayer;

    public Sound(Context context) {
        this.context = context;
    }

    public void soundOkAnswer(){
        mediaPlayer = MediaPlayer.create(context, R.raw.winner);
        playSound(mediaPlayer);
    }

    public void soundWrongAnswer(){
        mediaPlayer = MediaPlayer.create(context,R.raw.no);
        playSound(mediaPlayer);
    }

    public void soundExit(){
        mediaPlayer = MediaPlayer.create(context,R.raw.goodbye);
        playSound(mediaPlayer);
    }

    public void soundGoForward(){
        mediaPlayer = MediaPlayer.create(context,R.raw.ticka);
        playSound(mediaPlayer);
    }

    public void soundGoBack(){
        mediaPlayer = MediaPlayer.create(context,R.raw.woosha);
        playSound(mediaPlayer);
    }

    public void soundError(){
        mediaPlayer = MediaPlayer.create(context,R.raw.error);
        playSound(mediaPlayer);
    }

    private void playSound(MediaPlayer play){
        play.start();
        play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaP) {
                mediaP.release();
            };
        });

    }
}

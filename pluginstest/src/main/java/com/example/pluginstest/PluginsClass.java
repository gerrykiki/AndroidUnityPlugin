package com.example.pluginstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import com.unity3d.player.UnityPlayer;

import java.util.List;

/**
 * Created by gerrylin on 2018/3/6.
 */


public class PluginsClass {


    public static class MyRecognizerListener implements RecognitionListener{
        @Override
        public void onResults(Bundle results) {
            List resList = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            UnityPlayer.UnitySendMessage("Main Camera","Message","SpeechOK");
            //textView2.setText("onResults: " + );
            //Log.d("RECOGNIZER", "onResults: " + sb.toString());
        }

        @Override
        public void onError(int error) {
            //Log.d("RECOGNIZER", "Error Code: " + error);
        }

        @Override
        public void onReadyForSpeech(Bundle params) {
        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float rmsdB) {
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
        }

        @Override
        public void onEvent(int eventType, Bundle params) {
        }
    }

    static SpeechRecognizer recognizer;
    private AudioManager audioManager;
    static  Activity activity;

    public static String setUnityActivity(Activity Miaactivity) {
        activity = Miaactivity;
        return "Success";
    }

    static int REQ_CODE_SPEECH_OUTPUT = 143;


    public static String btnToOpenMic() {


        Intent target = new Intent(activity, activityplugin.class);
        activity.startActivity(target);
        return "Success";
    }


    public int StartPluginActivity(Context myConterxt) {
        audioManager = (AudioManager) myConterxt.getSystemService(Context.AUDIO_SERVICE);
        int volum = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        return volum;
    }

    private String TAG = this.getClass().getSimpleName();

    public static float getTest(){
        return (float) 1.1;
    }

    public static float getTest2(){
        return (float) 1.2;
    }

    public static float getVolume() {

        if (activity == null) {
            return (float)1.3;
        }

        else {
        AudioManager mgr = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        //float volumenumber = mgr.getStreamVolume(AudioManager.STREAM_SYSTEM);
        return mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        }

    }

    public static float setVolume(int volume) {

        //Log.d(TAG, "setVolume volume " + volume);

        if (activity == null) {
            return (float)1.4;
        }
        else {
            AudioManager mgr = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
            mgr.setStreamVolume(AudioManager.STREAM_MUSIC,volume,0);
            return volume;

        }
    }



}

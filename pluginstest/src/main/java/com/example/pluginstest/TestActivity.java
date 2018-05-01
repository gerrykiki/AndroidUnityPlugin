package com.example.pluginstest;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by gerrylin on 2018/3/29.
 */

public class TestActivity extends UnityPlayerActivity {

    Context mContext = null;
    private final int REQ_CODE_SPEECH_OUTPUT = 143;
    private static final String TAG = TestActivity.class.getSimpleName();
    private static final int PHOTO_REQUEST_CODE = 1;//相册
    public static final int PHOTOHRAPH = 2;// 拍照
    private static final boolean DEBUG = false;
    //	private String unitygameobjectName = "Main Camera";
    private String unitygameobjectName = "BGPlane"; //Unity 中对应挂脚本对象的名称
    public static final int NONE = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    //示例1：
    //Unity端调用该函数
    public void ShowToast(final String msg) {
        // 需要在UI线程下执行
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //示例2：
    //Unity端调用该函数
    public void setUnityText() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Android 端调用setText", Toast.LENGTH_SHORT).show();
                //调用Unity端函数

                UnityPlayer.UnitySendMessage("Main Camera", "Message", "SpeechOK3");
            }
        });
    }

    public void SpeechOn() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, "Hi, Speak now.....");

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);
        } catch (ActivityNotFoundException tim) { //Google mic is not opened.
            //Toast toast = Toast.makeText(SpeechActivity.this, "open mic!", Toast.LENGTH_LONG);
        }

        UnityPlayer.UnitySendMessage("Main Camera", "Message", "SpeechOK1");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_OUTPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    UnityPlayer.UnitySendMessage("Main Camera", "Message", voiceInText.get(0));
                }
                break;
            }
        }
    }

    public void TakePhoto(String str) {


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 999);
    }

    private void getAppList() {
        PackageManager pm = getPackageManager();
        // Return a List of all packages that are installed on the device.
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            // 判断系统/非系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) // 非系统应用
            {
                System.out.println("MainActivity.getAppList, packageInfo=" + packageInfo.packageName);
                UnityPlayer.UnitySendMessage("Main Camera", "PackageNameMessage", packageInfo.packageName);
            } else {
                // 系统应用
            }
        }
    }

    public void viewCreate(){

    }
}

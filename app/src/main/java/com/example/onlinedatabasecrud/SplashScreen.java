package com.example.onlinedatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    TextView textView;
    ProgressBar progressBar;
    int progress;
    String uid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("login", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        uid=pref.getString("uid", null); // getting String
        Log.i("id","uid:"+uid);

        textView=findViewById(R.id.stylishTV);
        progressBar=findViewById(R.id.PB);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();

                //String phone =FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
                //Log.i("Login ","phone:"+phone);
                Log.i("Login ","uid:"+uid);

                if(uid==null){

                    goNext();
                }else{
                    goDashboard();
                }

            }
        }
        );
        thread.start();
    }
    public void doWork()
    {
        for (progress=20;progress<=100;progress=progress+20)
        {
            try {
                Thread.sleep(500);
                progressBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void goNext()
    {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void goDashboard()
    {
        Intent intent=new Intent(this, DashBoard.class);
        startActivity(intent);
        finish();
    }
}



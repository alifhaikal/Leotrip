package com.slumberjer.tourism;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

public class SplashActivity extends Activity {
    public class Main {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        Thread splash_screen = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }
        };
        splash_screen.start();
    }
}

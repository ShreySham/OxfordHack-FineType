package com.finetype.hacking.finetype;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;
    private double xAcc, yAcc, zAcc;
    public double xVel, yVel, zVel;
    public double xPos, yPos, zPos;
    private double lastTime = System.currentTimeMillis();
    boolean left, right, up, down;
    String sentence = "";
    int xCount, yCount;

    char[][] alphabet = new char[][] {{'a','b','c','d','e','f','g'},{'h','i','j','k','l','m','n'},
                                        {'o','p','q','r','s','t'},{'u','v','w','x','y','z'}};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create sensor manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        //accelerometer sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //register sensor listener

        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        //Assign TextView
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);



        final TextView textViewOut = (TextView) findViewById(R.id.keyOut);
        Button keyPress = (Button) findViewById(R.id.keyPress);

        keyPress.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){

            }
        });

             keyPress.setOnTouchListener(
                    new RelativeLayout.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent m) {
                            double deltaTime= (System.currentTimeMillis() - lastTime);



                            if(xAcc<-3){
                                right = true;
                            }else {right = false;}
                            if(xAcc>3){
                                left = true;
                            }else{left = false;}
                            if(yAcc-1<-2){
                                down = true;
                            }else {down = false;}
                            if(yAcc>5){
                                up = true;
                            }else{up = false;}


                            try{
                                Thread.sleep(200);
                            }catch (Exception e) {}


                             if(down==true){
                                yCount--;
                                sentence += " "+alphabet[yCount][0];

                                }
                            if(right==true){
                                xCount++;
                                sentence += " "+alphabet[yCount][xCount];
                            }
                             if (left == true) {
                                    xCount--;
                                    sentence += " " + alphabet[yCount][xCount];
                                }

                            try{
                                Thread.sleep(200);
                            }catch (Exception e) {}


                            if(up==true){
                                yCount++;
                                sentence += " "+alphabet[yCount][0];

                            } if(left==true){
                                xCount--;
                                sentence += " "+alphabet[yCount][xCount];
                            }
                            if(right==true){
                                xCount++;
                                sentence += " "+alphabet[yCount][xCount];
                            }


                            textViewOut.setText("Right: " + right+" Xacc: " +xAcc+ " text:     "+ sentence);
                            lastTime=System.currentTimeMillis();
                            return true;
                        }
                    }
            );




    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        xText.setText("X " + event.values[0]);
        xAcc =  event.values[0];
        yText.setText("Y " + event.values[1]);
        yAcc = event.values[1];
        zText.setText("Z " + event.values[2]);
        zAcc = event.values[2];
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not used
    }

}

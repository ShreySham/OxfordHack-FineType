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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create sensor manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        //accelerometer sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
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
                            if (deltaTime<25.0){
                                deltaTime=0.0;
                            }
                            xVel += (xAcc * deltaTime / 1000);
                            xPos += (xVel * deltaTime / 1000);
                            yVel += (yAcc * deltaTime / 1000);
                            yPos += (yVel * deltaTime / 1000);
                            zVel += (zAcc * deltaTime / 1000);
                            zPos += (zVel * deltaTime / 1000);
                            textViewOut.setText("X velocity: " + xVel+"  X position: " + xPos+" Y velocity: " + yVel+"  Y position: " + yPos +" Z velocity: " + zVel+"  Z position: " + zPos);
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

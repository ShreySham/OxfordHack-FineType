package com.finetype.hacking.finetype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;



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
                textViewOut.setText("BOOM!");
            }
        });

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        xText.setText("X " + event.values[0]);
        yText.setText("Y " + event.values[1]);
        zText.setText("Z " + event.values[2]);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //not used
    }

}

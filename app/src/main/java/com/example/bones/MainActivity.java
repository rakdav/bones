package com.example.bones;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView image1,image2;
    private TextView result;
    private SensorManager sensorManager;
    private Sensor sensorAccelerometr;
    private float[] values=new float[3];
    private int[] dice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1=findViewById(R.id.imageView);
        image2=findViewById(R.id.imageView2);
        result=findViewById(R.id.textView);
        result.setText("Потрясите телефон");
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometr=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        dice= new int[]{R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listener,sensorAccelerometr,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }

    SensorEventListener listener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            switch (sensorEvent.sensor.getType())
            {
                case Sensor.TYPE_ACCELEROMETER:
                    float s=0;
                    for (int i = 0; i < 3; i++) {
                        values[i]=sensorEvent.values[i];
                        s+=values[i];
                    }
                    if(s>18) {
                        Random random1 = new Random();
                        int num1 = random1.nextInt(6);
                        image1.setImageResource(dice[num1]);
                        Random random2 = new Random();
                        int num2 = random2.nextInt(6);
                        image2.setImageResource(dice[num2]);
                        result.setText(Integer.toString(num1 + num2+2));
                    }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
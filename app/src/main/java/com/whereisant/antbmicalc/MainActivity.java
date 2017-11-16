package com.whereisant.antbmicalc;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private float height;
    private float weight;
    private float BMI;

    // SeekBar implementation
    private SeekBar height_sb;
    private TextView height_view;
    private SeekBar weight_sb;
    private TextView weight_view;


//    private SeekBar seekbar;
//    private TextView progressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("Class", "OnCreate Invoked!");

        int step = 1;

        int heightmax = 210;
        int heightmin = 130;

        height_sb = (SeekBar) findViewById(R.id.seekbar_height);
        height_sb.setMax((heightmax - heightmin) / step );
        height_sb.setProgress(50);
        height_view = (TextView) findViewById(R.id.textview_height);

        int weightmax = 200;
        int weightmin = 30;
        weight_sb = (SeekBar) findViewById(R.id.seekbar_weight);
        weight_sb.setMax((weightmax - weightmin) / step );
        //weight_sb.setMin(30);
        weight_sb.setProgress(60);
        weight_view = (TextView) findViewById(R.id.textview_weight);


        // perform seek bar change listener event used for getting the height_view value
        height_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 130;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = 130+progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                height_view.setText("Your height is "+progressChangedValue);
                Toast.makeText(MainActivity.this, "Height changed to :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });

        // perform seek bar change listener event used for getting the weight_view value
        weight_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 30;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = 30 + progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                weight_view.setText("Your weight is "+progressChangedValue);
                Toast.makeText(MainActivity.this, "Weight changed to:" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button compute_button = (Button) findViewById(R.id.compute_button);
        final EditText height_value = (EditText) findViewById(R.id.height);
        final EditText weight_value = (EditText) findViewById(R.id.weight);
        final TextView result = (TextView) findViewById(R.id.result);

        compute_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DecimalFormat df = new DecimalFormat("#.##");

                if (height_value.getText().length() > 0
                        && weight_value.getText().length() > 0) {

                    height = Float.parseFloat(height_value.getText().toString());
                    weight = Float.parseFloat(weight_value.getText().toString());

                    BMI = calculateBMI(weight, height);
                }


                if (BMI < 16) {
                    result.setText("Your BMI: " + df.format(BMI) + " ( Severely underwight )");
                    result.setTextColor(Color.parseColor("#D32F2F"));
                } else if (BMI < 18.5) {
                    result.setText("Your BMI: " + df.format(BMI) + " ( Underweight )");
                } else if (BMI < 25) {
                    result.setText("Your BMI: " + df.format(BMI) + " ( Normal )");
                } else if (BMI < 30) {
                    result.setText("Your BMI: " + df.format(BMI) + " ( Overweight )");
                } else {
                    result.setText("Your BMI: " + df.format(BMI) + " ( Obese )");
                    result.setTextColor(Color.parseColor("#D32F2F"));
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("Class", "OnStart Invoked!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Class", "OnDestroy Invoked!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Class", "OnResume Invoked!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("Class", "OnPause Invoked!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Class", "OnStop Invoked!");
    }

    private float calculateBMI(float weight, float height) {
        height = (height / 100);
        return (float) (weight / (height * height));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package edu.sdsu.cs.cs646.assignment2;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimeActivity extends AppCompatActivity {

    private TimePicker mTimePicker;
    private TextView mTimeDisplayView;
    private TextView mTimeText;
    private Button mTimeButton;
    private String format = "";
    SharedPreferences mSharedPreferences;
    private static int hourTimeStamp;
    private static int minuteTimeStamp;
    private static int spDefault;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);



        mTimePicker = (TimePicker)findViewById(R.id.time_picker);
        mTimePicker.setIs24HourView(false);
        mTimeText = (TextView)findViewById(R.id.set_time);
        mTimeDisplayView = (TextView)findViewById(R.id.set_time_display);
        mTimeButton = (Button)findViewById(R.id.set_time_button);
        mSharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        hourTimeStamp = mSharedPreferences.getInt("hour", spDefault);
        minuteTimeStamp = mSharedPreferences.getInt("minute",spDefault );
        if(hourTimeStamp == spDefault || minuteTimeStamp == spDefault) {
            Toast.makeText(TimeActivity.this, "No Data was Found", Toast.LENGTH_LONG).show();
        } else{Toast.makeText(TimeActivity.this, "Data loaded!", Toast.LENGTH_LONG).show();
            mTimePicker.setCurrentHour(hourTimeStamp);
            mTimePicker.setCurrentMinute(minuteTimeStamp);
            timeConvert(hourTimeStamp,minuteTimeStamp);
            String setTime = mTimeDisplayView.getText().toString();
            Intent toPassBack = getIntent();
            toPassBack.putExtra("editTextValue", setTime);
            setResult(RESULT_OK, toPassBack);
        }

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TimeActivity.this);
                builder.setMessage("Change Time?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int mHour = mTimePicker.getCurrentHour();
                                int mMinute = mTimePicker.getCurrentMinute();
                                timeConvert(mHour, mMinute);

                                SharedPreferences.Editor editor = mSharedPreferences.edit();
                                editor.putInt("hour", mHour);
                                editor.putInt("minute", mMinute);
                                editor.commit();

                                String setTime = mTimeDisplayView.getText().toString();
                                Intent toPassBack = getIntent();
                                toPassBack.putExtra("editTextValue", setTime);
                                setResult(RESULT_OK, toPassBack);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
    public void timeConvert(int hour, int minute){
        String minutesFormat = String.format("%02d",minute);
        if (hour == 0) {
            hour = hour + 12;
            format = "AM";
        }else if (hour == 12) {
            format = "PM";
        }else if (hour > 12){
            hour = hour - 12;
            format = " PM";
        }else {
            format = " AM";
        }
        mTimeDisplayView.setText(new StringBuilder().append(hour).append
                (":").append(minutesFormat).append
                (" ").append(format));
    }
}

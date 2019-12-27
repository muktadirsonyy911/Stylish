package com.example.onlinedatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.onlinedatabasecrud.ModelClass.ShopsInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

public class DateAndTime extends AppCompatActivity implements View.OnClickListener
{
    // date and time
    private TextView textView,textView1;
    private Button button, button1;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time);

        Intent intent = getIntent();
        ShopsInfo list = intent.getParcelableExtra("Example Item");

        String t1 = list.getShopName();
        String t2 = list.getShopAddress();
        String t3 = list.getShopPhone();

        TextView text1 = findViewById(R.id.Name2TV);
        text1.setText(t1);

        TextView text2 = findViewById(R.id.Address2TV);
        text2.setText(t2);

        TextView text3 = findViewById(R.id.Phone2TV);
        text3.setText(t3);

        //find the id for date and time
        textView=findViewById(R.id.text);
        textView1=findViewById(R.id.text2);
        button=findViewById(R.id.button);


        //find view for location
        button1=findViewById(R.id.locationBTN);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent location=new Intent(DateAndTime.this, MapsActivity.class);
                startActivity(location);
            }
        });


//set listener for date and time button
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        DatePicker datePicker=new DatePicker(this);
        TimePicker timePicker=new TimePicker(this);

        //varriable for date
        int currentdate= datePicker.getDayOfMonth();
        int currentmonth= (datePicker.getDayOfMonth()+1);
        int currentyear= datePicker.getYear();

        //varriable for time

        int currentHour=timePicker.getCurrentHour();
        int currentMinute=timePicker.getCurrentMinute();

        datePickerDialog= new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        textView.setText("Your appoint date is: "+dayOfMonth+"/"+(month+1)+"/"+year);

                    }
                },currentyear,currentmonth,currentdate);
        datePickerDialog.show();

        timePickerDialog=new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        textView1.setText("Your selected time is: "+hourOfDay+":"+minute);

                    }
                },currentHour,currentMinute,true);

        timePickerDialog.show();
    }


    public void logout(View view) {
        // lovely Dailog
        new LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                .setTopColorRes(R.color.colorPrimary)
                .setButtonsColorRes(R.color.colorAccent)
                .setIcon(R.drawable.logout)
                .setTitle("Logout Menu")
                .setMessage("Are you want to sure?")
                .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharedPreferences pref =DateAndTime.this.getSharedPreferences("login", 0); // 0 - for private mode
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("uid", null);
                        editor.commit();

                         FirebaseAuth.getInstance().signOut();
                         DateAndTime.this.finish();
                        // onStop();
                        DateAndTime.this.onDestroy();


                        Log.i("logout:","uidTesting:"+ FirebaseAuth.getInstance().getUid());
                        Log.i("login","local uid save:"+pref.getString("uid",null));
                        Intent loginActivity = new Intent(DateAndTime.this, MainActivity.class);
                        startActivity(loginActivity);
                            //Toast.makeText(context, "positive clicked", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}


package com.example.onlinedatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button button,button1,button2;
    String area[];
    private TextView textView,textView1,textView2;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        area=getResources().getStringArray(R.array.Area_list);

        spinner=findViewById(R.id.spinid);
        button=findViewById(R.id.spinbtn);

        textView=findViewById(R.id.spinttext);
        textView1=findViewById(R.id.text);
        textView2=findViewById(R.id.text2);


        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.area,R.id.layouttextView,area);
        spinner.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SpinnerActivity.this, RecycleView.class);
                startActivity(intent);

            }
        });


    }
}

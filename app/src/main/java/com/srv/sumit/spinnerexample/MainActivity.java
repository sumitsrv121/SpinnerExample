package com.srv.sumit.spinnerexample;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{
    Spinner spinner;
    RadioGroup radioGroup;
    RadioGroup radioGroup1;
    RadioButton chicken;
    RadioButton panner;
    ImageView imageView;
    Button order;
    int selectID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        order = (Button) findViewById(R.id.order);
        spinner = (Spinner) findViewById(R.id.spinner);
        imageView = (ImageView) findViewById(R.id.imageview);
        radioGroup = (RadioGroup) findViewById(R.id.veg);
        radioGroup1 = (RadioGroup) findViewById(R.id.nonveg);

        AnimationDrawable abc = (AnimationDrawable) getResources().getDrawable(R.drawable.animation);
        imageView.setBackground(abc);
        abc.start();
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.dishes,android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(MainActivity.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        String string=textView.getText().toString();
        if(string.equalsIgnoreCase("Vegetarian")){


            radioGroup.setVisibility(View.VISIBLE);
            if(radioGroup1.getVisibility()==View.VISIBLE)
                radioGroup1.setVisibility(View.GONE);
           order.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectID = radioGroup.getCheckedRadioButtonId();
                  if(selectID == -1){
                      Toast.makeText(MainActivity.this,"Select Something Before placing The order",Toast.LENGTH_SHORT).show();
                  }
                  else{
                      panner = (RadioButton) findViewById(selectID);
                      String menu=panner.getText().toString();
                      Toast.makeText(MainActivity.this,"item you selected "+menu,Toast.LENGTH_SHORT).show();
                  }
               }
           });
        }
       else if(string.equalsIgnoreCase("Non-Vegetarian")){


            radioGroup1.setVisibility(View.VISIBLE);
            if(radioGroup.getVisibility()==View.VISIBLE)
                radioGroup.setVisibility(View.GONE);
           order.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectID = radioGroup1.getCheckedRadioButtonId();
                   if(selectID == -1){
                       Toast.makeText(MainActivity.this,"Select Something Before placing The order",Toast.LENGTH_SHORT).show();
                   }
                   else{
                       chicken = (RadioButton) findViewById(selectID);
                       String menu=chicken.getText().toString();
                       Toast.makeText(MainActivity.this,"item you selected "+menu,Toast.LENGTH_SHORT).show();
                   }
               }
           });

        }
        else{
            if(radioGroup.getVisibility()==View.VISIBLE){
                radioGroup.setVisibility(View.GONE);
            }
            else if(radioGroup1.getVisibility()==View.VISIBLE){
                radioGroup1.setVisibility(View.GONE);
            }
            order.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"Please! Select the food Type",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(MainActivity.this,"Please! Select the food Type",Toast.LENGTH_SHORT).show();
    }
}

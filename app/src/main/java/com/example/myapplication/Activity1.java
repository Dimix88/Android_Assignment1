package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    TextView textView;
    String text;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        next=findViewById(R.id.next1);
        textView=findViewById(R.id.textView1);
        text=getIntent().getExtras().getString("Value");
        textView.setText(text);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity1.this, Activity2.class);
                text = textView.getText().toString();
                i.putExtra("Value",text+"\n1)I have read the Message");
                startActivity(i);
                finish();

            }
        });
    }
}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity5 extends AppCompatActivity {

    TextView textView;
    String text;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        next=findViewById(R.id.next5);
        textView=findViewById(R.id.textView5);
        text=getIntent().getExtras().getString("Value");
        textView.setText(text);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity5.this, MainActivity.class);
                text = textView.getText().toString();
                i.putExtra("Act5",text+"\n5)I have read the Message");
                startActivity(i);

            }
        });
    }
}

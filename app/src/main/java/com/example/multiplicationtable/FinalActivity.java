package com.example.multiplicationtable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FinalActivity extends Activity {
    private TextView mtRes,mfRes,textRes;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_layout);
        mtRes = findViewById(R.id.mtRes);
        mfRes = findViewById(R.id.mfRes);
        textRes = findViewById(R.id.textRes);
        int t = getIntent().getExtras().getInt("username");
        int f = getIntent().getExtras().getInt("gift");
        mtRes.setText(String.valueOf(t));
        mfRes.setText(String.valueOf(f));
        if (t < f) {
            textRes.setTextColor(Color.parseColor("#FF0000"));
            textRes.setText("Попробуй еще раз!");
        } else {
            textRes.setTextColor(Color.parseColor("#736A6A"));
            textRes.setText("Неплохо");
        }
        if (t == 9 || t == 81) {
            textRes.setTextColor(Color.parseColor("#08850D"));
            textRes.setText("Отлично!");
        }
    }
    public void onClickFinish(View view){
        Intent i = new Intent(FinalActivity.this, StartActivity.class);
        startActivity(i);

    }
}


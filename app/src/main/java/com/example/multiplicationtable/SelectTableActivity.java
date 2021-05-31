package com.example.multiplicationtable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class SelectTableActivity extends Activity {
    private EditText textNum;
    private int bound = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermediate_layout);
        textNum = findViewById(R.id.Number);
        //init();
    }

    public void onClickAllTable(View view) {
        startIntent();
    }

    public void onClickTrain(View view) {
       String userAnswerText = textNum.getText().toString();
       int userNum = Integer.parseInt(userAnswerText);
       bound = userNum;
        startIntent();
    }
    private  void startIntent(){
        Intent i = new Intent(SelectTableActivity.this, MultiplicationActivity.class);
        i.putExtra("username", bound);
        startActivity(i);

    }
}

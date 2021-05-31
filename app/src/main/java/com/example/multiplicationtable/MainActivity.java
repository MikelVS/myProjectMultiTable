package com.example.multiplicationtable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.min;
import static java.lang.String.valueOf;


@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    private TextView res, quetion, resFalse;

    private long startTime = 0;
    private long currenTime = 0;
    // private float timeResult = 0;
    private boolean trueAnswer = false;
    private String example;
    int i = 0;


    MultiplicationTable table = new MultiplicationTable(5);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        numbers();


    }

    private void init() {
        res = findViewById(R.id.res);

        quetion = findViewById(R.id.quetion);

        // actionBar = getSupportActionBar();
        //startTime = System.currentTimeMillis();
        resFalse = findViewById(R.id.resFalse);
        Toast toast = Toast.makeText(getApplicationContext(),
                "Готов?", Toast.LENGTH_SHORT);
        toast.show();
        Toast t = Toast.makeText(getApplicationContext(),
                "Поехали!!!", Toast.LENGTH_SHORT);
        t.show();
        String example = table.getNextExample();

        }



    public void onClickTrue(View view) {

        //getBack();
        //String example = getExample();
        if (table.solveExample(example, trueAnswer)) {

           numbers();
        } else {

            numbers();
          }
        res.setText(String.valueOf(table.getResolved()));
        resFalse.setText(String.valueOf(table.getUnResolved()));
    }
    // else {
    // res.setText(String.valueOf(table.getResolved()));
    //  resFalse.setText(String.valueOf(table.getUnResolved()));

    //}}


    public void onClickFalse(View view) {

       // getBack();
        //  if(table.hasNext()){
       // String example = getExample();
        if (table.solveExample(example, !trueAnswer)) {
           numbers();


        } else {
           numbers();

        }
        res.setText(String.valueOf(table.getResolved()));
        resFalse.setText(String.valueOf(table.getUnResolved()));
    }

    //        else {
//            res.setText(String.valueOf(table.getResolved()));
//            resFalse.setText(String.valueOf(table.getUnResolved()));
//
//        }
//    }
    private int getFalseResult(String example) {

         int falseRes;
        int min;
        int res = table.getResult(example);
        int max = res + 5;
        if (table.getResult(example) >= 9) {
            min = res - 6;
        } else {
            min = 0;
        }
        max -= min;
        falseRes= (int) (Math.random() * ++max) + min;

                if(falseRes == res){
                    falseRes = (int) (Math.random() * ++max) + min;
                }



        return falseRes;

    }

    private int getResult(String example) {
        int numberInd = (int) (Math.random() * 2) + 1;
        if (numberInd == 2) {
            trueAnswer = false;
            return getFalseResult(example);
        }
        trueAnswer = true;
        return table.getResult(example);

    }

    private void numbers() {
        if (table.hasNext()) {
            String ex = table.getNextExample();
            String quetions = ex+ " = " + getResult(ex);
            quetion.setText(quetions);
            example = ex;
            i++;
        }
       else  if (i == 9 || i == 81) {
            //  res.setText(String.valueOf(table.getResolved()));
            //resFalse.setText(String.valueOf(table.getUnResolved()));
            Intent i = new Intent(MainActivity.this, FinalActivity.class);
            i.putExtra("username", table.getResolved());
            i.putExtra("gift", table.getUnResolved());
            startActivity(i);


        }

    }

//    private String getExample() {
//        String example;
//        if (table.hasNext()) {
//            example = table.getNextExample();
//            return example;
//        } else {
//            return null;
//        }
//
//    }


    }

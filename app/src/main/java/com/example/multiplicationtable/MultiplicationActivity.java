package com.example.multiplicationtable;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.NoSuchElementException;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MultiplicationActivity extends Activity {
    private TextView trueRes, falseRes, quetions;
    private String userAnswerText;
    private int userNum;
    private String clone = "";
    private int number = 0;
    private int midStat;
    private EditText answer;
    private ProgressBar progressBar;
    private String example;
    ObjectAnimator animation;
    private int i = 0;
    private int t = 0;
    private int j = 0;
    private int nm = 0;
    private  boolean ans = false;


    MultiplicationTable table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_layout);
        init();
        pbar();


    }

    private void init() {
        trueRes = findViewById(R.id.trueRes);
        falseRes = findViewById(R.id.falseRes);
        quetions = findViewById(R.id.quetions);
        answer = findViewById(R.id.answer);
        progressBar = findViewById(R.id.progressBar);
        t = getIntent().getExtras().getInt("username");
        if (t == 0) {
            table = new MultiplicationTable();
        } else {
            table = new MultiplicationTable(t);
        }


    }

    private void number() {
        if (table.hasNext()) {
            String ex = table.getNextExample();
            quetions.setText(ex);
            example = ex;
//            if (i >= 1) {
//                if (t != 0) {
//                    midStat = 11;
//                    progressBar.incrementProgressBy(midStat);
//                } else {
//                    double midStatus = 100 / 81;
//                    progressBar.incrementProgressBy((int) midStatus);
//                }
//            }
//
//            i++;

//        } else if (i == 9 || i == 81) {
//            progressBar.setProgress(100);
//            Intent i = new Intent(MultiplicationActivity.this, FinalActivity.class);
//            i.putExtra("username", table.getResolved());
//            i.putExtra("gift", table.getUnResolved());
//            startActivity(i);
//        }


        }
    }


    public void onClickNext(View view) throws NumberFormatException {
        clone = answer.getText().toString();
        try {
            number = Integer.parseInt(clone);

        } catch (NumberFormatException ex) {

            number = 0;

        } finally {

            ans = table.solvexample(example, number);
            animation.end();
            animation.start();


        }
    }

    private void pbar() {

        animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animation.setDuration(10000);
        animation.setInterpolator(new DecelerateInterpolator());

        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) throws NoSuchElementException {
                if (number > 0) {
                    if (ans) {
                        number();
                    } else {
                        number();
                    }
                    trueRes.setText(String.valueOf(table.getResolved()));
                    falseRes.setText(String.valueOf(table.getUnResolved()));
                    answer.setText(null);
                } else {

                    number();
                }
            }


            @Override
            public void onAnimationEnd(Animator animator) throws NumberFormatException {
                if (table.hasNext() && progressBar.getProgress() == 100) {

                    userAnswerText = answer.getText().toString();
                    try {
                        userNum = Integer.parseInt(userAnswerText);

                    } catch (NumberFormatException ex) {

                        userNum = 1;

                    }
                    finally {


                        table.solvexample(example, userNum);
                        trueRes.setText(String.valueOf(table.getResolved()));
                        falseRes.setText(String.valueOf(table.getUnResolved()));
                        answer.setText(null);
                        number = -1;
                        progressBar.setProgress(100);
                        pbar();

                    }
                    } else{
                        Intent i = new Intent(MultiplicationActivity.this, FinalActivity.class);
                        i.putExtra("username", table.getResolved());
                        i.putExtra("gift", table.getUnResolved());
                        startActivity(i);
                    }
                }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animation.start();

            }
        }





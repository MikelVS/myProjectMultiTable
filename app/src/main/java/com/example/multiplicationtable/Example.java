package com.example.multiplicationtable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Example {
    private String textRepresentstion;
    private int result;

    public Example(String textRepresentstion,int result){
        this.result = result;
        this.textRepresentstion = textRepresentstion;
    }

    public String getTextRepresentstion() {
        return textRepresentstion;
    }

    public int getResult() {
        return result;
    }

   @Override
    public String toString() {
        return "Example{" +
             "textRepresentstion='" + textRepresentstion + '\'' +
               ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return result == example.result &&
                textRepresentstion.equals(example.textRepresentstion);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int hashCode() {
        return Objects.hash(textRepresentstion, result);
    }
}
